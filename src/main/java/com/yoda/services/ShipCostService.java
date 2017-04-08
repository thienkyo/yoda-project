package com.yoda.services;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.yoda.models.ShipCost;

@Transactional
public interface ShipCostService extends CrudRepository<ShipCost, Integer>{

}
