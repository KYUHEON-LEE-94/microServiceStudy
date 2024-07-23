package com.study.product.service

import com.study.product.dto.ProductRequest
import com.study.product.dto.ProductResponse
import com.study.product.model.Product
import com.study.product.repository.ProductRepository
import io.github.oshai.kotlinlogging.KotlinLogging
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

    fun getAllProducts():List<ProductResponse>{
        return productRepository.findAll().stream().map { product ->
            mapToProductResponse(product)
        }.toList()
    }
    fun mapToProductResponse(product: Product):ProductResponse{
        return ProductResponse().apply {
            id=product.id
            name=product.name
            description=product.description
            price=product.price
        }
    }
}