# 베이스 이미지로부터 시작 (OpenJDK 사용)
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 호스트의 현재 디렉토리에 있는 JAR 파일을 컨테이너의 /app 디렉토리로 복사
COPY build/libs/sqc-0.0.1-SNAPSHOT.jar /app/sqc-backend.jar

# 애플리케이션 실행 명령
CMD ["java", "-jar", "sqc-backend.jar"]
