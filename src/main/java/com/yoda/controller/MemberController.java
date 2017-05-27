package com.yoda.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.UtilityConstant;
import com.yoda.models.MemberRole;
import com.yoda.models.Members;
import com.yoda.models.ShipCost;
import com.yoda.services.MemberRoleService;
import com.yoda.services.MemberService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/members")
public class MemberController {
	@Autowired private MemberService memService;
	@Autowired private MemberRoleService memberRoleService;
	
/*		
	@RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final MemberLogin login) throws ServletException, UnsupportedEncodingException {
		Members mem =memService.findByEmailAndPassAndStatus(login.email, login.pass, 1);
        if (login.email == null || mem == null) {
            throw new ServletException("Invalid login");
        }
        
        List<MemberRole> mr = memberRoleService.findByEmail(login.email);
        
        List<String> rolelist = new ArrayList<String>(); 
        for(MemberRole r : mr ){
        	rolelist.add(r.getRole());
        }
        
        return new LoginResponse(Jwts.builder()
        		.setSubject(login.email)
        		.claim("roles", rolelist)
        		.claim("name", mem.getFullName())
        		.setIssuedAt(new Date())
        		.setExpiration(new Date(System.currentTimeMillis()+ UtilityConstant.AUTHENTICATION_TIMEOUT*60*1000))
        		.signWith(SignatureAlgorithm.HS256, "secretkey".getBytes("UTF-8"))
        		.compact());
    }*/
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final LoginRequest login) throws ServletException, UnsupportedEncodingException {
		String decoded = new String(Base64.getDecoder().decode(login.loginStr));
		String[] parts = decoded.split("--");
		
		Members mem =memService.findByEmailAndPassAndStatus(parts[0].toString(), parts[1].toString(), 1);
        if (parts[0].isEmpty() || mem == null) {
            throw new ServletException("Invalid login");
        }
        
        List<MemberRole> mr = memberRoleService.findByEmail(parts[0]);
        
        List<String> rolelist = new ArrayList<String>(); 
        for(MemberRole r : mr ){
        	rolelist.add(r.getRole());
        }
        
        return new LoginResponse(Jwts.builder()
        		.setSubject(parts[0])
        		.claim("roles", rolelist)
        		.claim("name", mem.getFullName())
        		.setIssuedAt(new Date())
        		.setExpiration(new Date(System.currentTimeMillis()+ UtilityConstant.AUTHENTICATION_TIMEOUT*60*1000))
        		.signWith(SignatureAlgorithm.HS256, "secretkey".getBytes("UTF-8"))
        		.compact());
    }
	
	@RequestMapping(value = "add", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public AddResponse add(@RequestBody final Members member) throws ServletException {
		if (memService.findByEmail(member.getEmail()) != null) {
            throw new ServletException("emailexists");
        }
		 
		member.setModDate(new Date());
		member.setShipCost(new ShipCost(7));
		Members returnMem = memService.save(member);
		memberRoleService.save(new MemberRole(member.getEmail(), UtilityConstant.MEMBER_ROLE));
		return new AddResponse(returnMem.getEmail());      
    }

    @SuppressWarnings("unused")
    private static class MemberLogin {
        public String email;
        public String pass;
    }
    
    @SuppressWarnings("unused")
    private static class LoginResponse {
    	public String token;
        public LoginResponse(final String token) {
            this.token = token;
        }
    }
    
    @SuppressWarnings("unused")
    private static class LoginRequest {
    	public String loginStr;
    }
/*
    @SuppressWarnings("unused")
    private static class LoginResponse {
    	public String token;
        public List<String> rolelist;
        public String address;
        public LoginResponse(final String token, List<String> rolelist, String address) {
            this.token = token;
            this.rolelist = rolelist;
            this.address = address; 
        }
    }*/
    
    @SuppressWarnings("unused")
    private static class AddResponse {
        public String replyStr;
        public AddResponse(final String replyStr) {
            this.replyStr = replyStr;
        }
    }

}
