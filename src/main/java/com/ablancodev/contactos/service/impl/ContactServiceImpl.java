package com.ablancodev.contactos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ablancodev.contactos.component.ContactConverter;
import com.ablancodev.contactos.entity.Contact;
import com.ablancodev.contactos.model.ContactModel;
import com.ablancodev.contactos.repository.ContactRepository;
import com.ablancodev.contactos.service.ContactService;

@Service( "contactServiceImpl" )
public class ContactServiceImpl implements ContactService {

	@Autowired
	@Qualifier( "contactRepository" )
	private ContactRepository contactRepository;

	@Autowired
	@Qualifier( "contactConverter" )
	private ContactConverter contactConverter;

	@Override
	public ContactModel addContact(ContactModel contactModel) {
		Contact contact = contactRepository.save( contactConverter.convertContactModel2Contact( contactModel ) );
		return contactConverter.convertContact2ContactModel( contact );
	}

	@Override
	public List<ContactModel> listAllContacts() {
		List<Contact> contacts = contactRepository.findAll();
		List<ContactModel> contactsModel = new ArrayList<ContactModel>();
		for ( Contact contact : contacts ) {
			contactsModel.add( contactConverter.convertContact2ContactModel( contact ) );
		}
		return contactsModel;
	}

	@Override
	public void removeContact( int id ) {
		Contact contact = findContactById( id );
		if ( contact != null ) {
			contactRepository.delete( findContactById( id ) );
		}
	}

	@Override
	public Contact findContactById(int id) {
		return contactRepository.findById( id );
	}

	@Override
	public ContactModel getContact(int id) {
		ContactModel contactModel = null;
		Contact contact = contactRepository.findById( id );
		if ( contact != null ) {
			contactModel = contactConverter.convertContact2ContactModel( contact );
		}
		return contactModel;
	}

	/*
	@Override
	public void editContact(int id) {
		Contact contact = findContactById( id );
		if ( contact != null ) {
			contactRepository.save( contact );
		}
		
	}
	*/



}
