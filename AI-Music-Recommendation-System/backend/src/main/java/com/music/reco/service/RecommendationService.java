package com.music.reco.service;

import com.music.reco.dto.RecommendationResponse;
import com.music.reco.model.Song;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecommendationService {
    private final RestClient restClient;

    private final List<Song> catalog = List.of(
            new Song(1L, "Sunrise Energy", "Nova", "Pop", "Happy", 0.88),
            new Song(2L, "Silent Rain", "Ari", "Acoustic", "Sad", 0.72),
            new Song(3L, "Ocean Breath", "ZenWave", "Ambient", "Relaxed", 0.67),
            new Song(4L, "Fireline", "Riot Pulse", "Rock", "Angry", 0.79),
            new Song(5L, "Golden Hour", "Skyline", "Pop", "Happy", 0.81),
            new Song(6L, "Night Letters", "Ember", "Indie", "Sad", 0.70),
            new Song(7L, "Calm Drift", "Mellow Tide", "Ambient", "Relaxed", 0.86)
    );

    public RecommendationService(@Value("${ml.api.url}") String mlApiUrl) {
        this.restClient = RestClient.builder().baseUrl(mlApiUrl).build();
    }

    public RecommendationResponse getRecommendations(Long userId, String text) {
        Map<?, ?> prediction = restClient.post()
                .uri("/predict-mood")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("text", text))
                .retrieve()
                .body(Map.class);

        String mood = String.valueOf(prediction.get("mood"));

        List<Song> songs = catalog.stream()
                .filter(song -> song.moodTag().equalsIgnoreCase(mood))
                .sorted(Comparator.comparingDouble(Song::popularityScore).reversed())
                .limit(5)
                .collect(Collectors.toList());

        return new RecommendationResponse(mood, songs);
    }

    public List<Song> getAllSongs() {
        return catalog;
    }
}
