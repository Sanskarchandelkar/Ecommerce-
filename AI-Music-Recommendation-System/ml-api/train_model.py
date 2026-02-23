from pathlib import Path

import joblib
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.linear_model import LogisticRegression
from sklearn.pipeline import Pipeline


def get_training_data():
    samples = [
        ("I feel amazing and excited today", "Happy"),
        ("Everything is awesome and joyful", "Happy"),
        ("I want energetic upbeat songs", "Happy"),
        ("I am feeling down and lonely", "Sad"),
        ("I feel heartbroken and tired", "Sad"),
        ("play something emotional and slow", "Sad"),
        ("I am calm and peaceful", "Relaxed"),
        ("Need soft and chill music", "Relaxed"),
        ("I want meditative soothing tracks", "Relaxed"),
        ("I am furious and frustrated", "Angry"),
        ("I feel intense rage right now", "Angry"),
        ("play loud aggressive music", "Angry"),
    ]
    texts, labels = zip(*samples)
    return list(texts), list(labels)


def train_and_save(model_path: Path):
    texts, labels = get_training_data()

    model = Pipeline(
        [
            ("tfidf", TfidfVectorizer(ngram_range=(1, 2))),
            ("clf", LogisticRegression(max_iter=300, random_state=42)),
        ]
    )

    model.fit(texts, labels)
    model_path.parent.mkdir(parents=True, exist_ok=True)
    joblib.dump(model, model_path)
    print(f"Saved model to {model_path}")


if __name__ == "__main__":
    train_and_save(Path("model/mood_model.joblib"))
