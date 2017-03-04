package com.yoda.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import com.yoda.models.Categories;

@Transactional
public interface CategoriesRepository extends CrudRepository<Categories, Integer> {

	public Categories findByCategoryId(int categoryId);//@Param("cateId")
	public List<Categories> findByStatus(int status);
	//public List<Categories> findAll();
	
}
