package com.yoda.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.yoda.models.Article;
import com.yoda.models.Products;

@Transactional
public interface ArticleService extends CrudRepository<Article, Integer>{
	public List<Article> findAllByStatusOrderByArticleIdDesc(int status);
	public Article findByArticleIdAndStatus(int articleId, int status);
	public Page<Article> findByStatus(int status, Pageable pageRequest);
	public List<Article> findFirst4ByStatusOrderByModDateDesc(int status);
	public List<Article> findByStatusAndArticleNameContainingIgnoreCase(int status, String articleName);
	///////////mngt///////////
	public Article findByArticleId(int articleId);
	public List<Article> findFirst20ByOrderByArticleIdDesc();
	public List<Article> findAllByOrderByArticleIdDesc();
}
