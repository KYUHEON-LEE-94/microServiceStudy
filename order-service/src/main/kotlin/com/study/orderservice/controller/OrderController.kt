package com.study.orderservice.controller

import com.study.orderservice.dto.OrderRequest
import com.study.orderservice.service.OrderService
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
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
class OrderController @Autowired constructor(
    private var orderService: OrderService
){
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory")
    fun placeOrder(@RequestBody orderRequest: OrderRequest):String{
        orderService.placeOrder(orderRequest)
        return "Order Placed Successfully"
    }
}