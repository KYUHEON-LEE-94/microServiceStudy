package com.study.productservice.model

import jakarta.persistence.*
import java.math.BigDecimal

/**
 * @Description : OrderLineItems.java
 * @author      : heon
 * @since       : 2024-07-05
 *
 * <pre>
 *
 * << 개정이력(Modification Information) >>
 *
 * ===========================================================
 * 수정인          수정자      수정내용
 * ----------    ----------    --------------------
 * 2024-07-05       heon         최초 생성
 *
 * <pre>
 */
@Entity
@Table(name="t_order_line_items")
data class OrderLineItems (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0,
    var skuCode:String = "",
    var price:BigDecimal = BigDecimal.ZERO,
    var quantity:Int = 0
)