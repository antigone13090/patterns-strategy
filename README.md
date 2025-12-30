# Strategy (Head First Design Patterns) — Projet IntelliJ/Gradle

## Objectif
Illustrer le **pattern Stratégie** (chapitre 1 du livre *Design Patterns – Tête la première*) avec :
- `MiniDuckSimulator` / `MiniDuckSimulator1`
- le challenge “Camera” dans `src/main/java/headfirst/designpatterns/strategy/challenge/`

## Sujet (problème)
Une classe `Duck` doit avoir des comportements qui varient : **voler** et **cancaner**.  
L’héritage mène vite à :
- explosion de sous-classes
- duplication
- rigidité
- difficulté à changer un comportement **à l’exécution**

## Solution (pattern Stratégie)
On **encapsule** les comportements dans des stratégies :
- `FlyBehavior`
- `QuackBehavior`

`Duck` **contient** ces stratégies et **délègue** :
- `performFly()` -> `flyBehavior.fly()`
- `performQuack()` -> `quackBehavior.quack()`

Changement à l’exécution possible :
- `setFlyBehavior(new FlyRocketPowered())`

## Exécuter
```bash
./gradlew run
