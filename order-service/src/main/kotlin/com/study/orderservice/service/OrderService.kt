package com.study.orderservice.service

import com.study.orderservice.dto.InventoryResponse
import com.study.orderservice.dto.OrderLinesImtesDto
import com.study.orderservice.dto.OrderRequest
import com.study.orderservice.dto.OrderResponse
import com.study.orderservice.event.OrderPlacedEvent
import com.study.orderservice.model.Order
import com.study.orderservice.model.OrderLineItems
import com.study.orderservice.repository.OrderRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import io.micrometer.tracing.Tracer
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient
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

private val logger = KotlinLogging.logger{}

@Service
@Transactional
class OrderService(
    private val orderRepository: OrderRepository,
    private val webClient: WebClient.Builder,
    private val tracer: Tracer,
    private val kafkaTemplate: KafkaTemplate<String, OrderPlacedEvent>
    )
{

    fun getOrderList():List<OrderResponse>{
        return orderRepository.findAll().map { order ->
            mapToDto(order)
        }

    }

    fun placeOrder(orderRequest: OrderRequest):String{
        var order = Order(

            orderNumber = UUID.randomUUID().toString(),

            orderLineItemsList = orderRequest.orderLineItemsDtoList.map { orderLinesItem ->
                mapToDto(orderLinesItem)
            }
        )

        val skuCodes = order.orderLineItemsList.map(OrderLineItems::skuCode).toTypedArray()
        val inventoryServiceLookup = tracer.nextSpan().name("InventoryServiceLookup")

        try {
            tracer.withSpan(inventoryServiceLookup.start()).use { _ ->
                // Inventory service 호출, 재고가 있으면 주문 진행
                val inventoryResponseArray = webClient.build().get()
                    .uri { uriBuilder ->
                        uriBuilder
                            .path("/api/inventory")
                            .queryParam("skuCode", *skuCodes) // 가변인자 전달
                            .build()
                    }
                    .retrieve()
                    .bodyToMono(Array<InventoryResponse>::class.java)
                    .block()

                val allProductsInStock = inventoryResponseArray?.all { it.isInStock }

                if (allProductsInStock == true) {
                    orderRepository.save(order)
                    kafkaTemplate.send("notificationTopic", OrderPlacedEvent().apply { orderNumber= order.orderNumber} )
                    logger.info { "Order placed successfully with order number: ${order.orderNumber}" }
                    return "Order Placed Successfully"
                } else {
                    throw IllegalArgumentException("Product is not in stock")
                }
            }
        } catch (ex: Exception) {
            logger.error(ex) { "Error placing order" }
            throw ex
        } finally {
            inventoryServiceLookup.end()
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

    fun mapToDto(order: Order):OrderResponse{
        val apply = OrderResponse().apply {
            id = order.id
            orderNumber = order.orderNumber
            orderLineItemsList = order.orderLineItemsList
        }
        return apply
    }
}