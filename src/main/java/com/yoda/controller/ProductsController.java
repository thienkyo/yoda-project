package com.yoda.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Products> getProductByStatus(@PathVariable final int status) {//@RequestParam(value = "status") 
        return prodRepo.findByStatusOrderByModDateDesc(status);
    }
	
	@RequestMapping("first6")
    public List<Products> getFisrt6Product() {//@RequestParam(value = "status") 
        return prodRepo.findFirst6ByStatusOrderByModDateDesc(UtilityConstant.ACTIVE_STATUS);
    }
	
	@RequestMapping("homeProduct")
    public List<Products> getHomeProduct() {//@RequestParam(value = "status") 
        return prodRepo.findFirst9ByStatusOrderByModDateDesc(UtilityConstant.ACTIVE_STATUS);
    }
	
	@RequestMapping("count")
    public String count() {//@RequestParam(value = "status") 
        return "count: "+prodRepo.count();
    }
	
	@RequestMapping(value="id/{id}",method = RequestMethod.GET)
    public Products getOneProduct(@PathVariable final int id) {
		return prodRepo.findByProdId(id);
    }
	
	@RequestMapping(value="activeId/{id}",method = RequestMethod.GET)
    public Products getOneActiveProduct(@PathVariable final int id) {
		return prodRepo.findByProdIdAndStatus(id, UtilityConstant.ACTIVE_STATUS);
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
	
	@RequestMapping(value = "getRandomProduct", method = RequestMethod.GET)
    public List<Products> getRandomProduct() {
		List<Products> productList = prodRepo.findByStatusOrderByModDateDesc(UtilityConstant.ACTIVE_STATUS); 
		Random ran = new Random();
		List<Products> list = new ArrayList<>();
		int x =0;
		for(int i =0 ; i<6; i++){
			x = ran.nextInt(productList.size());
			if(!list.contains(productList.get(x))){
				list.add(productList.get(x));
			}
		}
        return list;
    }
	
	@RequestMapping(value = "getProductPage/{cateId}/{pageNumber}", method = RequestMethod.GET)
	public Page<Products> getProductPage(@PathVariable Integer cateId, @PathVariable Integer pageNumber) {
        //Pageable request = new PageRequest(pageNumber - 1, UtilityConstant.PRODUCT_PAGE_SIZE, Sort.Direction.DESC, "ProdId");
        Pageable request = PageRequest.of(pageNumber - 1, UtilityConstant.PRODUCT_PAGE_SIZE, Sort.Direction.DESC, "ProdId");
        Categories cate = new Categories(cateId);
        return prodRepo.findByCategoryAndStatus(cate, UtilityConstant.ACTIVE_STATUS, request);
    }
}
