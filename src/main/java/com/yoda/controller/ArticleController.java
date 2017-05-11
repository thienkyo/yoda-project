package com.yoda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.UtilityConstant;
import com.yoda.models.Article;
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
}
