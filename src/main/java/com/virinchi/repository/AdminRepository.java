package com.virinchi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virinchi.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	boolean existsByUsernameAndPassword(String username, String password);

}