package com.qa.horses.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.horses.domain.Horses;
import com.qa.horses.repo.HorsesRepo;

@SpringBootTest
@ActiveProfiles("test")
public class HorsesServiceDBTest {
	
	private Horses input;
	private Horses returned;
	
	@Autowired
	private HorsesServiceDB service;
	
	@MockBean
	private HorsesRepo repo;
	
	@BeforeEach
	void setUp() {
		input = new Horses("Driftwood", "Mustang", 12, "male");
		returned = new Horses(1L, "Driftwood", "Mustang", 12, "male");
		
	}
	
	@Test
	void testCreate() {
		System.out.println("test 1");
		
		//GIVEN
		//input/data
		//WHEN
		Mockito.when(this.repo.save(input)).thenReturn(returned);
		//THEN
		assertThat(this.service.create(input)).isEqualTo(returned);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(input);
	}
	
	@Test
	void testReadAll() {
		System.out.println("test 2");
		// GIVEN
		List<Horses> horseList = new ArrayList<>();
		horseList.add(input);
		//WHEN
		Mockito.when(this.repo.findAll()).thenReturn(horseList);
		//THEN
		assertThat(this.service.readAll()).isEqualTo(horseList);
		//VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findAll();	
		
	}
	
	@Test
	void testreadById( ) {
		// GIVEN
		Long findId = 1L;
		Optional<Horses> optionalHorse = Optional.of(returned);
		// WHEN
		Mockito.when(this.repo.findById(findId)).thenReturn(optionalHorse);
		// THEN
		assertThat(this.service.readById(findId)).isEqualTo(returned);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findById(findId);
	}
	
	@Test
	void testUpdate() {
		Long id = 1L;
		// NEW ANIMAL OBJECT FOR INPUT TO UPDATE METHOD
		Horses toUpdate = new Horses("Clyde", "Appaloosa", 29, "male");
		// METHOD USES AN OPTIONAL VERSION OF THE ANIMAL OBJECT
		Optional<Horses> optional = Optional.of(returned);
		// UPDATED VERSION:
		Horses updated = new Horses(id, toUpdate.getName(), toUpdate.getBreed(), toUpdate.getAge(), toUpdate.getGender());
		// WHEN
		Mockito.when(this.repo.findById(id)).thenReturn(optional);
		Mockito.when(this.repo.save(updated)).thenReturn(updated);
		// THEN
		assertThat(this.service.update(id, toUpdate)).isEqualTo(updated);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updated);
	}
	
	@Test
	void testDelete() {
		//GIVEN
		Long id = 1L;
		//WHEN
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		//THEN
		assertThat(this.service.delete(id)).isTrue();
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		
	}
	
	@AfterEach
	void clear() {
		System.out.println("after");
		
	}

}
