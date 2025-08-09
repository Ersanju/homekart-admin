package com.homekart.homekart_admin.controller;

import com.homekart.homekart_admin.model.AppReview;
import com.homekart.homekart_admin.service.AppReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/admin/app-reviews")
@CrossOrigin(origins = "*")
public class AppReviewController {

    @Autowired
    private AppReviewService appReviewService;

    @PostMapping("/add")
    public String addAppReviews(@RequestBody List<AppReview> reviews) throws ExecutionException, InterruptedException {
        return appReviewService.insertAppReviews(reviews);
    }

    @GetMapping("/all")
    public List<AppReview> getAllReviews() throws ExecutionException, InterruptedException {
        return appReviewService.getAllAppReviews();
    }

}
