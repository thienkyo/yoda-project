package com.yoda.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.UtilityConstant;
import com.yoda.DAO.MembersDAO;
import com.yoda.models.MemberRole;
import com.yoda.models.Members;
import com.yoda.models.Orders;
import com.yoda.services.MemberService;
import com.yoda.services.OrdersService;

import io.jsonwebtoken.Claims; 

@RestController
@RequestMapping("/authenticated")
public class AuthenticationController {
	@Autowired private MemberService memService;
	@Autowired private OrdersService orderService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "role", method = RequestMethod.GET)
	public Boolean login(@PathVariable final String role, final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");

		return ((List<String>) claims.get("roles")).contains(role);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "me", method = RequestMethod.GET)
	public MembersDAO getMe(final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		MembersDAO memDAO = new MembersDAO(memService.findByEmailAndStatus(claims.get("sub")+"", UtilityConstant.ACTIVE_STATUS));
		return memDAO;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "saveOrder", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	public AuthenticationResponse saveOrder(@RequestBody final Orders order,final HttpServletRequest request) throws ServletException {
		final Claims claims = (Claims) request.getAttribute("claims");
		if(((List<String>) claims.get("roles")).contains(UtilityConstant.MEMBER_ROLE)){
			Orders or = orderService.save(order);
		}
		
		return new AuthenticationResponse("order_success");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "updateMe", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	public AuthenticationResponse updateMe(@RequestBody final Members member) throws ServletException {
		Members m = memService.findByEmail(member.getEmail());
		if (m == null) {
            throw new ServletException("email_not_exist");
        }
		
		m.setAddress(member.getAddress());
		m.setFullName(member.getFullName());
		m.setPhone(member.getPhone());
		m.setShipCost(member.getShipCost());
		
		m.setModDate(new Date());
		//member.setShipCost(new ShipCost(1));
		memService.save(m);
		
		return new AuthenticationResponse("update_mem_success");
	}
	
	@SuppressWarnings("unused")
    private static class AuthenticationResponse {
    	public String replyStr;
        public AuthenticationResponse(final String replyStr) {
            this.replyStr = replyStr;
        }
    }
}
