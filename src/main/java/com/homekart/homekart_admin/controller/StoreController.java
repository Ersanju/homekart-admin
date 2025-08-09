package com.homekart.homekart_admin.controller;

import com.homekart.homekart_admin.model.PizzaModel;
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

    // Add pizza menu items to a specific store
    @PostMapping("/{storeId}/menu/pizza")
    public String addPizzaMenu(
            @PathVariable String storeId,
            @RequestBody List<PizzaModel> pizzas
    ) throws ExecutionException, InterruptedException {
        return storeService.addPizzas(storeId, pizzas);
    }

    // Get pizza menu items for a specific store
    @GetMapping("/{storeId}/menu/pizza")
    public List<PizzaModel> getPizzaMenu(@PathVariable String storeId)
            throws ExecutionException, InterruptedException {
        return storeService.getAllPizzas(storeId);
    }
}
