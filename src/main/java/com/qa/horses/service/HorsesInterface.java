package com.qa.horses.service;

import java.util.List;

import com.qa.horses.domain.Horses;

public interface HorsesInterface {
	
	Horses create(Horses x);
	
	List<Horses> readAll();
	
	Horses readById(Long id);
	
	Horses update(Long id, Horses y);
	
	Boolean delete(Long id);
	
	// -- alternative delete method
	// Horses delete(Long id);

}
