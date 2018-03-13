package com.ablancodev.contactos.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ablancodev.contactos.entity.Contact;

@Repository( "contactRepository" )
public interface ContactRepository extends JpaRepository<Contact, Serializable> {

	public abstract Contact findById( int id );

}
