package com.virinchi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.virinchi.model.Chapter;
import com.virinchi.repository.ChapterRepository;

import java.util.List;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    public Chapter save(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    public Chapter getChapterById(int id) {
        return chapterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));
    }

    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }

    public void deleteChapter(int id) {
        chapterRepository.deleteById(id);
    }

    public List<Chapter> getChaptersByStory(int storyId) {
        // custom query can be added in repository later
        return chapterRepository.findAll()
                .stream()
                .filter(ch -> ch.getStory().getId() == storyId)
                .toList();
    }
}
