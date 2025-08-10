package com.homekart.homekart_admin.controller;

import com.homekart.homekart_admin.model.store.PizzaModel;
import com.homekart.homekart_admin.model.store.Store;
import com.homekart.homekart_admin.model.StoreMenuItem;
import com.homekart.homekart_admin.model.store.StoreItem;
import com.homekart.homekart_admin.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return storeService.addStores(stores);
    }

    // Get all stores
    @GetMapping("/allStore")
    public List<Store> getAllStores() throws ExecutionException, InterruptedException {
        return storeService.getAllStores();
    }

    // Add store items
    @PostMapping("/{storeId}/items")
    public ResponseEntity<String> addItemsToStore(
            @PathVariable String storeId,
            @RequestBody List<StoreItem> items
    ) {
        try {
            String message = storeService.addItemsToStore(storeId, items);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{storeId}/items")
    public ResponseEntity<List<StoreItem>> getStoreItems(@PathVariable String storeId) {
        try {
            List<StoreItem> items = storeService.getItemsByStore(storeId);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }





}
