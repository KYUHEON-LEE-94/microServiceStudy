package com.study.microservcie.repository

import com.study.microservcie.model.Product
import org.springframework.data.mongodb.repository.MongoRepository

/**
 *packageName    : com.study.microservcie.repository
 * fileName       : ProductRepository
 * author         : LEE KYUHEON
 * date           : 2024-07-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-02        LEE KYUHEON       최초 생성
 */
interface ProductRepository: MongoRepository<Product, String>{
}