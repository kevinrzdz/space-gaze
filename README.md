

# Space Gaze



## Live demo

Once deployed on Railway, the public URL will be listed here.


|                   |                      |
| ----------------- | -------------------- |
| **Demo login**    | `demo@spacegaze.app` |
| **Demo password** | `Demo1234!`          |


> Create a free account via Register if you prefer.

## Stack

- **Frontend:** Angular 15 + Bootstrap / Angular Material
- **Backend:** Spring Boot 2.7 (Java 17), JWT auth
- **Database:** MySQL 8

## Quick start (Docker)

Requires Docker and Docker Compose.

```bash
docker compose up --build
```

Open [http://localhost:8090](http://localhost:8090) and sign in with the demo credentials above.

## Local development



### Database

```bash
mysql -u root -p < source/space_gaze.sql
```

Or apply `[source/schema.sql](source/schema.sql)` then `[source/seed-demo.sql](source/seed-demo.sql)` on an existing `space_gaze` database.

### Backend

```bash
cd source/backend
./mvnw spring-boot:run
```

API default: `http://localhost:8090`

### Frontend

```bash
cd source/frontend
npm install
npm start
```

UI default: `http://localhost:4200` (proxies API calls to `http://localhost:8090` via `environment.apiUrl`).

## Environment variables


| Variable                     | Description              | Default (local)                                             |
| ---------------------------- | ------------------------ | ----------------------------------------------------------- |
| `PORT`                       | HTTP port                | `8090`                                                      |
| `SPRING_DATASOURCE_URL`      | JDBC URL                 | `jdbc:mysql://localhost:3306/space_gaze?serverTimezone=UTC` |
| `SPRING_DATASOURCE_USERNAME` | DB user                  | `space_gaze`                                                |
| `SPRING_DATASOURCE_PASSWORD` | DB password              | `space_gaze2023.`                                           |
| `JWT_SECRET`                 | HMAC secret (≥ 32 chars) | dev fallback in `application.properties`                    |
| `CORS_ALLOWED_ORIGINS`       | Comma-separated origins  | `http://localhost:4200`                                     |
| `UPLOADS_DIR`                | Upload directory         | `uploads`                                                   |
| `JAVA_OPTS`                  | JVM flags (Docker)       | `-Xmx384m`                                                  |




## Deploy

See [deploy/README.md](./deploy/README.md) for Railway deployment steps.

## Documentation

- [English docs](./doc/english%20%F0%9F%87%AC%F0%9F%87%A7/)
- [Documentación en español](./doc/espa%C3%B1ol%20%F0%9F%87%AA%F0%9F%87%B8)
- [Source](./source/)
- [Testing](./testing/README.md)



### Introduction

Space Gaze is a web application that provides information on celestial objects — planets, exoplanets, asteroids, stars, and astronomical events — with user accounts, tracking, and image uploads.
