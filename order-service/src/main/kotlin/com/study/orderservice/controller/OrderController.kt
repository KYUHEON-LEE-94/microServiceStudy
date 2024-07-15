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
import java.lang.RuntimeException

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
class OrderController (
    private var orderService: OrderService
){
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    /**inventory라는 이름의 Circuit Breaker를 사용,
     * 만약 placeOrder 메서드에서 예외가 발생하면, fallbackMethod 메서드를 호출하여 예외 상황을 처리합니다.**/
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    fun placeOrder(@RequestBody orderRequest: OrderRequest):String{
        orderService.placeOrder(orderRequest)
        return "Order Placed Successfully"
    }

    fun fallbackMethod(orderRequest: OrderRequest, runtimeException: RuntimeException):String{
        return "Ops! Something went wrong, please order after some time!"
    }
}