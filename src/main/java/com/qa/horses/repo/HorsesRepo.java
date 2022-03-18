package com.qa.horses.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.horses.domain.Horses;

@Repository
public interface HorsesRepo extends JpaRepository<Horses, Long>{

}
