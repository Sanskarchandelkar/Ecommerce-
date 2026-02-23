import React, { useState } from "react";

const API_URL = "http://localhost:8080/api/recommendations/mood";

export default function App() {
  const [text, setText] = useState("");
  const [result, setResult] = useState(null);

  const submitMood = async (e) => {
    e.preventDefault();
    const response = await fetch(API_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ userId: 1, text })
    });
    const data = await response.json();
    setResult(data);
  };

  return (
    <main style={{ maxWidth: 700, margin: "2rem auto", fontFamily: "Arial" }}>
      <h1>Mood-Based Music Recommender</h1>
      <form onSubmit={submitMood}>
        <label htmlFor="mood-input">How are you feeling right now?</label>
        <textarea
          id="mood-input"
          rows="4"
          value={text}
          onChange={(e) => setText(e.target.value)}
          style={{ width: "100%", marginTop: 8 }}
          placeholder="Example: I feel calm and peaceful today"
        />
        <button type="submit" style={{ marginTop: 12 }}>Get Recommendations</button>
      </form>

      {result && (
        <section style={{ marginTop: 24 }}>
          <h2>Detected Mood: {result.detectedMood}</h2>
          <ul>
            {result.songs?.map((song) => (
              <li key={song.songId}>
                {song.title} - {song.artist} ({song.genre})
              </li>
            ))}
          </ul>
        </section>
      )}
    </main>
  );
}
