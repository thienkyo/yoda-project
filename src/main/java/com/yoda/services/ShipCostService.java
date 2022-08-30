package com.yoda.services;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import com.yoda.models.ShipCost;

import java.util.List;

@Transactional
public interface ShipCostService extends CrudRepository<ShipCost, Integer>{
    public List<ShipCost> findAllByStatus(int status);
}
