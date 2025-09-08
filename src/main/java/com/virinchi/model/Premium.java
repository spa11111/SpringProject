package com.virinchi.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;

@Entity
public class Premium {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//GeneratedValue= auto_increment
	private int id; //primary key, auto_increment
	
	@Transient
    private MultipartFile file;   // this matches the HTML "name=file"

    @Lob
    @Column(columnDefinition = "mediumblob")
    private String image;
    
    private String title;

}
