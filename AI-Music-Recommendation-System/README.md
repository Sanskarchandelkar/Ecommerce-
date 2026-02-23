# AI-Based Personalized Music Recommendation System with Mood Detection

This project is a full-stack starter implementation of a **Mood-Aware Music Recommendation System**.

It combines:
- **React frontend** for user interaction.
- **Spring Boot backend** for user/song/history management and recommendation orchestration.
- **Python (Flask) ML API** for mood detection using **TF-IDF + Logistic Regression**.
- **MySQL schema** for storing users, songs, moods, and listening history.

## High-Level Workflow

1. User logs in and enters a mood sentence (e.g., "I feel calm and peaceful").
2. Frontend sends mood text to Spring Boot backend.
3. Backend calls Python ML API `/predict-mood`.
4. ML API returns mood class (Happy/Sad/Relaxed/Angry).
5. Backend uses hybrid recommendation logic:
   - mood-based filtering,
   - user preference/history weighted ranking.
6. Recommended songs are returned to frontend.

## Folder Structure

```text
AI-Music-Recommendation-System/
├── backend/
│   ├── pom.xml
│   └── src/main/java/com/music/reco/... (Spring Boot API)
├── frontend/
│   ├── package.json
│   └── src/... (React app)
├── ml-api/
│   ├── app.py
│   ├── train_model.py
│   ├── requirements.txt
│   └── model/ (generated)
├── database/
│   └── schema.sql
└── docs/
    ├── ER_DIAGRAM.md
    └── UML_DIAGRAMS.md
```

## Quick Start

### 1) Database

Run SQL from `database/schema.sql` in MySQL.

### 2) ML API

```bash
cd ml-api
python3 -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
python train_model.py
python app.py
```

ML API runs on `http://localhost:5001`.

### 3) Backend

```bash
cd backend
mvn spring-boot:run
```

Backend runs on `http://localhost:8080`.

### 4) Frontend

```bash
cd frontend
npm install
npm start
```

Frontend runs on `http://localhost:3000`.

## Core API Endpoints

### Backend (Spring Boot)
- `POST /api/recommendations/mood` -> detect mood + fetch recommendations.
- `GET /api/recommendations/user/{userId}` -> personalized recommendations.
- `GET /api/songs` -> list all songs.

### ML API (Flask)
- `POST /predict-mood` with `{ "text": "..." }`

## Future Enhancements

- Collaborative filtering integration.
- Streaming provider API (Spotify/YouTube Music) integration.
- Real-time contextual features (time/weather/activity).
- Mobile app (React Native / Flutter).
