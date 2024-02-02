FROM python:3.9-slim

# 작업 디렉토리 설정
WORKDIR /app

# 현재 디렉토리의 파일을 컨테이너의 /app 디렉토리로 복사
COPY flask_mongodb_crawling.py /app/sqc-ai.py

# Flask 및 MongoDB 관련 라이브러리 설치
RUN pip install Flask requests beautifulsoup4 selenium pymongo webdriver-manager

# CMD에 파이썬 파일 실행 추가
CMD ["python", "sqc-ai.py"]
