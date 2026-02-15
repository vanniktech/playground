import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver

actual fun driver(): SqlDriver = WebWorkerDriver(
  org.w3c.dom.Worker(
    js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)"""),
  ),
)
