package com.yoda.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.yoda.models.Members;

@Transactional
public interface MemberService extends CrudRepository<Members, Integer>{
	
	public Members findByEmailAndPassAndStatus(String email, String pass, int status);
	public Members findByEmailAndStatus(String email, int status);
	public Members findByEmail(String email);
	
	////mgnt
	
	public List<Members> findFirst20ByOrderByMemberIdDesc();
	public List<Members> findAllByOrderByMemberIdDesc();
}
