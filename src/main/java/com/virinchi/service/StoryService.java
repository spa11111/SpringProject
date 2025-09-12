package com.virinchi.service;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virinchi.model.Story;
import com.virinchi.model.User;
import com.virinchi.repository.StoryRepository;
import com.virinchi.repository.UserRepository;

@Service
public class StoryService {

    @Autowired
    private StoryRepository storyRepo;
    
    @Autowired
    private UserRepository userRepo;


    // Get a single story by ID
    public Story getStory(Long id) {
        return storyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Story not found"));
    }

    // Get all stories
    public List<Story> getAllStories() {
        return storyRepo.findAll();
    }

    // Increment votes
    public void vote(Long id) {
        Story story = storyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Story not found"));
        story.setVote(story.getVote() + 1); // increment
        storyRepo.save(story);
    }

    // Save a new story or update existing
    public Story save(Story story) {
        return storyRepo.save(story);
    }

    //Delete a story
    public void deleteStory(long id) {
        storyRepo.deleteById(id);
    }

	public Story findById(long id) {
		return storyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Story not found"));
	}



    
    
}

