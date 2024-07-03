package com.study.microservcie.dto

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
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var price: BigDecimal = BigDecimal.ZERO
)