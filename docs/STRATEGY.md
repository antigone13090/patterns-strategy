# Pattern Stratégie — Explication détaillée

## 1) Intention
Définir une famille de comportements, les encapsuler, et les rendre interchangeables.
Ici : voler / cancaner.

## 2) Problème (ce qu’on veut éviter)
### 2.1 Explosion de sous-classes
Avec l’héritage, chaque combinaison “vol + cancan” pousse à créer une nouvelle sous-classe.

### 2.2 Couplage et rigidité
Modifier un comportement oblige à modifier des classes existantes.

### 2.3 Comportement figé
On ne peut pas changer facilement “en cours d’exécution” la manière de voler/cancaner.

## 3) Solution : composition + délégation
### 3.1 Extraction des comportements
Interfaces :
- `FlyBehavior`
- `QuackBehavior`

Implémentations (exemples du projet) :
- Vol : `FlyWithWings`, `FlyNoWay`, `FlyRocketPowered`
- Cancan : `Quack`, `Squeak`, `MuteQuack`

### 3.2 Duck contient des stratégies
Dans `Duck` :
- `FlyBehavior flyBehavior;`
- `QuackBehavior quackBehavior;`

Et délègue :
- `performFly()` -> `flyBehavior.fly()`
- `performQuack()` -> `quackBehavior.quack()`

### 3.3 Changement à l’exécution
`Duck` expose :
- `setFlyBehavior(...)`
- `setQuackBehavior(...)`

Ce qui permet :
- au départ : `FlyNoWay`
- ensuite : `FlyRocketPowered`

## 4) Cartographie du projet
Package principal :
`src/main/java/headfirst/designpatterns/strategy/`

- Contexte : `Duck`
- Interfaces : `FlyBehavior`, `QuackBehavior`
- Stratégies : `FlyWithWings`, `FlyNoWay`, `FlyRocketPowered`, `Quack`, `Squeak`, `MuteQuack`
- Démos : `MiniDuckSimulator`, `MiniDuckSimulator1`

## 5) Lecture de la sortie console
- `Quack` : stratégie `Quack`
- `Squeak` : stratégie `Squeak`
- `<< Silence >>` : stratégie `MuteQuack`
- `I can't fly` : stratégie `FlyNoWay`
- `I'm flying with a rocket` : après changement vers `FlyRocketPowered`

## 6) Principes OO
- Encapsuler ce qui varie
- Préférer la composition à l’héritage
- Programmer vers une interface

## 7) Challenge (camera)
Package :
`src/main/java/headfirst/designpatterns/strategy/challenge/`
Même idée : stratégie de partage via `ShareStrategy` + `Email`, `Txt`, `Social`.
