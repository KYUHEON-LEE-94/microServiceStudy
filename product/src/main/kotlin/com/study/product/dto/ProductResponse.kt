package com.study.product.dto

import java.math.BigDecimal

/**
 *packageName    : com.study.microservcie.dto
 * fileName       : ProductResponse
 * author         : LEE KYUHEON
 * date           : 2024-07-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-02        LEE KYUHEON       최초 생성
 */
data class ProductResponse(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var price: BigDecimal = BigDecimal.ZERO
)