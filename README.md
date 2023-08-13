# 지원자 성명: 조수훈 

---


## 애플리케이션 실행 방법
1. 로컬에서 프로젝트를 빌드
./gradlew build
2. 도커 이미지 빌드 및 푸시하기
docker build -t chosuhoon/preonboard .
docker login
docker push chosuhoon/preonboard
3. EC2 환경에서 도커 이미지 실행
docker pull chosuhoon/preonboard
docker-compose up


클라우드 환경(AWS, GCP)에 배포 환경을 설계하고 애플리케이션을 배포한 경우 (README.md 파일에 배포된 API 주소와 설계한 AWS 환경 그림으로 첨부


---

# 데이터베이스 테이블 구조

![preonboarding erd](https://github.com/s2hoon/wanted-pre-onboarding-backend/assets/82464990/1c1062f7-b510-438d-bddd-e73c6bc0a2f3)

Post에 Author를 추가하여 인증 할수있지만 , table 을 간소화하기 위하여 다른방법을 사용하였습니다.



---

# 구현한 API의 동작을 촬영한 데모 영상 링크
영상 링크


---

# 구현 방법 및 이유에 대한 간략한 설명
1대다
BaseException 에 대한 설명
Spring Data Jpa
Jwt 관련?
DTO
통합 테스트 또는 단위 테스트 코드를 추가한 경우


---

# API 명세(request/response 포함)
음 노션 캡쳐를 올릴까??
