package com.study.productservice.service

import com.study.productservice.dto.OrderLinesImtesDto
import com.study.productservice.dto.OrderRequest
import com.study.productservice.model.Order
import com.study.productservice.model.OrderLineItems
import com.study.productservice.repository.OrderRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 *packageName    : com.study.productservice.service
 * fileName       : OrerService
 * author         : LEE KYUHEON
 * date           : 2024-07-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-06        LEE KYUHEON       최초 생성
 */
@Service
@Slf4j
class OrderService @Autowired constructor(
    private var orderRepository: OrderRepository)
{

    fun placeOrder(orderRequest: OrderRequest){
        var order:Order = Order(

            orderNumber = UUID.randomUUID().toString(),

            orderLineItemsList = orderRequest.orderLineItemsDtoList.stream()
                .map{orderLinesItem -> mapToDto(orderLinesItem)}
                .toList()
        )

        orderRepository.save(order)
    }

    fun mapToDto(orderLinesItemsDto: OrderLinesImtesDto):OrderLineItems{
        val apply = OrderLineItems().apply {
            price = orderLinesItemsDto.price
            quantity = orderLinesItemsDto.quantity
            skuCode = orderLinesItemsDto.skuCode
        }
        return apply
    }
}