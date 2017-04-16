package com.yoda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.UtilityConstant;
import com.yoda.models.Products;
import com.yoda.services.ProductsService;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/mgnt")
public class ManagementController {
	@Autowired private ProductsService productService;
	
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
}
