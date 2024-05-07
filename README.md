# JAVA-Quiz-Game
- This project is a JAVA Console App
- It is programmed using core JAVA, using Object-Oriented Programming(OOP)
- OOP helps to keep the JAVA code DRY `Don't Repeat Yourself`, and makes the code easier to maintain, modify and debug

## Motivation
The true inspiration behind this project is the famous Quiz game: **'Who wants to be Millionaire'** which is an international television game show, where contestants tackle a series of multiple-choice questions to win large cash prizes.

## About the Game
- Each game can be played by a single player at a time
- At the start of the game, user is given choice to select their difficulty level: `Easy` or `Hard`
- Both difficulty levels game consist of 3 rounds. Easy Game has 3 questions per round while Hard one has 5 questions per round
- Player must choose the most correct answer choice of the 4 possible answer choices provided along with question
- After the player chooses their answer for any given question, they are allowed to confirm that the choice selected is their final answer. If the user doesn't confirm their choice then they can change their answer to another option
- Once the user incorrectly answers a question they will lose the game and walk
away with no money
- A player cannot quit a game in between rounds, however at the end of each round, they can choose to walk away with the cash prize or move on to the next round
- Once they complete the last round they will win the game and walk away with 1 million dollars
- For both difficulty options, players are allowed 3 lifelines which are: `50/50`, `Ask the Audience`, `Phone a friend`
- The above lifelines are only available in round 2 and round 3 for players who chose the HARD option, whereas all lifelines are available from round 1 to players who chose the EASY option.

## Softwares Used
- [Java Runtime Environment - 1.8.0 (JRE)](https://www.java.com/en/download/manual.jsp)
- [Java Development Kit - 17.0.2 (JDK)](https://www.oracle.com/java/technologies/downloads/)
- [Apache Netbeans - 13 - IDE](https://netbeans.apache.org/download/index.html)

## Start the Game
- Install all the above mentioned softwares
- Clone the repository
- Open this project in `Apache Netbeans`
- Then open file `JAVA-Quiz-Game\src\javaquizgame\JavaQuizGame.java`
- The above file contains the main JAVA function
- Click on `Run Project` or `F6` to start the game, you should be able to see initial screen as below:

```bash
********* Who Wants to Be a Millionaire? ********

* Please choose an approppriate number from below menu *
1. Start the game
2. View the rules of the game
3. Exit the game
```

## Input Data
You can find below 2 input data files in root folder of this project
- **game_rules.txt**: This file contains game rules
- **question_bank.txt**: This file contains all the questions used in the Quiz, along with the 4 options and 1 correct answer

## Packages Created
- `javaQuizGame` : This package contains the main class `JavaQuizGame`(starting point of this project)
- `data` : This package is responsible for storing all input data in `Data` class
- `consoleInterface` : This package handles all activities of displaying as well as accepting data from the user. It also handles the starting lauch screen of the game
- `businessLogic` : This package is the heart of this project. It take cares of all logical parts with classes like: Question Bank, Type of Game(Easy/Hard), User, Rounds, Lifelines
- `exceptions` : Handles exceptions where game needs to end(`EndGameException`)

## Author
[Manali Darji](https://www.linkedin.com/in/manalidarji/)