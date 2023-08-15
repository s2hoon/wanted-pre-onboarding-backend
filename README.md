# 지원자 성명: 조수훈 


---


## 애플리케이션 실행 방법
1. 로컬에서 프로젝트를 빌드

  ```./gradlew build```

2. 도커 이미지 빌드 및 푸시하기

```docker build -t chosuhoon/preonboard .```

```docker login```

```docker push chosuhoon/preonboard```


3. EC2 환경에서 도커 이미지 실행

```docker pull chosuhoon/preonboard```

```docker-compose up -d```



배포 링크 : 43.202.50.205:8080


![system archi](https://github.com/s2hoon/wanted-pre-onboarding-backend/assets/82464990/ff0673c5-59e3-4fa9-9444-4d56aae2bdae)



---

# 데이터베이스 테이블 구조

![preonboarding erd](https://github.com/s2hoon/wanted-pre-onboarding-backend/assets/82464990/1c1062f7-b510-438d-bddd-e73c6bc0a2f3)




---

# 구현한 API의 동작을 촬영한 데모 영상 링크


https://www.youtube.com/watch?v=Dc6ZwdZt3W0


---

# 구현 방법 및 이유에 대한 간략한 설명


관계형 데이터베이스와의 상호작용을 위해 JPA 객체 관계 매핑(ORM) 을 사용


1:N 연관관계 매핑

간편한 CRUD 작업을 자동으로 처리하기 위해 Spring Data JPA 사용


SpringSecurity 와 JWT를 활용하여 로그인 했을경우 JWT Token 반환


Post를 수정하거나 삭제할때 해당유저가 해당 Post 를 작성했는지 확인, 아니라면 오류


DTO를 사용하여 각 클라이언트에 맞는 데이터를 제공할수 있게끔하고, domain 을 바꿀수있도록 설계


BaseException 을 활용하여 일관성 있는 예외 처리, 코드 중복 최소화 및 유지보수성 향상




---

# API 명세(request/response 포함)

https://woozy-cuticle-bfb.notion.site/API-880f51845bde4e1caa907fc40ee24a71?pvs=4
