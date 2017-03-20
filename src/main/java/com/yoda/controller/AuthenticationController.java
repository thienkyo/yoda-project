package com.yoda.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.DAO.MembersDAO;
import com.yoda.models.Members;
import com.yoda.services.MemberService;

import io.jsonwebtoken.Claims; 

@RestController
@RequestMapping("/authenticated")
public class AuthenticationController {
	@Autowired private MemberService memService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "role", method = RequestMethod.GET)
	public Boolean login(@PathVariable final String role,
			final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");

		return ((List<String>) claims.get("roles")).contains(role);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "me", method = RequestMethod.GET)
	public MembersDAO getMe(final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		MembersDAO memDAO = new MembersDAO(memService.findByEmailAndStatus(claims.get("sub")+"", 1));
		return memDAO;
	}
}
