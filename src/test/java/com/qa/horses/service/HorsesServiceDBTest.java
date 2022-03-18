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
		
		Mockito.when(this.repo.save(input)).thenReturn(returned);
		assertThat(this.service.create(input)).isEqualTo(returned);
		Mockito.verify(this.repo, Mockito.times(1)).save(input);
	}
	
	@Test
	void testReadAll() {
		
		List<Horses> horseList = new ArrayList<>();
		horseList.add(input);
		Mockito.when(this.repo.findAll()).thenReturn(horseList);
		assertThat(this.service.readAll()).isEqualTo(horseList);
		Mockito.verify(this.repo, Mockito.times(1)).findAll();	
	}
	
	@Test
	void testreadById( ) {
		
		Long findId = 1L;
		Optional<Horses> optionalHorse = Optional.of(returned);
		Mockito.when(this.repo.findById(findId)).thenReturn(optionalHorse);
		assertThat(this.service.readById(findId)).isEqualTo(returned);
		Mockito.verify(this.repo, Mockito.times(1)).findById(findId);
	}
	
	@Test
	void testUpdate() {
		
		Long id = 1L;
		Horses toUpdate = new Horses("Clyde", "Appaloosa", 29, "male");
		Optional<Horses> optional = Optional.of(returned);
		Horses updated = new Horses(id, toUpdate.getName(), toUpdate.getBreed(), toUpdate.getAge(), toUpdate.getGender());
		Mockito.when(this.repo.findById(id)).thenReturn(optional);
		Mockito.when(this.repo.save(updated)).thenReturn(updated);
		assertThat(this.service.update(id, toUpdate)).isEqualTo(updated);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updated);
	}
	
	@Test
	void testDelete() {

		Long id = 1L;
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		assertThat(this.service.delete(id)).isTrue();
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);	
	}
	
	@AfterEach
	void clear() {
		System.out.println("after");	
	}

}
