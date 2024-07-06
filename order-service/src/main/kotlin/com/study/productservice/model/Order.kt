package com.study.productservice.model

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
@Entity
@Table(name="t_orders")
data class Order (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var orderNumber:String ,
    /*
    * cascade: 부모 엔티티에서 자식 엔티티로 어떤 작업이 전파될지를 정의
    * [CascadeType.ALL]: 모든 작업(영속화, 병합, 제거 등)이 자식 엔티티로 전파 즉, 부모 엔티티가 저장, 업데이트, 삭제될 때 자식 엔티티도 함께 처리
    * */
    @OneToMany(cascade =[CascadeType.ALL])
    var orderLineItemsList:List<OrderLineItems>
)
