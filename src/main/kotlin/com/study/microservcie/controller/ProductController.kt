package com.study.microservcie.controller

import com.study.microservcie.dto.ProductRequest
import com.study.microservcie.dto.ProductResponse
import com.study.microservcie.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 *packageName    : com.study.microservcie.controller
 * fileName       : ProductController
 * author         : LEE KYUHEON
 * date           : 2024-07-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-02        LEE KYUHEON       최초 생성
 */
@RestController
@RequestMapping("/api/product")
class ProductController @Autowired constructor(
    private val productService: ProductService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(@RequestBody productRequest: ProductRequest) {
        productService.createProduct(productRequest)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllProducts():List<ProductResponse>{
        return productService.getAllProducts()
    }
}