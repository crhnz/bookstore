# Define build-time variables BEFORE ANY `FROM`
ARG JDK_VERSION=21
ARG DISTROLESS_VERSION=21

# Build stage
FROM eclipse-temurin:${JDK_VERSION}-jdk AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM gcr.io/distroless/java${DISTROLESS_VERSION}-debian12
WORKDIR /app

ARG VERSION=0.0.1
LABEL org.opencontainers.image.version=$VERSION
LABEL org.opencontainers.image.title="My Bookstore APP"
LABEL org.opencontainers.image.description="Built with Java ${JDK_VERSION} and Distroless ${DISTROLESS_VERSION}"

COPY --from=builder /app/apps/backend/target/*.jar app.jar
CMD ["app.jar"]
