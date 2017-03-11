package com.yoda.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.yoda.models.Members;
import com.yoda.models.Products;

@Transactional
public interface MemberRepository extends CrudRepository<Members, Integer>{
	
	public List<Products> findByEmailAndPass(String email, String pass);
}
