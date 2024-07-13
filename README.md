# microServiceStudy
# 재원
- kotlin (JDK17)
- spring boot
- MongoDB (by Docker)
- Eureka
- spring cloud gateway

# 목적
- spring boot Microservice Project 배우기 및 활용
- Java -> kotlin으로 코드를 변경함으로써 kotlin 문법 적응

## 목표 아키텍처
![image](https://github.com/user-attachments/assets/a4a9ef9b-fe4f-4b73-8a66-c4083452ebe3)


### How To
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

# API
##Proudct

POST http://localhost:8081/api/product
```java
    {
        "name": "Iphone14",
        "description": "Iphone 14",
        "price": 1200
    }
```

Order-Srevice

POST http://localhost:8081/api/order
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

# 공부 영상
- https://www.youtube.com/watch?v=mPPhcU7oWDU&list=WL&index=1&t=468s

# 그 외 출처
- https://www.keycloak.org/getting-started/getting-started-docker
- https://spring.io/projects/spring-cloud
