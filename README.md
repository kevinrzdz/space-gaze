# Space Gaze

<div align="center">
  <img src="./img/logo.png" alt="Space Gaze logo" height="220">
</div>

Space Gaze is a full-stack web application for exploring celestial objects — planets, exoplanets, asteroids, stars, and astronomical events. Users can register, sign in, browse catalog data, track objects of interest, and contribute images.

Built as a web development project and prepared for public deployment as a portfolio demo.

## Features

- Browse planets, asteroids, stars, exoplanets, and astronomical events
- Search and filter catalog entries
- JWT-based authentication (register / login)
- User profile with image upload
- Object tracking for signed-in users
- Single deployable unit: Angular UI served by Spring Boot

## Tech stack

| Layer | Technology |
| --- | --- |
| Frontend | Angular 15, Bootstrap, Angular Material |
| Backend | Spring Boot 2.7, Java 17, Spring Security, JWT |
| Database | MySQL 8 |
| Packaging | Docker (multi-stage build) |

## Live demo

<!-- Replace with your public HTTPS URL after deploying -->
_Deployment URL coming soon._

**Try it locally** with Docker (recommended):

```bash
docker compose up --build
```

Then open [http://localhost:8090](http://localhost:8090).

| | |
| --- | --- |
| Demo email | `demo@spacegaze.app` |
| Demo password | `Demo1234!` |

You can also create your own account from the Register page.

## Project structure

```
source/
  frontend/   # Angular application
  backend/    # Spring Boot API
  schema.sql  # Database schema
  seed-demo.sql
deploy/       # Deployment guide
doc/          # Project documentation (EN / ES)
```

## Local development

### Prerequisites

- Java 17+
- Node.js 18+ (for the Angular CLI)
- MySQL 8
- Or Docker / Docker Compose for the all-in-one setup above

### Database

```bash
mysql -u root -p < source/space_gaze.sql
```

### Backend

```bash
cd source/backend
./mvnw spring-boot:run
```

API: `http://localhost:8090`

### Frontend

```bash
cd source/frontend
npm install
npm start
```

UI: `http://localhost:4200` (calls the API at `http://localhost:8090` in development)

## Deployment

See [deploy/README.md](./deploy/README.md) for production deployment with Docker and Railway.

## Documentation

- [English documentation](./doc/english%20%F0%9F%87%AC%F0%9F%87%A7/)
- [Documentación en español](./doc/espa%C3%B1ol%20%F0%9F%87%AA%F0%9F%87%B8)
- [Testing notes](./testing/README.md)

## License

See [LICENSE](./LICENSE).
