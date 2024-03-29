package com.yoda.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
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
	@Autowired private MemberService memberService;
	@Autowired private ShipCostService shipCostService;
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
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
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
		}
		
		return productList;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "upsertProduct", method = RequestMethod.POST)
	public ManagementResponse updateProducts(@RequestBody final Products product, final HttpServletRequest request) throws ServletException {
		product.setModDate(new Date());
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			productService.save(product);
		}
		return new ManagementResponse("upsert_product_success");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getProductById/{prodId}", method = RequestMethod.GET)
	public Products getProductById(@PathVariable final int prodId, final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			return	productService.findByProdId(prodId);
		}
		return	null;
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
	public Categories upsertCategory(@RequestBody final Categories cate,final HttpServletRequest request) throws ServletException {
		cate.setMod_date(new Date());
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			cateService.save(cate);
		}
		return cate;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deleteCategory", method = RequestMethod.POST)
	public ManagementResponse deleteCategory(@RequestBody final Categories cate, final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			cateService.delete(cate);
		}
		return new ManagementResponse("delete_category_success");
	}
	
/////////////////////////Orders section///////////////////////
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getOrdersForMgnt/{amount}", method = RequestMethod.GET)
	public List<Orders> getOrdersForMgnt(@PathVariable final int amount, final HttpServletRequest request) throws ServletException {
		List<Orders> orderList = new ArrayList<Orders>();
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			if(amount==20){
				orderList =  orderService.findFirst20ByOrderByOrderIdDesc();
			}else{
				orderList = orderService.findAllByOrderByOrderIdDesc();
			}
		}
		return orderList;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deleteOrder", method = RequestMethod.POST)
	public ManagementResponse deleteOrder(@RequestBody final Orders order, final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			orderService.delete(order);
		}
		return new ManagementResponse("delete_order_success");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "updateOrderStatus/{orderId}/{status}", method = RequestMethod.GET)
	public ManagementResponse updateOrderStatus(@PathVariable final int orderId, @PathVariable final int status, final HttpServletRequest request) throws ServletException {
	//	Orders order = orderService.findOne(orderId);
		Orders order = orderService.findById(orderId).orElse(null);
		List<Products> products = new ArrayList<>();
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			for(OrderDetails od : order.getOrderDetails()){
				Products prod = od.getProduct();
				if(status == 21){
					prod.setQuantity(prod.getQuantity() - od.getQuantity());
				}else if(status == 20){
					prod.setQuantity(prod.getQuantity() + od.getQuantity());
				}
				
				products.add(prod);
			}
		//	productService.save(products);
			productService.saveAll(products);
			order.setStatus(status);
			orderService.save(order);
		}
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "uploadfile2", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile uploadfile, @RequestParam("oldName") String oldName, final HttpServletRequest request) {
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			String directory = env.getProperty("yoda.uploadedFiles.thumbnail");
	    	return UtilityConstant.savefile(directory,uploadfile,oldName);
		}
		return null;
    } // method uploadFile
    
	@SuppressWarnings("unchecked")
    @RequestMapping(value = "uploadfile3", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadFileProductImage(@RequestParam("file") MultipartFile uploadfile, @RequestParam("oldName") String oldName, final HttpServletRequest request) {
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			String directory = env.getProperty("yoda.uploadedFiles.productDetail");
	    	return UtilityConstant.savefile(directory,uploadfile,oldName);
		}
		return null;
    }
	
	@RequestMapping(value = "uploadfile4", method = RequestMethod.POST)// artile upload
    @ResponseBody
    public ResponseEntity<String> uploadFileArticleImage(@RequestParam("file") MultipartFile uploadfile, @RequestParam("oldName") String oldName) {
    	String directory = env.getProperty("yoda.uploadedFiles.article");
    	return UtilityConstant.savefile(directory,uploadfile,oldName);
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "uploadfile5", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadFileBannerImage(@RequestParam("file") MultipartFile uploadfile, @RequestParam("oldName") String oldName, final HttpServletRequest request) {
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			String directory = env.getProperty("yoda.uploadedFiles.banner");
	    	return UtilityConstant.savefile(directory,uploadfile, oldName);
		}
		return null;
    }
/////////////////////////////Banner section/////////////////////////////
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getBannerForMgnt", method = RequestMethod.GET)
	public List<Banner> getBanner(final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			return (List<Banner>) bannerService.findAll();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "upsertBanner", method = RequestMethod.POST)
	public ManagementResponse updateBanner(@RequestBody final Banner banner, final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			banner.setModDate(new Date());
			bannerService.save(banner);
		}
		return new ManagementResponse("upsert_banner_success");
	}

/////////////////////////////ShipCost section/////////////////////////////
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getShipCostForMgnt", method = RequestMethod.GET)
	public List<ShipCost> getShipCost(final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			return (List<ShipCost>) shipCostService.findAll();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "upsertShipCost", method = RequestMethod.POST)
	public ManagementResponse updateShipCost(@RequestBody final ShipCost shipCost, final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			shipCostService.save(shipCost);
		}
		return new ManagementResponse("upsert_shipCost_success");
	}
	
	
//////////////////////////////Member section/////////////////////////////	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getMemberForMgnt/{amount}", method = RequestMethod.GET)
	public List<Members> getMemberForMgnt(@PathVariable final int amount, final HttpServletRequest request) throws ServletException {
		List<Members> memberList = new ArrayList<Members>();
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			if(amount==20){
				memberList =  memberService.findFirst20ByOrderByMemberIdDesc();
			}else{
				memberList = memberService.findAllByOrderByMemberIdDesc();
			}
		}
		return memberList;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "upsertMember", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ManagementResponse updateMe(@RequestBody final Members member,final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.ADMIN_ROLE)){
			Members m = memberService.findByEmail(member.getEmail());
			if (m == null) {
	            throw new ServletException("email_not_exist");
	        }
			
			m.setStatus(member.getStatus());
			memberService.save(m);
			return new ManagementResponse("update_member_success");
		}else{
			return new ManagementResponse("no_right_in_admin");
		}
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
