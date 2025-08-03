package com.homekart.homekart_admin.controller;

import com.homekart.homekart_admin.model.Banner;
import com.homekart.homekart_admin.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/admin/banner")
@CrossOrigin(origins = "*")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @PostMapping("/add-banners")
    public String addBanners(@RequestBody List<Banner> banners) throws ExecutionException, InterruptedException {
        return bannerService.addMultipleBanners(banners);
    }

    @GetMapping("/get-banners")
    public List<Banner> getBanners() throws ExecutionException, InterruptedException {
        return bannerService.getAllBanners();
    }
}
