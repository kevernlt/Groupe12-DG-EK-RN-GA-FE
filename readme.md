# "Othello"

## This project is part of "SAÉ" Project, a school project for UniCaen / BUT Informatique.

### Description

In facts, the game is not a real implementation of the Othello game, it's more a [Yinsh](https://yinsh.net/) game implementation.
Subject and rules are available [here](subject.pdf).

### Implement project in IDE

After cloning the project, import pom.xml file from root. It will create one main project and two subprojects (coordinates system and main game logic system).

### Run the project

The main class is located in "Othello" subproject, in `iut.gon.sae201.g12` package. The class is called "Main". You can run it to start the game.
The class `iut.gon.sae201.g12.MainIA` is not aimed to work as well, it's only a shortcut to start with an IA opponent. You can run it to start the game with an IA opponent.

### Authors
Group 12:

- Nils Rennesson
- Aurelien Giguerre
- Ernault Kevin
- Duteurtre Gabriel
- (Fraisse Eliot)

https://redmine-etu.unicaen.fr/projects/g12-dq-ek-rn-ga-fe

##### La partie expert comptable :
En executant dans le projet mère : `mvn test`
Puis `mvn site` pour générer le rapport de test dans `target/site/index.html`
Avec une interface web pour visualiser les résultats des tests unitaires0.