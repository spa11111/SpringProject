package com.virinchi.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

public class Book {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Lob //Large Objects- Byte as String
    @Column (columnDefinition = "mediumblob")
    private String image;
	
	private String title;
	private String author;
	private String views;
	private String votes;
	private String status;
	private String premium;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getViews() {
		return views;
	}
	public void setViews(String views) {
		this.views = views;
	}
	public String getVotes() {
		return votes;
	}
	public void setVotes(String votes) {
		this.votes = votes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
		this.premium = premium;
	}
	
	
	
}
