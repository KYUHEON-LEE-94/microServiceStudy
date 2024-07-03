package com.study.microservcie

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
class MicroServcieApplicationTests {

    /**
     *  companion object: 클래스에 속한 객체를 정의
     *  이는 코틀린에서 정적(static) 멤버와 비슷한 역할을
     *
     *  @Container : Testcontainers 라이브러리에서 제공하는 어노테이션으로, 컨테이너의 생명 주기를 테스트 클래스와 연결
     *  이 어노테이션을 붙인 컨테이너는 테스트가 시작될 때 자동으로 시작되고, 테스트가 종료될 때 자동으로 종료
     *
     *  @JvmStatic: Java와의 상호 운용성을 높이기 위해 사용
     *  이 어노테이션을 사용하면 Java 코드에서 ProductServiceTests.mongoDBContainer처럼 접근 가능
     * **/
    companion object {
        @Container
        @JvmStatic
        var mongoDBContainer: MongoDBContainer = MongoDBContainer("mongo:4.4.2").apply {
            withExposedPorts(27017)
        }
    }

    fun setProperties(dynamicPropertyRegistry: DynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl)
    }

    @Test
    fun contextLoads() {
    }

}
