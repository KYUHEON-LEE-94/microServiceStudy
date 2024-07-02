package com.study.microservcie.service

import com.study.microservcie.dto.ProductRequest
import com.study.microservcie.model.Product
import com.study.microservcie.repository.ProductRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import lombok.extern.log4j.Log4j2
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *packageName    : com.study.microservcie.service
 * fileName       : ProductService
 * author         : LEE KYUHEON
 * date           : 2024-07-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-02        LEE KYUHEON       최초 생성
 */

private val logger = KotlinLogging.logger{}

@Service
@Slf4j
class ProductService @Autowired constructor(
    private val productRepository:ProductRepository
){

    fun createProduct(productRequest: ProductRequest){
        /*kotlin의 apply == builder*/
        var product:Product = Product().apply {
            name=productRequest.name
            description=productRequest.description
            price=productRequest.price
        }
        productRepository.save(product)
        logger.atDebug { "Product is Savedd: ${product.id}" }
    }
}