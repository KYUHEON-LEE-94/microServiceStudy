# microServiceStudy
# 재원
- kotlin (JDK17)
- spring boot
- MongoDB (by Docker)

# 목적
- spring boot Microservice Project 배우기 및 활용
- Java -> kotlin으로 코드를 변경함으로써 kotlin 문법 적응

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

# 참고 자료
- https://www.youtube.com/watch?v=mPPhcU7oWDU&list=WL&index=1&t=468s
