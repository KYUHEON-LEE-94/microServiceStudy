package com.study.inventoryservice.controller

import com.study.inventoryservice.dto.InventoryResponse
import com.study.inventoryservice.service.InventoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 *packageName    : com.study.inventoryservice.controller
 * fileName       : InvenrtoryController
 * author         : LEE KYUHEON
 * date           : 2024-07-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-07        LEE KYUHEON       최초 생성
 */
@RestController
@RequestMapping("/api/inventory")
class InventoryController @Autowired constructor(
    private var inventoryService: InventoryService
){

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun isInStock(@RequestParam skuCode:List<String>):List<InventoryResponse>{

        return inventoryService.isInStock(skuCode)
    }

}