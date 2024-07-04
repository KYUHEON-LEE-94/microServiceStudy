package com.study.microservcie

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.microservcie.dto.ProductRequest
import com.study.microservcie.repository.ProductRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.math.BigDecimal
import kotlin.test.assertEquals

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
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

        @DynamicPropertySource //동적으로 속성을 설정하는 데 사용되는 어노테이션
        @JvmStatic
        fun setProperties(dynamicPropertyRegistry: DynamicPropertyRegistry){
            dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl)
        }
    }

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper:ObjectMapper

    @Autowired
    private lateinit var productRepository: ProductRepository



    @Test
    fun shouldCreateProduct() {
        val productRequest = getProductRequest()
        val productRequestString = objectMapper.writeValueAsString(productRequest)

        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(productRequestString)
        )
            .andExpect(status().isCreated)
        assertEquals(1, productRepository.findAll().size )
    }

    private fun getProductRequest():ProductRequest {
        return ProductRequest().apply {
            name="iPhone 13"
            description="iPhone 13"
            price= BigDecimal.valueOf(1200)
        }
    }

}
