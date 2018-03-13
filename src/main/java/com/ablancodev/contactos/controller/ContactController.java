package com.ablancodev.contactos.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ablancodev.contactos.constant.ViewConstant;
import com.ablancodev.contactos.model.ContactModel;
import com.ablancodev.contactos.service.ContactService;

@Controller
@RequestMapping( "/contacts" )
public class ContactController {

	private static final Log LOG = LogFactory.getLog( ContactController.class );

	@Autowired
	@Qualifier( "contactServiceImpl" )
	private ContactService contactService;

	@GetMapping( "/cancel" )
	public String cancel() {
		return "redirect:/contacts/showcontacts";
	}

	@GetMapping( "/contactform" )
	private String redirectContactForm( Model model ) {
		model.addAttribute( "contactModel", new ContactModel() );
		return ViewConstant.CONTACT_FORM;
	}

	@PostMapping( "/addcontact" )
	public String addContact( 
			@ModelAttribute( name="contactModel" ) ContactModel contactModel,
			Model model ) {

		if ( contactService.addContact( contactModel ) != null ) {
			model.addAttribute( "result", 1 );
		} else {
			model.addAttribute( "result", 0 );
		}

		LOG.info("Method: addContact() --- Params: ContactModel=" + contactModel.toString());
		return "redirect:/contacts/showcontacts";
	}

	@GetMapping( "/showcontacts" )
	public ModelAndView showContacts() {
		ModelAndView mav = new ModelAndView( ViewConstant.CONTACTS );
		mav.addObject( "contacts", contactService.listAllContacts() );
		return mav;
	}

	@GetMapping( "/removecontact" )
	public ModelAndView removeContact( @RequestParam( name="id", required=true ) int id ) {
		contactService.removeContact( id );
		return showContacts();
	}

	@GetMapping( "/editcontact" )
	public ModelAndView editContact( @RequestParam( name="id", required=true ) int id ) {
		ContactModel contactModel = contactService.getContact( id );
		ModelAndView mav = new ModelAndView();
		if ( contactModel != null ) {
			mav.setViewName( ViewConstant.CONTACT_FORM );
			mav.addObject( "contactModel", contactModel );
		} else {
			mav.setViewName( ViewConstant.CONTACTS );
		}
		return mav;
	}
}
