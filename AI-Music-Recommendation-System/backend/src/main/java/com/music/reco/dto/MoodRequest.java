package com.music.reco.dto;

import jakarta.validation.constraints.NotBlank;

public record MoodRequest(Long userId, @NotBlank String text) {
}
