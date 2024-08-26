# microServiceStudy
# 재원
## Language & FrameWork
- kotlin (JDK17)
- spring boot

## DB
- MongoDB (by Docker)
-  MySql
  
## Tools
- Eureka
- spring cloud gateway
- keyCloak
- resilience4j (circuitbreaker)
- zipkin
- kafka
- grafana, prometheus

## Script
### 실습에 필요한 도커 컨테이너 생성 스크립트
- dockerScript 폴더 아래에 있는 .ps1, sh 스크립트로 Docker 컨테이너 생성 및 실행 가능
  - powerShell: run_docker_containers.ps1
  - Linux: run_docker_containers.sh
 
### k8s yaml
- Infra: DB, gateway, tools
- application: inventory-service, notification-service, order-service, product-service
 
## DockerHub
- [https://hub.docker.com/](https://hub.docker.com/repository/docker/maizurzu/product/general)
 
# 목적
- spring boot Microservice Project 배우기 및 활용
- Java -> kotlin으로 코드를 변경함으로써 kotlin 문법 적응
- 다양한 Tool 학습
- kuernetes 실전 적용

# API
### Proudct

POST http://localhost:8080/api/product
- Request
```java
    {
        "name": "Iphone14",
        "description": "Iphone 14",
        "price": 1200
    }
```

### Order-Srevice

GET http://localhost:8080/api/order
- Response
```java
    {
        "orderLineItemsDtoList": [
            {
                "skuCode":"iphone_14",
                "price":1200,
                "quantity":1
            }
        ]
    }
```

POST http://localhost:8080/api/order
- Request
```java
    {
        "orderLineItemsDtoList": [
            {
                "skuCode":"iphone_14",
                "price":1200,
                "quantity":1
            }
        ]
    }
```
-Response
```text
Order Placed Successfully
```

# 목표 아키텍처
![image](https://github.com/user-attachments/assets/a4a9ef9b-fe4f-4b73-8a66-c4083452ebe3)


## How To
#### 1. Order-service는 유동적으로 변할 수 있는 Inventory-Service를 어떻게 알아낼것인가?
![image](https://github.com/KYUHEON-LEE-94/microServiceStudy/assets/101496219/22ef289f-788a-4a31-9165-35e6667b0d3a)

-> Discovery Service 사용 (Eureka)

#### 2. Api GateWay 사용
![image](https://github.com/user-attachments/assets/6e8351bb-7d57-43eb-a46a-2c62300c89cf)

Entry Point를 아래와 같이 변경하여 Api GateWay를 활용하여 보다 효율적인 관리를 할 수 있도록 하자


![image](https://github.com/user-attachments/assets/4ed56f1e-a177-4d83-9161-1146013459f9)

##### 얻을 수 있는 이점
- Routing based on Request Headers
- Authentication
- Security
- Load Balancing
- SSL Termination

#### 3. Circuit Breaker
- Resilience4j 를 사용
  
![image](https://github.com/user-attachments/assets/c9cf7125-7ceb-4eca-9d2f-41818b353052)
```
Circuit Breaker?:
- 소프트웨어 디자인 패턴 중 하나로, 시스템의 안정성을 높이고 장애 복구 시간을 줄이기 위해 사용
- 이 패턴은 네트워크나 다른 외부 서비스에 의존하는 시스템에서 특히 유용
- Circuit Breaker 패턴은 호출하는 서비스가 지속적으로 실패할 때, 일정 시간 동안 호출을 중단하여 시스템의 과부하를 방지

Circuit Breaker의 세 가지 상태
- Closed (닫힘): 요청을 정상적으로 수행하고 모든 요청이 외부 서비스로 전달. 만약 외부 서비스가 실패하면 실패 횟수를 기록
- Open (열림): 외부 서비스가 일정 횟수 이상 실패하면 Circuit Breaker는 열림 상태로 전환. 이 상태에서는 외부 서비스로의 호출을 즉시 실패로 간주하고 더 이상 요청을 전달하지 않음. 이로 인해 시스템은 실패한 서비스에 대한 연속적인 호출로 인한 부하를 피할 수 있음.
- Half-Open (반열림): 일정 시간이 지나면 Circuit Breaker는 반열림 상태로 전환되어, 일부 요청을 외부 서비스로 전달하여 서비스가 복구되었는지 확인. 만약 성공하면 Circuit Breaker는 닫힘 상태로 돌아가고, 실패하면 다시 열림 상태로 전환

주요 기능
- 장애 전파 방지: 하나의 서비스 장애가 다른 서비스로 확산되는 것을 막습니다.
- 자동 복구: 외부 서비스가 복구되었는지 주기적으로 확인하여 자동으로 시스템을 정상 상태로 되돌립니다.
- 시스템 안정성 향상: 장애가 발생한 외부 서비스로의 호출을 제한함으로써 시스템 전체의 안정성을 높입니다.
```

#### 4. Trace Log
![image](https://github.com/user-attachments/assets/9a437bc6-7448-4f16-b96e-032ef8d62bbb)

- 현재 Tracing이 이리저리 흩뿌려져 있다.
- Tracd ID를 사용하여 로그를 추적하여 파악하자.
- spring cloud sleuth를 사용하자.

##### 사용 예시 화면
![image](https://github.com/user-attachments/assets/1d26c155-8704-443f-99c4-d36674fd6cea)

#### 5. prometheus, Grafana
![image](https://github.com/user-attachments/assets/fe65da53-fa60-4fcb-8c49-fa9ed31b91b5)



# 공부 영상
- https://www.youtube.com/watch?v=mPPhcU7oWDU&list=WL&index=1&t=468s

# 그 외 출처
- https://www.keycloak.org/getting-started/getting-started-docker
- https://spring.io/projects/spring-cloud
- https://hub.docker.com/
