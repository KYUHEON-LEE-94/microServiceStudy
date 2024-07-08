package com.study.orderservice.dto

import java.math.BigDecimal

/**
 *packageName    : com.study.productservice.dto
 * fileName       : OrderLinesImtesDto
 * author         : LEE KYUHEON
 * date           : 2024-07-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-06        LEE KYUHEON       최초 생성
 */
data class OrderLinesImtesDto (
    var id:Long,
    var skuCode:String,
    var price: BigDecimal,
    var quantity:Int
)