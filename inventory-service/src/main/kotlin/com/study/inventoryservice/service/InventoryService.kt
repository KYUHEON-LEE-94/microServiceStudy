package com.study.inventoryservice.service

import com.study.inventoryservice.repositroy.InventoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *packageName    : com.study.inventoryservice.service
 * fileName       : InventoryService
 * author         : LEE KYUHEON
 * date           : 2024-07-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-07        LEE KYUHEON       최초 생성
 */
@Service
class InventoryService @Autowired constructor(
    private var inventoryRepository: InventoryRepository
){

    @Transactional(readOnly = true)
    fun isInStock(skuCode:String):Boolean{
        return inventoryRepository.findBySkuCode().isPresent
    }
}