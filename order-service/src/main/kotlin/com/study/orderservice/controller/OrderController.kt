package com.study.orderservice.controller

import com.study.orderservice.dto.OrderRequest
import com.study.orderservice.service.OrderService
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import io.github.resilience4j.timelimiter.annotation.TimeLimiter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException
import java.util.concurrent.CompletableFuture

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
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name= "inventory")
    @Retry(name="inventory")
    fun placeOrder(@RequestBody orderRequest: OrderRequest):CompletableFuture<String>{
        return CompletableFuture.supplyAsync {
            orderService.placeOrder(orderRequest)
        }
    }

    fun fallbackMethod(orderRequest: OrderRequest, runtimeException: RuntimeException):CompletableFuture<String>{
        return CompletableFuture.supplyAsync { "Ops! Something went wrong, please order after some time!" }
    }
}