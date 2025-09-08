package com.virinchi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virinchi.model.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

}
