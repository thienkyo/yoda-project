package com.yoda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.models.Categories;
import com.yoda.services.CategoriesRepository;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
	@Autowired
    private CategoriesRepository cateRepo;
	
	@RequestMapping("/{status}")
    public List<Categories> getCategories(@PathVariable int status) {//@RequestParam(value = "status") 
        return cateRepo.findByStatus(status);
    }
	
	@RequestMapping("/count")
    public String count() {//@RequestParam(value = "status") 
        return "count: "+cateRepo.count();
    }
	
	@RequestMapping(value="/id/{id}",method = RequestMethod.GET)
    public Categories getOneCategory(@PathVariable int id) {
		return cateRepo.findByCategoryId(id);	
    }
}
