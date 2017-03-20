package com.yoda.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.DAO.MembersDAO;
import com.yoda.models.MemberRole;
import com.yoda.models.Members;
import com.yoda.services.MemberRoleService;
import com.yoda.services.MemberService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/members")
public class MemberController {
	@Autowired private MemberService memService;
	@Autowired private MemberRoleService memberRoleService;
	
		
	@RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final MemberLogin login) throws ServletException {
        if (login.email == null || memService.findByEmailAndPassAndStatus(login.email, login.pass, 1) == null) {
            throw new ServletException("Invalid login");
        }
        
        List<MemberRole> mr = memberRoleService.findByEmail(login.email);
        
        List<String> rolelist = new ArrayList<String>(); 
        for(MemberRole r : mr ){
        	rolelist.add(r.getRole());
        }
        return new LoginResponse(Jwts.builder().setSubject(login.email)
            .claim("roles", rolelist).setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
    }
	
	@RequestMapping(value = "add", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public AddResponse add(@RequestBody final Members member) throws ServletException {
		if (memService.findByEmail(member.getEmail()) != null) {
            throw new ServletException("emailexists");
        }
		
		member.setModDate(new Date());
		Members returnMem = memService.save(member); 
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
    private static class AddResponse {
        public String replyStr;
        public AddResponse(final String replyStr) {
            this.replyStr = replyStr;
        }
    }

}
