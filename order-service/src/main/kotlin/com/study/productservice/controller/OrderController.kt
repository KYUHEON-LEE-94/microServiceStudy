package com.study.productservice.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *packageName    : com.study.productservice.controller
 * fileName       : OrderController
 * author         : LEE KYUHEON
 * date           : 2024-07-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-06        LEE KYUHEON       최초 생성
 */
@RestController
@RequestMapping("/api/order")
class OrderController {
    @PostMapping
    fun placeOrder():String{
        return "Order Placed Successfully"
    }
}