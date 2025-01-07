package app.playground

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.decode.DataSource
import coil3.decode.ImageSource
import coil3.fetch.FetchResult
import coil3.fetch.Fetcher
import coil3.fetch.SourceFetchResult
import coil3.memory.MemoryCache
import coil3.request.Options
import coil3.util.DebugLogger
import io.ktor.client.HttpClient
import io.ktor.client.request.prepareGet
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.bodyAsChannel
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.readRemaining
import kotlinx.coroutines.withContext
import kotlinx.io.readByteArray
import okio.Buffer
import okio.BufferedSource
import okio.Sink
import okio.buffer
import okio.use
import kotlin.coroutines.CoroutineContext

@Composable fun initCoil(frontendDependencies: FrontendDependencies) {
  setSingletonImageLoaderFactory { context ->
    ImageLoader.Builder(context)
      .memoryCache(MemoryCache.Builder().maxSizeBytes(10_000_000).build())
      .logger(DebugLogger())
      .components {
        val factory = ProfileAvatarRequestFactory(frontendDependencies)
        add(factory, Int::class)
      }
      .build()
  }
}

internal class ProfileAvatarRequestFactory(
  private val frontendDependencies: FrontendDependencies,
) : Fetcher.Factory<Int> {
  override fun create(data: Int, options: Options, imageLoader: ImageLoader): Fetcher = ProfileAvatarRequestFetcher(
    httpClient = frontendDependencies.httpClient,
    dispatcher = frontendDependencies.ioDispatcher,
    profileAvatarRequest = data,
    options = options,
  )
}

internal class ProfileAvatarRequestFetcher(
  private val httpClient: HttpClient,
  private val profileAvatarRequest: Int,
  private val options: Options,
  private val dispatcher: CoroutineContext,
) : Fetcher {
  override suspend fun fetch(): FetchResult? {
    println("FETCHING $profileAvatarRequest")

    return withContext(dispatcher) {
      inMemory(httpClient.prepareGet("https://xsgames.co/randomusers/avatar.php?g=male"))
    }
  }

  private suspend fun inMemory(statement: HttpStatement) = SourceFetchResult(
    source = ImageSource(
      source = statement.execute().bodyAsChannel().toBufferedSource(),
      fileSystem = options.fileSystem,
    ),
    mimeType = null,
    dataSource = DataSource.NETWORK,
  )
}

private suspend fun ByteReadChannel.toBufferedSource(): BufferedSource {
  val buffer = Buffer()
  readFully(buffer)
  return buffer
}

private const val OKIO_RECOMMENDED_BUFFER_SIZE = 8192

@Suppress("NAME_SHADOWING")
suspend fun ByteReadChannel.readFully(sink: Sink) {
  val channel = this
  sink.buffer().use { sink ->
    while (!channel.isClosedForRead) {
      val packet = channel.readRemaining(OKIO_RECOMMENDED_BUFFER_SIZE.toLong())
      while (!packet.exhausted()) {
        sink.write(packet.readByteArray())
      }
    }
  }
}
