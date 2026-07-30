# Deployment

Space Gaze is packaged as a single Docker image: the Angular production build is copied into the Spring Boot JAR and served from the same origin.

## Architecture

| Component | Role |
| --- | --- |
| Web service | Spring Boot + Angular static assets (`Dockerfile`) |
| Database | MySQL 8 |
| Uploads | Demo images included in the image; mount `/app/uploads` if you need persistence |

## Run locally (production-like)

```bash
docker compose up --build
```

Application: [http://localhost:8090](http://localhost:8090)

Demo account: `demo@spacegaze.app` / `Demo1234!`

## Environment variables

| Variable | Description |
| --- | --- |
| `PORT` | HTTP port (provided automatically on Railway) |
| `SPRING_DATASOURCE_URL` | JDBC URL, e.g. `jdbc:mysql://host:3306/space_gaze?serverTimezone=UTC` |
| `SPRING_DATASOURCE_USERNAME` | Database user |
| `SPRING_DATASOURCE_PASSWORD` | Database password |
| `JWT_SECRET` | HMAC signing secret (minimum 32 characters) |
| `CORS_ALLOWED_ORIGINS` | Comma-separated origins (mainly for local `ng serve`) |
| `UPLOADS_DIR` | Upload directory (default: `uploads`) |
| `JAVA_OPTS` | JVM options (recommended: `-Xmx384m`) |

## Railway

1. Create a new project and add a **MySQL** database.
2. Add a service from this GitHub repository (root `Dockerfile`).
3. Configure the web service variables (use Railway references to the MySQL service):

   ```text
   SPRING_DATASOURCE_URL=jdbc:mysql://${{MySQL.MYSQLHOST}}:${{MySQL.MYSQLPORT}}/${{MySQL.MYSQLDATABASE}}?serverTimezone=UTC
   SPRING_DATASOURCE_USERNAME=${{MySQL.MYSQLUSER}}
   SPRING_DATASOURCE_PASSWORD=${{MySQL.MYSQLPASSWORD}}
   JWT_SECRET=<random-string-at-least-32-chars>
   JAVA_OPTS=-Xmx384m
   ```

   Adjust the `MySQL` service name in the references if yours differs.

4. Initialize the database once with [`source/schema.sql`](../source/schema.sql) and [`source/seed-demo.sql`](../source/seed-demo.sql).
5. Generate a public domain under the web service networking settings.
6. Confirm login and catalog pages on the live URL.

## Database scripts

| File | Purpose |
| --- | --- |
| [`source/schema.sql`](../source/schema.sql) | Tables only (safe for an empty managed database) |
| [`source/seed-demo.sql`](../source/seed-demo.sql) | Demo catalog + demo user |
| [`source/space_gaze.sql`](../source/space_gaze.sql) | Full local bootstrap (create DB + schema + seed) |
