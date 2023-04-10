# springboot - redis 테스트 샘플 프로젝트
SpringBoot 프레임워크 - redis 공통 모듈을 만들기 위한 샘플 프로젝트

### 디펜던시 버전
SpringBoot : 3.0.5  
Java : 17  
Redis : 7.0.10
Gradle : 7.6

### 프로젝트 구조
- config  
  Redis DB 연결관련 설정
- controller  
  Key 등록, 조회 api 샘플  
  Session 등록, 조회 api 샘플  
- Service  
  Key 등록, 조회 비지니스 로직


### 회원 API
GET http://localhost:8080/redis : redis에 key 값으로 조회  
POST http://localhost:8080/redis : redis에 key, value 등록  
GET http://localhost:8080/sessionId : 현재 sessionId 조회  
POST http://localhost:8080/login : rlogin 하면서 사용자 정보 (name, value) 등록  

### 설명
1. application.yml 설정
```
spring:
  session:
    store-type: redis
    redis:
      flush-mode: on_save
      namespace: spring:session
      repository-type: indexed
  data:
    redis:
      host: 172.30.10.25
      port: 6379
      password: insoft!23
```  
2. 프로그램 실행  
```
   java -jar build\libs\redis-test-0.0.1-SNAPSHOT.jar
```