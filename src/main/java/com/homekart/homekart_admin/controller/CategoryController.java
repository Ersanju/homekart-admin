package com.homekart.homekart_admin.controller;

import com.homekart.homekart_admin.model.Category;
import com.homekart.homekart_admin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/admin/category")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add-categories")
    public String addMultipleCategories(@RequestBody List<Category> categories)
            throws ExecutionException, InterruptedException {
        return categoryService.addMultipleCategories(categories);
    }
}
