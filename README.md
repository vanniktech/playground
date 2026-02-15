Playground
==========

- Android: `./gradlew installDebug`
- iOS: XCode ...
- Desktop: `./gradlew hotRun --auto`
- Server
  - Postgres is required with:
```sql
CREATE USER playground WITH PASSWORD 'playground';
CREATE DATABASE playground;
ALTER DATABASE playground OWNER TO playground;
```
  - Run locally: `./gradlew playground-server:run`
  - Run as jar: `./gradlew playground-server:shadowJar && java -jar playground-server/build/libs/server-all.jar`

- Js: `./gradlew playground-frontend-web:jsBrowserDevelopmentRun`
