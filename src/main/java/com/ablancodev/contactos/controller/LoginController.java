package com.ablancodev.contactos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ablancodev.contactos.constant.ViewConstant;
import com.ablancodev.contactos.model.UserCredential;

@Controller
public class LoginController {

	private static final Log LOG = LogFactory.getLog( LoginController.class );

	@GetMapping("/")
	public String redirectToLogin() {
		LOG.info("Method: redirectToLogin()");
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String showLoginForm ( 
			Model model,
			@RequestParam( name="error", required=false ) String error,
			@RequestParam( name="logout", required=false ) String logout
		) {
		LOG.info("Method: showLoginForm() --- Params: error=" + error + " Logout=" + logout);
		model.addAttribute( "error", error );
		model.addAttribute( "logout", logout );
		model.addAttribute( "userCredentials", new UserCredential() );
		LOG.info( "return to login view" );
		return ViewConstant.LOGIN;
	}

	@PostMapping("/logincheck")
	public String loginCheck( @ModelAttribute( name="userCredentials" ) UserCredential userCredential ) {
		LOG.info("Method: loginCheck() --- Params: userCredential=" + userCredential.toString());

		if ( userCredential.getUsername().equals("user") && userCredential.getPassword().equals("user") ) {
			LOG.info( "return to contacts view" );
			return "redirect:/contacts/showcontacts";
		} else {
			LOG.info( "redirect to login error" );
			return "redirect:/login?error";
		}
	}
}
