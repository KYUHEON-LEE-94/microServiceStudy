package com.study.microservcie.dto

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import java.math.BigDecimal

/**
 *packageName    : com.study.microservcie.dto
 * fileName       : ProductRequest
 * author         : LEE KYUHEON
 * date           : 2024-07-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-02        LEE KYUHEON       최초 생성
 */

data class ProductRequest(
    @Id
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var price: BigDecimal = BigDecimal.ZERO
)