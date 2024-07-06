package com.study.productservice.dto

/**
 *packageName    : com.study.productservice.dto
 * fileName       : OrderRequest
 * author         : LEE KYUHEON
 * date           : 2024-07-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-06        LEE KYUHEON       최초 생성
 */
data class OrderRequest (
    var orderLineItemsDtoList: List<OrderLinesImtesDto>
)