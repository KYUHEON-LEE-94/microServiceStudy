package com.study.microservcie.model


import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

/**
 *packageName    : com.study.microservcie.model
 * fileName       : Product
 * author         : LEE KYUHEON
 * date           : 2024-07-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-02        LEE KYUHEON       최초 생성
 */
@Document(value = "product")
data class Product(
    @Id
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var price: BigDecimal = BigDecimal.ZERO
)