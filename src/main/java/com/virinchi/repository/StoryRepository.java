package com.virinchi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virinchi.model.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long>{
	

}