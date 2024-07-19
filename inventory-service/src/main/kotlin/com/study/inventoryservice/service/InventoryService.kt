package com.study.inventoryservice.service

import com.study.inventoryservice.dto.InventoryResponse
import com.study.inventoryservice.repositroy.InventoryRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import lombok.SneakyThrows
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
private val logger = KotlinLogging.logger{}

@Service
class InventoryService @Autowired constructor(
    private var inventoryRepository: InventoryRepository
){

    @Transactional(readOnly = true)
    @SneakyThrows /*Checked Exception를 메서드 시그니처에 명시적으로 선언하지 않고도 예외를 던질 수 있음*/
    fun isInStock(skuCode:List<String>):List<InventoryResponse>{

        logger.info { "Wait! Started" }
        //Thread.sleep(10000)
        logger.info { "Wait Ended" }

        return inventoryRepository.findBySkuCodeIn(skuCode)
            .map { inventory ->  InventoryResponse().apply {
                this.isInStock=inventory.quantity>0
                this.skuCode=inventory.skuCode

                }
            }

    }
}