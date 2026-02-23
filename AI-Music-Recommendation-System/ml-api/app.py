from pathlib import Path

import joblib
from flask import Flask, jsonify, request

MODEL_PATH = Path("model/mood_model.joblib")

app = Flask(__name__)

if not MODEL_PATH.exists():
    raise RuntimeError("Model file not found. Run: python train_model.py")

model = joblib.load(MODEL_PATH)


@app.get("/health")
def health():
    return jsonify({"status": "ok"})


@app.post("/predict-mood")
def predict_mood():
    payload = request.get_json(silent=True) or {}
    text = payload.get("text", "").strip()

    if not text:
        return jsonify({"error": "text is required"}), 400

    prediction = model.predict([text])[0]
    probabilities = {}
    if hasattr(model, "predict_proba"):
        classes = model.classes_
        probs = model.predict_proba([text])[0]
        probabilities = {c: float(p) for c, p in zip(classes, probs)}

    return jsonify({"mood": prediction, "probabilities": probabilities})


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5001, debug=True)
