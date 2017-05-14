package com.yoda.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.yoda.models.Banner;

@Transactional
public interface BannerService extends CrudRepository<Banner, Integer>{
	public List<Banner> findAllByStatus(int status);
}
