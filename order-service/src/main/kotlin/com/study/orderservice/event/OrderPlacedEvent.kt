package com.study.orderservice.event

/**
 *packageName    : com.study.orderservice.event
 * fileName       : OrderPlacedEvent
 * author         : LEE KYUHEON
 * date           : 2024-07-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-19        LEE KYUHEON       최초 생성
 */

data class OrderPlacedEvent (
    var orderNumber:String = ""


        )