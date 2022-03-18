package com.qa.horses.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.horses.domain.Horses;
import com.qa.horses.repo.HorsesRepo;

@Service
public class HorsesServiceDB implements HorsesInterface{
	
	private HorsesRepo repo;
	
	public HorsesServiceDB(HorsesRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Horses create(Horses x) {
		return this.repo.save(x);
	}

	@Override
	public List<Horses> readAll() {
		return this.repo.findAll();
	}

	@Override
	public Horses readById(Long id) {
		Optional<Horses> optionalRead = this.repo.findById(id);
		return optionalRead.orElse(null);
	}

	@Override
	public Horses update(Long id, Horses y) {
		Optional<Horses> optionalHorse = this.repo.findById(id);
		Horses found = optionalHorse.get();
		found.setName(y.getName());
		found.setBreed(y.getBreed());
		found.setAge(y.getAge());
		found.setGender(y.getGender());
		return this.repo.save(found);
	}

	@Override
	public Boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
