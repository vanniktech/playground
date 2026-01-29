package app.playground

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.memory.MemoryCache

@Composable fun initCoil(frontendDependencies: FrontendDependencies) {
  setSingletonImageLoaderFactory { context ->
    ImageLoader.Builder(context)
      .memoryCache(MemoryCache.Builder().maxSizeBytes(10_000_000).build())
      .build()
  }
}
