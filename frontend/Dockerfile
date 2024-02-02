# 빌드 단계
FROM node:alpine AS build
# 작업 디렉토리 설정
WORKDIR /app
# 소스 코드 복사
COPY package.json package-lock.json ./
# 의존성 설치
RUN npm install
# 소스 코드 복사 (의존성이 변경되지 않았을 때 캐시 사용)
COPY . .
# 빌드
RUN npm run build
# 실행 단계
FROM nginx:alpine

# 빌드한 앱 파일 복사
COPY --from=build /app/build /usr/share/nginx/html
# 포트 설정
EXPOSE 80
# Nginx 실행
CMD ["nginx", "-g", "daemon off;"]