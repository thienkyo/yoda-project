package com.yoda.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.UtilityConstant;
import com.yoda.models.Article;
import com.yoda.models.Products;
import com.yoda.services.ArticleService;
import com.yoda.services.ProductsService;


@RestController
@RequestMapping("/search")
public class SearchController {
	@Autowired private ProductsService productService;
	@Autowired private ArticleService articleService;
	
	@RequestMapping("{keySearch}")
    public List<SearchResult> search(@PathVariable final String keySearch) {
		List<Products> prodList = productService.findByStatusAndProdNameContainingIgnoreCase(UtilityConstant.ACTIVE_STATUS, keySearch);
		List<SearchResult> resultList = new ArrayList<>();
		prodList.forEach(item->{
			SearchResult temp = new SearchResult(1, item.getProdName(), item.getProdId(), item.getImage());
			resultList.add(temp);
		});
		
		List<Article> artList = articleService.findByStatusAndArticleNameContainingIgnoreCase(UtilityConstant.ACTIVE_STATUS, keySearch);
		artList.forEach(item->{
			SearchResult temp = new SearchResult(2, item.getArticleName(), item.getArticleId(), item.getImage());
			resultList.add(temp);
		});
		
        return resultList;
    }
	
////////////////////////////////////
	@SuppressWarnings("unused")
    private class SearchResult {
		public int type;
    	public String name;
    	public int id;
    	public String image;
    	
        public SearchResult(int type, String name, int id, String image) {
			super();
			this.type = type;
			this.name = name;
			this.id = id;
			this.image = image;
		}
    }

}
