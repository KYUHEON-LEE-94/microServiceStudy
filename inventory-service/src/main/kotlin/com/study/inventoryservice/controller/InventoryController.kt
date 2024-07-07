package com.study.inventoryservice.controller

import com.study.inventoryservice.service.InventoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

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


    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    fun isInStock(@PathVariable("sku-code") skuCode:String):Boolean{
        return inventoryService.isInStock(skuCode)
    }

}