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

    public Story getStory(Long id) {
        return storyRepository.findById(id).orElseThrow();
    }
    
    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }


    public void vote(Long id) {
        Story story = storyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Story not found"));
        story.setVotes(story.getVotes() + 1); // increment
        storyRepository.save(story);
    }
    
    public Story save(Story story) {
        return storyRepository.save(story);
    }

}
