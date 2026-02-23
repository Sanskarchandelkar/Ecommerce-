CREATE DATABASE IF NOT EXISTS music_reco;
USE music_reco;

CREATE TABLE IF NOT EXISTS user (
  user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(120) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  preferred_genre VARCHAR(80),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS mood (
  mood_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  mood_name VARCHAR(50) NOT NULL UNIQUE,
  description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS song (
  song_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(160) NOT NULL,
  artist VARCHAR(120) NOT NULL,
  genre VARCHAR(80),
  mood_tag VARCHAR(50),
  popularity_score DOUBLE DEFAULT 0.0,
  CONSTRAINT fk_song_mood_name FOREIGN KEY (mood_tag) REFERENCES mood(mood_name)
);

CREATE TABLE IF NOT EXISTS listening_history (
  history_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  song_id BIGINT NOT NULL,
  played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_history_user FOREIGN KEY (user_id) REFERENCES user(user_id),
  CONSTRAINT fk_history_song FOREIGN KEY (song_id) REFERENCES song(song_id)
);

INSERT INTO mood (mood_name, description) VALUES
('Happy', 'Energetic and positive emotional state'),
('Sad', 'Low-energy and reflective emotional state'),
('Relaxed', 'Calm and peaceful emotional state'),
('Angry', 'Intense and high-arousal emotional state')
ON DUPLICATE KEY UPDATE description = VALUES(description);
