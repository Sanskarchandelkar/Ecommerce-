package com.music.reco.dto;

import com.music.reco.model.Song;

import java.util.List;

public record RecommendationResponse(String detectedMood, List<Song> songs) {
}
