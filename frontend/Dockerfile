# 베이스 이미지 설정
FROM node:14.17.0-alpine as build

# 작업 디렉토리 설정
WORKDIR /app

# 소스 코드 복사
COPY . .

# 애플리케이션 빌드
RUN npm install
RUN npm run build

# 두 번째 단계: Nginx를 사용하여 정적 파일을 제공
FROM nginx:1.21.3-alpine

# Nginx 설정 파일 복사
COPY --from=build /app/build /usr/share/nginx/html

# Nginx 서버 시작
CMD ["nginx", "-g", "daemon off;"]
