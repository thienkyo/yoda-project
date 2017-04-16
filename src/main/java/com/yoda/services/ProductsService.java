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
	public List<Products> findByProdIdInAndStatus(List<Integer> prodIds, int status);
	//mgnt
	public List<Products> findFirst20ByOrderByProdIdDesc();
	public List<Products> findFirst50ByOrderByModDateDesc();
	public List<Products> findAllByOrderByModDateDesc(); 
}
