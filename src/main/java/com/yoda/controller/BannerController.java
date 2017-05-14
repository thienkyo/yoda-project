package com.yoda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.UtilityConstant;
import com.yoda.models.Banner;
import com.yoda.services.BannerService;

@RestController
@RequestMapping("/banner")
public class BannerController {
	@Autowired
    private BannerService bannerRepo;
	
	@RequestMapping("")
    public List<Banner> getActiveBanner() {
        return bannerRepo.findAllByStatus(UtilityConstant.ACTIVE_STATUS);
    }
}
