package com.virinchi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virinchi.model.Story;
import com.virinchi.model.User;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long>{
	
	List<Story> findByUserAndPublishedTrue(User user);

}