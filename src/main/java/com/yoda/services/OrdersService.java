package com.yoda.services;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.yoda.models.Orders;


@Transactional
public interface OrdersService extends CrudRepository<Orders, Integer>{

}
