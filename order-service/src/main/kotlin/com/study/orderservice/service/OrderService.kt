package com.study.orderservice.service

import com.study.orderservice.dto.OrderLinesImtesDto
import com.study.orderservice.dto.OrderRequest
import com.study.orderservice.model.Order
import com.study.orderservice.model.OrderLineItems
import com.study.orderservice.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriBuilder
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
@Transactional
class OrderService @Autowired constructor(
    private var orderRepository: OrderRepository,
    private var webClient: WebClient
    )
{

    fun placeOrder(orderRequest: OrderRequest){
        var order:Order = Order(

            orderNumber = UUID.randomUUID().toString(),

            orderLineItemsList = orderRequest.orderLineItemsDtoList.map { orderLinesItem ->
                mapToDto(orderLinesItem)
            }
        )

        val skuCodes = order.orderLineItemsList.map(OrderLineItems::skuCode).toTypedArray()

        //Inventory service 호출, 재고가 있으면 주문 진행
        val result = webClient.get()
            .uri{
                uriBuilder ->
                uriBuilder.path("htp://lcalhost:8082/api/inventory")
                    .queryParam("skuCode", skuCodes)
                    .build()
            }
            .retrieve()
            .bodyToMono(Boolean::class.java)
            .block()

        if(result == true){
            orderRepository.save(order)
        }else{
            throw IllegalArgumentException("Product is not in stock")
        }
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