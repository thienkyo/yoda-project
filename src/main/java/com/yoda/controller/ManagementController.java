package com.yoda.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yoda.UtilityConstant;
import com.yoda.models.*;
import com.yoda.services.*;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/mgnt")
public class ManagementController {
	@Autowired private ProductsService productService;
	@Autowired private CategoriesService cateService;
	@Autowired private OrdersService orderService;
	@Autowired private ArticleService articleService;
	@Autowired private BannerService bannerService;
	@Autowired private Environment env;
	
//////////////////////////product section///////////////////////
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
	public ManagementResponse updateProducts(@RequestBody final Products product) throws ServletException {
		product.setModDate(new Date());
		productService.save(product);
		return new ManagementResponse("upsert_product_success");
	}
	
////////////////////////////category section//////////////////////////
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "upsertCategory", method = RequestMethod.POST)
	public Categories upsertCategory(@RequestBody final Categories cate) throws ServletException {
		//cate.setMod_date(new Date());
		cateService.save(cate);
		return cate;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deleteCategory", method = RequestMethod.POST)
	public ManagementResponse deleteCategory(@RequestBody final Categories cate) throws ServletException {
		//cate.setMod_date(new Date());
		cateService.delete(cate);;
		return new ManagementResponse("delete_category_success");
	}
	
/////////////////////////Orders section///////////////////////
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getOrdersForMgnt/{amount}", method = RequestMethod.GET)
	public List<Orders> getOrdersForMgnt(@PathVariable final int amount) throws ServletException {
		List<Orders> orderList = new ArrayList<Orders>();
		
		if(amount==20){
			orderList =  orderService.findFirst20ByOrderByOrderIdDesc();
		}else{
			orderList = orderService.findAllByOrderByOrderIdDesc();
		}
		
		return orderList;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deleteOrder", method = RequestMethod.POST)
	public ManagementResponse deleteOrder(@RequestBody final Orders order) throws ServletException {
		//cate.setMod_date(new Date());
		orderService.delete(order);
		return new ManagementResponse("delete_order_success");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "updateOrderStatus/{orderId}/{status}", method = RequestMethod.GET)
	public ManagementResponse updateOrderStatus(@PathVariable final int orderId, @PathVariable final int status) throws ServletException {
		Orders order = orderService.findOne(orderId);
		List<Products> products = new ArrayList<>();
		for(OrderDetails od : order.getOrderDetails()){
			Products prod = od.getProduct();
			if(status == 21){
				prod.setQuantity(prod.getQuantity() - od.getQuantity());
			}else if(status == 20){
				prod.setQuantity(prod.getQuantity() + od.getQuantity());
			}
			
			products.add(prod);
		}
		productService.save(products);
		order.setStatus(status);
		orderService.save(order);
		return new ManagementResponse("upsert_order_success");
	}
	
////////////////////////Article/blog section //////////////////////
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getArticlesForMgnt/{amount}", method = RequestMethod.GET)
	public List<Article> getArticlesForMgnt(@PathVariable final int amount) throws ServletException {
		List<Article> articleList = new ArrayList<Article>();
		if(amount==20){
			articleList =  articleService.findFirst20ByOrderByArticleIdDesc();
		}else{
			articleList = articleService.findAllByOrderByArticleIdDesc();
		}
		return articleList;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "upsertArticle", method = RequestMethod.POST)
	public ManagementResponse updateArticle(@RequestBody final Article article) throws ServletException {
		article.setModDate(new Date());
		articleService.save(article);
		return new ManagementResponse("upsert_article_success");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getArticleById/{articleId}", method = RequestMethod.GET)
	public Article getArticleById(@PathVariable final int articleId) throws ServletException {
		return	articleService.findByArticleId(articleId);
	}
///////////////////////////////Upload section /////////////////////////
	@RequestMapping(value = "/uploadfile2", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile uploadfile) {
    	String directory = env.getProperty("yoda.uploadedFiles.thumbnail");
    	return UtilityConstant.savefile(directory,uploadfile);
    } // method uploadFile
    
    @RequestMapping(value = "/uploadfile3", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadFileProductImage(@RequestParam("file") MultipartFile uploadfile) {
    	String directory = env.getProperty("yoda.uploadedFiles.productDetail");
    	return UtilityConstant.savefile(directory,uploadfile);
    }
	
	@RequestMapping(value = "/uploadfile4", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadFileArticleImage(@RequestParam("file") MultipartFile uploadfile) {
    	String directory = env.getProperty("yoda.uploadedFiles.article");
    	return UtilityConstant.savefile(directory,uploadfile);
    }
	
	@RequestMapping(value = "/uploadfile5", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadFileBannerImage(@RequestParam("file") MultipartFile uploadfile) {
    	String directory = env.getProperty("yoda.uploadedFiles.Banner");
    	return UtilityConstant.savefile(directory,uploadfile);
    }
/////////////////////////////Banner section/////////////////////////////
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getBannerForMgnt", method = RequestMethod.GET)
	public List<Banner> getBanner() throws ServletException {
		return (List<Banner>) bannerService.findAll();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "upsertBanner", method = RequestMethod.POST)
	public ManagementResponse updateBanner(@RequestBody final Banner banner) throws ServletException {
		banner.setModDate(new Date());
		bannerService.save(banner);
		return new ManagementResponse("upsert_banner_success");
	}
	
/////////////////////////////////////////////////////////////////////////
	
	
	@SuppressWarnings("unused")
    private static class ManagementResponse {
    	public String replyStr;
        public ManagementResponse(final String replyStr) {
            this.replyStr = replyStr;
        }
    }
}
