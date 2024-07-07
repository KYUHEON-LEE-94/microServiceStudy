package com.study.inventoryservice.model

import jakarta.persistence.*

/**
 *packageName    : com.study.inventoryservice.model
 * fileName       : Inventory
 * author         : LEE KYUHEON
 * date           : 2024-07-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-07        LEE KYUHEON       최초 생성
 */
@Entity
@Table(name="t_inventory")
data class Inventory (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0,
    var skuCode:String = "",
    var quantity:Int = 0
)