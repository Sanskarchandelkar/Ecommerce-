package com.music.reco.model;

public record Song(Long songId, String title, String artist, String genre, String moodTag, double popularityScore) {
}
