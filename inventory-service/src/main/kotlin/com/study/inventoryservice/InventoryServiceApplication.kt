package com.study.inventoryservice

import com.study.inventoryservice.model.Inventory
import com.study.inventoryservice.repositroy.InventoryRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableDiscoveryClient
class InventoryServiceApplication{
    @Bean
    fun loadData(inventoryRepository: InventoryRepository):CommandLineRunner {
        return CommandLineRunner {
            // Create some initial data
            val item1 = Inventory(
                skuCode = "iphone_14",
                quantity = 100,
            )
            val item2 = Inventory(
                skuCode = "iphone_13",
                quantity = 0,
            )

            inventoryRepository.save(item1)
            inventoryRepository.save(item2)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<InventoryServiceApplication>(*args)
}

