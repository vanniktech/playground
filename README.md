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
  - Run locally: `./gradlew server:run`
  - Run as jar: `./gradlew server:shadowJar && java -jar server/build/libs/server-all.jar`

- Js: `./gradlew frontend-web:jsBrowserDevelopmentRun`
