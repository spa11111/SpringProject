package com.virinchi.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	
	//Hibernate will help create a table named "User" with an annotation provided to the class, the annotation is "@Entity"
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//GeneratedValue= auto_increment
	private int id; //primary key, auto_increment
	private String name;
	private String username;
	private String email;
	private String gender;
	private String date;
	private String password;
	
	@Column(name = "work", nullable = false)
	private int work =0;
	
	@Column(name = "coins", nullable = false)
    private int coins = 0;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Story> stories = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getWork() {
		return work;
	}

	public void setWork(int work) {
		this.work = work;
	}
	
	public void incrementWork() {
        this.work++;
    }

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public List<Story> getStories() {
		return stories;
	}

	public void setStories(List<Story> stories) {
		this.stories = stories;
	}
	
	public void addStory(Story story) {
        stories.add(story);
        story.setUser(this);
    }
	
	
	
}
