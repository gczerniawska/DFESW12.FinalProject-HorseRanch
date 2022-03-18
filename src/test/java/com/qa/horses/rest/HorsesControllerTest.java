package com.qa.horses.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.horses.domain.Horses;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:horses-schema.sql", "classpath:horses-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class HorsesControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper map;
	
	@Test
	void create() throws Exception {
		
		Horses create = new Horses("Playgun", "Thoroughbred", 9, "male");
		String createJSON = this.map.writeValueAsString(create);
		RequestBuilder mockRequest = post("/create").contentType(MediaType.APPLICATION_JSON).content(createJSON);
		Horses saved = new Horses(2L, "Playgun", "Thoroughbred", 9, "male");
		String savedJSON = this.map.writeValueAsString(saved);
		ResultMatcher matchStatus = status().isCreated();
		ResultMatcher matchBody = content().json(savedJSON);
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}
	
	@Test
	void readAll() throws Exception {
			
		List<Horses> horses = new ArrayList<>();
		RequestBuilder request = get("/readAll").contentType(MediaType.APPLICATION_JSON);
		Horses saved = new Horses(1L, "Playgun", "Mustang", 9, "Male");
		horses.add(saved);
		String savedJSON = this.map.writeValueAsString(horses);
		ResultMatcher matchStatus = status().is2xxSuccessful();
		ResultMatcher matchBody = content().json(savedJSON);
		this.mock.perform(request).andExpect(matchStatus).andExpect(matchBody);
	}
	
	@Test
	void readById() throws Exception {
		
		Horses saved = new Horses(1L, "Playgun", "Mustang", 9, "Male");
		String savedJSON = this.map.writeValueAsString(saved);
		RequestBuilder request = get("/readById/1");
		ResultMatcher matchStatus = status().is3xxRedirection();
		ResultMatcher matchBody = content().json(savedJSON);
		this.mock.perform(request).andExpect(matchStatus).andExpect(matchBody);
	}
	
	@Test
	void update() throws Exception {
		
		Long id = 1L;
		Horses updateItem = new Horses("Toygun", "Seahorse", 666, "Male");
		String updateJSON = this.map.writeValueAsString(updateItem);
		RequestBuilder request = put("/update/{id}", id).contentType(MediaType.APPLICATION_JSON).content(updateJSON);
		Horses returnedItem = new Horses(1L, "Toygun", "Seahorse", 666, "Male");
		String createdJSON = this.map.writeValueAsString(returnedItem);
		ResultMatcher matchStatus = status().is4xxClientError();
		ResultMatcher matchBody = content().json(createdJSON);
		this.mock.perform(request).andExpect(matchStatus).andExpect(matchBody);
	}
	
	@Test
	void deleteById() throws Exception {
		
		Long id = 1L;
		RequestBuilder request = delete("/deleteById/{id}", id);
		ResultMatcher matchStatus = status().is4xxClientError();
		ResultMatcher matchBody = content().string("true");
		this.mock.perform(request).andExpect(matchStatus).andExpect(matchBody);
	}
	
}
