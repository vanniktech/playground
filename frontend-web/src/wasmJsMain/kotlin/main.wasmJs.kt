import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver

@OptIn(ExperimentalWasmJsInterop::class)
actual fun driver(): SqlDriver = WebWorkerDriver(
  org.w3c.dom.Worker(
    getScriptURL(),
  ),
)

@OptIn(ExperimentalWasmJsInterop::class)
private fun getScriptURL(): String = js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)""")
