package com.study.productservice.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

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
@Entity
@Table(name="t_orders")
data class Order (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var orderNumber:String ,
    var orderLineItemsList:List<OrderLineItems>
)
