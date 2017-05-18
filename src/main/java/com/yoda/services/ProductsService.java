package com.yoda.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yoda.models.Categories;
import com.yoda.models.Products;

@Transactional
public interface ProductsService extends PagingAndSortingRepository<Products, Integer>{

	public Products findByProdId(int prodId);
	public Products findByProdIdAndStatus(int prodId, int status);
	public List<Products> findByStatusOrderByModDateDesc(int status);
	public List<Products> findByCategoryAndStatus(Categories category, int status);
	public List<Products> findFirst6ByStatusOrderByModDateDesc(int status);
	public List<Products> findFirst9ByStatusOrderByModDateDesc(int status);
	public List<Products> findByProdIdInAndStatus(List<Integer> prodIds, int status);
	public Page<Products> findByCategoryAndStatus(Categories category, int status, Pageable pageRequest);
	
	//mgnt
	public List<Products> findFirst20ByOrderByProdIdDesc();
	public List<Products> findFirst50ByOrderByModDateDesc();
	public List<Products> findAllByOrderByModDateDesc();
	public List<Products> findAllByOrderByProdIdDesc();
	public List<Products> findFirst20ByCategoryOrderByProdIdDesc(Categories category);
	public List<Products> findAllByCategoryOrderByProdIdDesc(Categories category);
}
