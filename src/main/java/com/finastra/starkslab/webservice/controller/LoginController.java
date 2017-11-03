package com.finastra.starkslab.webservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finastra.starkslab.webservice.model.UserRequest;
import com.finastra.starkslab.webservice.model.User;
import com.finastra.starkslab.webservice.provider.LDAPProvider;

@RestController
@RequestMapping("/rest")
public class LoginController {

/*	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Response loginUser(User u) throws ClassNotFoundException {
		User user = new User();
		//LDAPProvider ldapUtility = new LDAPProvider();
		//user = ldapUtility.validateLogin(u.getId(), u.getPassword());
		user.setSuccessfulLogin(true);
		user.setId("jpardillo");
		user.setFirstName("Jerico");
		user.setLastName("Pardillo");
		user.setMiddleName("T");
		return Response.ok(user)
        .header("Access-Control-Allow-Origin", "http://man-l1xtyf12:3000")
        .header("Access-Control-Allow-Headers", "Content-Type,Access-Control-Request-Origin,Access-Control-Request-Headers,Access-Control-Request-Method")
        .header("Access-Control-Allow-Methods", "POST")
        .build();
	}*/
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@CrossOrigin(origins="*")
	public User loginUser(@RequestBody UserRequest userRequest) throws ClassNotFoundException {
		User user = new User();
		LDAPProvider ldapUtility = new LDAPProvider();
		user = ldapUtility.validateLogin(userRequest.getId(), userRequest.getPassword());
		return user;
	}
}
