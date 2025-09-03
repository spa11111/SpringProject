package com.virinchi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virinchi.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	

}