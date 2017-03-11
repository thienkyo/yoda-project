package com.yoda.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoda.models.Members;
import com.yoda.services.MemberRepository;

@RestController
@RequestMapping("/members")
public class MemberController {
	@Autowired
    private MemberRepository memRepo;
	
	@RequestMapping(value="",method = RequestMethod.GET)
    public Principal login( Principal member) {
		
		
		return member;
    }
	
	@RequestMapping(value="/login" ,method = RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.ALL_VALUE)
	public ResponseEntity<String>login(@RequestBody Members member){
		String result = "not_found";
		System.out.print(member);
		if(member.getEmail().equals("thienkyo")){
			result = "verified";
		}
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}


}
