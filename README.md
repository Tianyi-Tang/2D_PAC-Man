# Paws&Pumpkins
Paws and Pumpkins is a captivating 2D PAC-MAN game where the player controls a cute corgi dog called Paws. The objective is to collect candy in a haunted house and exit the room after gathering all the candy. Ghosts roam the room, searching for the player, who must evade the ghosts and avoid touching the stationary spiders. During the game, the player can also collect pumpkin heads that appear randomly to earn higher scores. The game ends when the player successfully leaves the room, is caught by a ghost, or the score becomes negative. At the end of the game, the player's score is displayed. The game offers three difficulty levels: easy, medium, and hard.

## Project Demonstration
When you enter the game you will see the Main Menu with start button\n
<img src="/Game_demonstrate_img/Game_start.png" alt="Game_start" width="300" height="200">

After clicking the start button, you can chose any difficulty you want
![plot](./Game_demonstrate_img/Difficulty_level.pdf)

The game will load and display the game level base on the difficulty you chose
![plot](./Game_demonstrate_img/Game_scene.pdf)

After you finished you game, you will able to see your performance
![plot](./Game_demonstrate_img/Fianll_score.pdf)

## Getting Started
### Perequisites
- Java JDK (Version 20 or above)
- Maven (Version 3.6.0 or above)
- Git (latest stable version)

## Installation
#### Clone the project
```bash
git clone https://github.com/Tianyi-Tang/Paws-Pumpkins.git
```

#### Go to the project directory
```bash
cd .\pawsandpumpkins\
```

## Build and Run
#### Clean and Install Dependencies
```bash
mvn clean install
```

#### Compile the project
```bash
mvn clean compile
```

#### Run the Game
```bash
mvn exec:java
```
