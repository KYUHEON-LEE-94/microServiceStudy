package com.study.orderservice.repository

import com.study.orderservice.model.Order
import org.springframework.data.jpa.repository.JpaRepository

/**
 *packageName    : com.study.productservice.repository
 * fileName       : OrderRepository
 * author         : LEE KYUHEON
 * date           : 2024-07-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-06        LEE KYUHEON       최초 생성
 */
interface OrderRepository:JpaRepository<Order,Long> {

}