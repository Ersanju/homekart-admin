package com.homekart.homekart_admin.controller;

import com.homekart.homekart_admin.model.Store;
import com.homekart.homekart_admin.model.StoreMenuItem;
import com.homekart.homekart_admin.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/admin/stores")
@CrossOrigin(origins = "*")
public class StoreController {

    @Autowired
    private StoreService storeService;

    // Add multiple stores
    @PostMapping("/addStore")
    public String addStores(@RequestBody List<Store> stores) throws ExecutionException, InterruptedException {
        return storeService.insertStores(stores);
    }

    // Get all stores
    @GetMapping("/allStore")
    public List<Store> getAllStores() throws ExecutionException, InterruptedException {
        return storeService.getAllStores();
    }

    // Add menu items to a specific store
    @PostMapping("/{storeId}/menu")
    public String addMenuItems(
            @PathVariable String storeId,
            @RequestBody List<StoreMenuItem> menuItems
    ) throws ExecutionException, InterruptedException {
        return storeService.insertStoreMenu(storeId, menuItems);
    }

    // Get menu items for a specific store
    @GetMapping("/{storeId}/menu")
    public List<StoreMenuItem> getMenuItems(@PathVariable String storeId)
            throws ExecutionException, InterruptedException {
        return storeService.getStoreMenu(storeId);
    }
}
