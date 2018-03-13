package com.ablancodev.contactos.service;

import java.util.List;

import com.ablancodev.contactos.entity.Contact;
import com.ablancodev.contactos.model.ContactModel;

public interface ContactService {

	public abstract ContactModel addContact( ContactModel contactModel );
	
	public abstract List<ContactModel> listAllContacts();

	public abstract Contact findContactById( int id );

	public abstract void removeContact( int id );

	//public abstract void editContact( int id );

	public abstract ContactModel getContact( int id );
}
