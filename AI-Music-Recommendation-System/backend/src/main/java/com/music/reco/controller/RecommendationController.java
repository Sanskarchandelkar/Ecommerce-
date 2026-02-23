package com.music.reco.controller;

import com.music.reco.dto.MoodRequest;
import com.music.reco.dto.RecommendationResponse;
import com.music.reco.model.Song;
import com.music.reco.service.RecommendationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecommendationController {
    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/recommendations/mood")
    public RecommendationResponse recommendByMood(@Valid @RequestBody MoodRequest request) {
        return recommendationService.getRecommendations(request.userId(), request.text());
    }

    @GetMapping("/songs")
    public List<Song> songs() {
        return recommendationService.getAllSongs();
    }
}
