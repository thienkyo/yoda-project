package com.yoda.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.yoda.models.Categories;
import com.yoda.models.Products;

@Transactional
public interface ProductsService extends CrudRepository<Products, Integer>{

	public Products findByProdId(int prodId);
	public List<Products> findByStatusOrderByModDateDesc(int status);
	public List<Products> findByCategoryAndStatus(Categories category, int status);
	public List<Products> findFirst6ByStatusOrderByModDateDesc(int status);
}
