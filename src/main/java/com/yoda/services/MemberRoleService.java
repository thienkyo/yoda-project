package com.yoda.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.yoda.models.MemberRole;

@Transactional
public interface MemberRoleService extends CrudRepository<MemberRole, Integer>{
	public List<MemberRole> findByEmail(String email);
}
