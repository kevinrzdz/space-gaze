# Deploy — Space Gaze

Production target: **Railway** (single Docker web service + managed MySQL).

The Angular build is packaged inside the Spring Boot JAR and served from the same origin (no CORS needed in production).

## Architecture

- **Web service:** Docker image from the repo root `Dockerfile`
- **Database:** Railway MySQL plugin
- **Uploads:** baked into the image for demo images; optional volume at `/app/uploads` for persistence

## Cost note

JVM + MySQL usually need Railway **Hobby (~$5/month)** for a stable always-on portfolio demo. The free trial credits are enough to validate the deploy.

## Deploy checklist (Railway)

1. **Push** this repository to GitHub (`kevinrzdz/space-gaze` or your fork).

2. In [Railway](https://railway.app), create a **New Project**.

3. **Add MySQL**
   - New → Database → MySQL
   - Note the generated variables (`MYSQLHOST`, `MYSQLPORT`, `MYSQLUSER`, `MYSQLPASSWORD`, `MYSQLDATABASE`, or `MYSQL_URL`).

4. **Add the web service**
   - New → GitHub Repo → select this repository
   - Railway should detect the root `Dockerfile` / `railway.toml`
   - Root directory: repository root (default)

5. **Set environment variables** on the web service:

   | Variable | Value |
   | --- | --- |
   | `SPRING_DATASOURCE_URL` | `jdbc:mysql://${{MySQL.MYSQLHOST}}:${{MySQL.MYSQLPORT}}/${{MySQL.MYSQLDATABASE}}?serverTimezone=UTC` (use Railway variable references) |
   | `SPRING_DATASOURCE_USERNAME` | `${{MySQL.MYSQLUSER}}` |
   | `SPRING_DATASOURCE_PASSWORD` | `${{MySQL.MYSQLPASSWORD}}` |
   | `JWT_SECRET` | a random string of at least 32 characters |
   | `CORS_ALLOWED_ORIGINS` | your public HTTPS URL (optional when same-origin; useful if you keep a separate frontend) |
   | `JAVA_OPTS` | `-Xmx384m` |
   | `PORT` | left to Railway (injected automatically) |

6. **Initialize the database** (once):
   - Open the MySQL service → Query / Data / connect with any MySQL client
   - Run [`source/schema.sql`](../source/schema.sql)
   - Run [`source/seed-demo.sql`](../source/seed-demo.sql)

7. **Public URL**
   - Web service → Settings → Networking → Generate Domain
   - Paste the HTTPS URL into the root [`README.md`](../README.md) Live demo section

8. **Verify**
   - Open the URL → Login with `demo@spacegaze.app` / `Demo1234!`
   - Check Planets, Asteroids, Stars, Exoplanets, and Events
   - Confirm asteroid images load for seeded rows that reference `uploads/`

## Local production-like run

```bash
docker compose up --build
```

App: [http://localhost:8090](http://localhost:8090)

## Demo credentials

- Email: `demo@spacegaze.app`
- Password: `Demo1234!`

## Legacy note

The previous student hosting URL (`spacegaze.alu7359.arkania.es` with Nginx + Tomcat) is retired. Use Railway (or `docker compose`) instead.
