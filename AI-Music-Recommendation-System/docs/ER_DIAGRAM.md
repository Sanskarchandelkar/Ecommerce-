# ER Diagram (Structured Text)

## Entities

### 1) USER
- user_id (PK)
- name
- email
- password
- preferred_genre
- created_at

### 2) SONG
- song_id (PK)
- title
- artist
- genre
- mood_tag
- popularity_score

### 3) MOOD
- mood_id (PK)
- mood_name
- description

### 4) LISTENING_HISTORY
- history_id (PK)
- user_id (FK -> USER.user_id)
- song_id (FK -> SONG.song_id)
- played_at

## Relationships
- USER (1) ---- (M) LISTENING_HISTORY
- SONG (1) ---- (M) LISTENING_HISTORY
- MOOD (1) ---- (M) SONG (via SONG.mood_tag -> MOOD.mood_name)

## Text Diagram

```text
USER
-----
user_id (PK)
name
email
password
preferred_genre
created_at

        1
        |
        |
        M
LISTENING_HISTORY
-----------------
history_id (PK)
user_id (FK)
song_id (FK)
played_at
        M
        |
        |
        1

SONG
-----
song_id (PK)
title
artist
genre
mood_tag
popularity_score
        |
        |
        M
MOOD
-----
mood_id (PK)
mood_name
description
```
