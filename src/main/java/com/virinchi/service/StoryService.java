package com.virinchi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virinchi.model.Story;
import com.virinchi.repository.StoryRepository;

@Service
public class StoryService {

    @Autowired
    private StoryRepository storyRepository;

    // Get a single story by ID
    public Story getStory(Long id) {
        return storyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Story not found"));
    }

    // Get all stories
    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }

    // Increment votes
    public void vote(Long id) {
        Story story = storyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Story not found"));
        story.setVote(story.getVote() + 1); // increment
        storyRepository.save(story);
    }

    // Save a new story or update existing
    public Story save(Story story) {
        return storyRepository.save(story);
    }
}
