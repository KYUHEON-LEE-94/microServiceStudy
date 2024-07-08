package com.study.orderservice.dto

/**
 *packageName    : com.study.inventoryservice.dto
 * fileName       : InventoryResponse
 * author         : LEE KYUHEON
 * date           : 2024-07-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-08        LEE KYUHEON       최초 생성
 */
data class InventoryResponse (
    var skuCode:String = "",
    var isInStock:Boolean = true
)