package com.study.microservcie.controller

import com.study.microservcie.dto.ProductRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

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
class ProductController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(@RequestBody productRequest: ProductRequest) {
        
    }
}