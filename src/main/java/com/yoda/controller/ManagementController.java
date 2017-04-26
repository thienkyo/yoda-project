package com.yoda.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.UtilityConstant;
import com.yoda.models.Categories;
import com.yoda.models.Products;
import com.yoda.services.CategoriesService;
import com.yoda.services.ProductsService;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/mgnt")
public class ManagementController {
	@Autowired private ProductsService productService;
	@Autowired private CategoriesService cateService;
	
	/////////////product sectiob/////////////
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "get20Products", method = RequestMethod.GET)
	public List<Products> get20Products(final HttpServletRequest request) throws ServletException {
		List<Products> productList = new ArrayList<Products>();
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			productList = productService.findFirst20ByOrderByProdIdDesc();
		}
		return productList;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getAllProducts", method = RequestMethod.GET)
	public List<Products> getAllProducts(final HttpServletRequest request) throws ServletException {
		List<Products> productList = new ArrayList<Products>();
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			productList = (List<Products>) productService.findAll();
		}
		return productList;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getProductsForMgnt/{cateId}/{amount}", method = RequestMethod.GET)
	public List<Products> getProductsForMgnt(@PathVariable final int cateId,@PathVariable final int amount, final HttpServletRequest request) throws ServletException {
		List<Products> productList = new ArrayList<Products>();
		if(cateId==0){
			if(amount==20){
				productList =  productService.findFirst20ByOrderByProdIdDesc();
			}else{
				productList = (List<Products>) productService.findAll();
			}
		}else{
			Categories cate = new Categories(cateId);
			if(amount==20){
				productList =  productService.findFirst20ByCategoryOrderByProdIdDesc(cate);
			}else{
				productList =  productService.findAllByCategoryOrderByProdIdDesc(cate);
			}
		}
		return productList;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "upsertProduct", method = RequestMethod.POST)
	public AuthenticationResponse updateProducts(@RequestBody final Products product) throws ServletException {
		product.setModDate(new Date());
		productService.save(product);
		return new AuthenticationResponse("upsert_product_success");
	}
	
	//////////////category section
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getAllCategories", method = RequestMethod.GET)
	public List<Categories> getAllCategories(final HttpServletRequest request) throws ServletException {
		List<Categories> cateList = new ArrayList<Categories>();
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			cateList = (List<Categories>) cateService.findAll();
		}
		return cateList;
	}
	
	@SuppressWarnings("unused")
    private static class AuthenticationResponse {
    	public String replyStr;
        public AuthenticationResponse(final String replyStr) {
            this.replyStr = replyStr;
        }
    }
}
