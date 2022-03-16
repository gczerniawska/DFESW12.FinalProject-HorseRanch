package com.qa.horses.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.horses.domain.Horses;
import com.qa.horses.service.HorsesServiceDB;

@RestController
public class HorsesController {
		
	private HorsesServiceDB service;
		
	public HorsesController(HorsesServiceDB service) {
		super();
		this.service = service;
	}
		
	@PostMapping("/create")
	public ResponseEntity<Horses> createHorse(@RequestBody Horses horse) {
		return new ResponseEntity<Horses>(this.service.create(horse), HttpStatus.CREATED);
	}
		
	@GetMapping("/readAll")
	public ResponseEntity<List<Horses>> readHorses() {
		return new ResponseEntity<List<Horses>>(this.service.readAll(), HttpStatus.ACCEPTED);
	}
		
	@GetMapping("/readById/{id}")
	public ResponseEntity<Horses> readById(@PathVariable Long id) {
		return new ResponseEntity<Horses>(this.service.readById(id), HttpStatus.FOUND);
	}
		
	@PutMapping("/update/{id}")
	public ResponseEntity<Horses> update(@PathVariable Long id, @RequestBody Horses updated) {
		return new ResponseEntity<Horses>(this.service.update(id, updated), HttpStatus.I_AM_A_TEAPOT);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
	    return new ResponseEntity<>(this.service.delete(id), HttpStatus.GONE);
	}
		
	// -- alternative delete method
	//@DeleteMapping("/delete")
	//public ResponseEntity<Horses> delete(@PathParam("id") Long id) {
	//return new ResponseEntity<Horses>(this.service.delete(id), HttpStatus.GONE);
	//	}
}
