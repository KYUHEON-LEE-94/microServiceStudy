package com.study.orderservice.dto

import com.study.orderservice.model.OrderLineItems
import jakarta.persistence.*

/**
 * @Description : Order.java
 * @author      : heon
 * @since       : 2024-07-04
 *
 * <pre>
 *
 * << 개정이력(Modification Information) >>
 *
 * ===========================================================
 * 수정인          수정자      수정내용
 * ----------    ----------    --------------------
 * 2024-07-04       heon         최초 생성
 *
 * <pre>
 */

data class OrderResponse (

    var id: Long = 0,
    var orderNumber:String = "",
    var orderLineItemsList:List<OrderLineItems> = ArrayList()
)
