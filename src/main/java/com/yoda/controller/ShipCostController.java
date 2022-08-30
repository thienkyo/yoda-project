package com.yoda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.models.ShipCost;
import com.yoda.services.ShipCostService;


@RestController
@RequestMapping("/shipcost")
public class ShipCostController {
	@Autowired
    private ShipCostService shipCostRepo;
	
	@RequestMapping("")
    public List<ShipCost> getShipCost() {//@RequestParam(value = "status") 
        return (List<ShipCost>) shipCostRepo.findAll();
    }

    @RequestMapping("/active")
    public List<ShipCost> getActiveShipCost() {
        return (List<ShipCost>) shipCostRepo.findAllByStatus(1);
    }
}
