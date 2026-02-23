# UML Diagrams (Text Representation)

## 1) Use Case Diagram

### Actors
- User
- Admin

### User Use Cases
- Register
- Login
- Enter Mood
- Get Recommendations
- View Songs
- View History

### Admin Use Cases
- Add Song
- Update Song
- Delete Song

```text
                +---------------------+
                |      Music System   |
                +---------------------+
                 |        |         |
                 |        |         |
              Register   Login   Enter Mood
                 |                    |
                 |                    |
                Get Recommendations
                         |
                     View Songs
                         |
                     View History

Admin:
   Add Song
   Update Song
   Delete Song
```

## 2) Class Diagram

```text
+------------------+
|      User        |
+------------------+
| userId           |
| name             |
| email            |
| password         |
| preferredGenre   |
+------------------+
| register()       |
| login()          |
+------------------+

        |
        | 1
        |
        | M
+------------------+
| ListeningHistory |
+------------------+
| historyId        |
| playedAt         |
+------------------+
| saveHistory()    |
+------------------+

+------------------+
|      Song        |
+------------------+
| songId           |
| title            |
| artist           |
| genre            |
| moodTag          |
+------------------+
| getSongDetails() |
+------------------+

+--------------------------+
|  RecommendationService   |
+--------------------------+
| getRecommendations()     |
| filterByMood()           |
| personalizeResults()     |
+--------------------------+
```
