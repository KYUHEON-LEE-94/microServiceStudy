package com.study.inventoryservice.repositroy

import com.study.inventoryservice.model.Inventory
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

/**
 *packageName    : com.study.inventoryservice.repositroy
 * fileName       : InventoryRepository
 * author         : LEE KYUHEON
 * date           : 2024-07-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-07        LEE KYUHEON       최초 생성
 */
interface InventoryRepository:JpaRepository<Inventory,Long> {
    fun findBySkuCode():Optional<Inventory>
}