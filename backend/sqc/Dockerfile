# 빌드 단계: 그레이들 빌드
FROM gradle:latest AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY src ./src
RUN gradle build -x test

# 실행 단계: OpenJDK 17 JRE 이미지 사용
FROM openjdk:17-jdk-slim AS runtime
WORKDIR /app
COPY --from=build /app/build/libs/*.jar ./sqc-backend.jar
EXPOSE 8080
CMD ["java", "-jar", "sqc-backend.jar"]
