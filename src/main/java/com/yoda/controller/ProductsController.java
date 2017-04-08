package com.yoda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.UtilityConstant;
import com.yoda.models.Categories;
import com.yoda.models.Members;
import com.yoda.models.Products;
import com.yoda.services.ProductsService;;

@RestController
@RequestMapping("/products")
public class ProductsController {
	@Autowired
    private ProductsService prodRepo;
	
	@RequestMapping("{status}")
    public List<Products> getProductByStatus(@PathVariable int status) {//@RequestParam(value = "status") 
        return prodRepo.findByStatusOrderByModDateDesc(status);
    }
	
	@RequestMapping("first6")
    public List<Products> getFisrt6Product() {//@RequestParam(value = "status") 
        return prodRepo.findFirst6ByStatusOrderByModDateDesc(UtilityConstant.ACTIVE_STATUS);
    }
	
	
	@RequestMapping("count")
    public String count() {//@RequestParam(value = "status") 
        return "count: "+prodRepo.count();
    }
	
	@RequestMapping(value="id/{id}",method = RequestMethod.GET)
    public Products getOneProduct(@PathVariable int id) {
		return prodRepo.findByProdId(id);
    }

	@RequestMapping(value = "categoryId/{id}", method = RequestMethod.GET)
    public List<Products> hello(@PathVariable int id) {
		Categories cate = new Categories(id);
        return prodRepo.findByCategoryAndStatus(cate, UtilityConstant.ACTIVE_STATUS);
    }
	
	
	@RequestMapping(value = "getProductForCart", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Products> getProductForCart(@RequestBody final List<Integer> prodIds) {
        return prodRepo.findByProdIdInAndStatus(prodIds, UtilityConstant.ACTIVE_STATUS);
    }
}
