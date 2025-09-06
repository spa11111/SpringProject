package com.virinchi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Story {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Lob //Large Objects- Byte as String
    @Column (columnDefinition = "mediumblob")
    private String image;
	
	private String title;
    private String subtitle;
    private String description;
    private String category;
    private String audience;
    private String status;
    private String premium;
    private String matureContent;
    private int vote;
    private int views;
    
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAudience() {
		return audience;
	}
	public void setAudience(String audience) {
		this.audience = audience;
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
	public String getMatureContent() {
		return matureContent;
	}
	public void setMatureContent(String matureContent) {
		this.matureContent = matureContent;
	}
	public int getVotes() {
		return vote;
	}
	public void setVotes(int vote) {
		this.vote = vote;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	
    
	
	
}
