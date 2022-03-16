package com.qa.horses.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Horses {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String breed;
	private int age;
	private String gender;
	
//	public Horses() {
//		super();
//	}

	public Horses(String name, String breed, int age, String gender) {
		super();
		this.name = name;
		this.breed = breed;
		this.age = age;
		this.gender = gender;
	}

//	public Horses(Long id, String name, String breed, int age, String gender) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.breed = breed;
//		this.age = age;
//		this.gender = gender;
//	}

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getBreed() {
//		return breed;
//	}
//
//	public void setBreed(String breed) {
//		this.breed = breed;
//	}
//
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
//
//	public String getGender() {
//		return gender;
//	}
//
//	public void setGender(String gender) {
//		this.gender = gender;
//	}

}
