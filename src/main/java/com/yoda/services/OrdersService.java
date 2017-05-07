package com.yoda.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.yoda.models.Categories;
import com.yoda.models.Orders;
import com.yoda.models.Products;


@Transactional
public interface OrdersService extends CrudRepository<Orders, Integer>{

	//mgnt
		public List<Orders> findFirst20ByOrderByOrderIdDesc();
	//	public List<Orders> findFirst50ByOrderByModDateDesc();
	//	public List<Orders> findAllByOrderByModDateDesc();
		public List<Orders> findAllByOrderByOrderIdDesc();
}
