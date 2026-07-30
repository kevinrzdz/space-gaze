# Multi-stage build: Angular frontend + Spring Boot backend in one image.

FROM node:18-alpine AS frontend
WORKDIR /frontend
COPY source/frontend/package.json source/frontend/package-lock.json ./
RUN npm ci
COPY source/frontend/ ./
# Bust cached frontend layers when sources change between deploys.
ARG FRONTEND_BUILD_ID=dev
RUN echo "frontend build ${FRONTEND_BUILD_ID}" && npm run build

FROM eclipse-temurin:17-jdk-jammy AS backend
WORKDIR /backend
COPY source/backend/ ./
COPY --from=frontend /frontend/dist/frontend/ ./src/main/resources/static/
RUN chmod +x mvnw && ./mvnw -DskipTests package \
    && JAR=$(ls target/*.jar | grep -v '\.original$' | head -1) \
    && cp "$JAR" /backend/app.jar

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
RUN mkdir -p /app/uploads
COPY --from=backend /backend/app.jar /app/app.jar
COPY source/backend/uploads/ /app/uploads/
ENV JAVA_OPTS="-Xmx384m"
ENV PORT=8090
EXPOSE 8090
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
