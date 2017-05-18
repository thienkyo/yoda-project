package com.yoda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.UtilityConstant;
import com.yoda.models.Article;
import com.yoda.models.Products;
import com.yoda.services.ArticleService;

@RestController
@RequestMapping("/blog")
public class ArticleController {
	@Autowired
    private ArticleService articleRepo;
	
	@RequestMapping("")
    public List<Article> getActiveArticle() {
        return articleRepo.findAllByStatusOrderByArticleIdDesc(UtilityConstant.ACTIVE_STATUS);
    }
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Article getOneActiveArticle(@PathVariable final int id) {
        return articleRepo.findByArticleIdAndStatus(id, UtilityConstant.ACTIVE_STATUS);
    }
	
	@RequestMapping(value = "getArticlePage/{pageNumber}", method = RequestMethod.GET)
	public Page<Article> getArticlePage(@PathVariable Integer pageNumber) {
        Pageable request = new PageRequest(pageNumber - 1, UtilityConstant.BLOG_PAGE_SIZE, Sort.Direction.DESC, "articleId");
        return articleRepo.findByStatus(UtilityConstant.ACTIVE_STATUS, request);
    }
	
	@RequestMapping("homeArticle")
    public List<Article> getHomeProduct() {//@RequestParam(value = "status") 
        return articleRepo.findFirst4ByStatusOrderByModDateDesc(UtilityConstant.ACTIVE_STATUS);
    }
}
