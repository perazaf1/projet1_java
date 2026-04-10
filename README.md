# Système de réservation aérienne

Mini-projet Java — cours II.1102 Algorithme et Programmation (ISEP A1 S2).

## Description

Application de gestion de réservations aériennes en Java, développée selon les principes de la programmation orientée objet : héritage, encapsulation, polymorphisme et classes abstraites.

## Structure des classes

```
Personne (abstraite)
├── Employe
│   ├── Pilote
│   └── PersonnelCabine
└── Passager

Vol
Avion
Aeroport
Reservation
```

Chaque classe dispose de méthodes CRUD via une liste statique (`ArrayList`).

## Lancer le projet

Depuis IntelliJ IDEA, exécuter la classe `Main`. Depuis le terminal :

```bash
mvn compile
mvn exec:java -Dexec.mainClass="Main"
```

## Lancer les tests

```bash
mvn test
```

Les tests unitaires (JUnit 5) couvrent les classes `Avion`, `Aeroport`, `Pilote`, `PersonnelCabine`, `Passager`, `Reservation` et `Vol`.

## Prérequis

- Java 25
- Maven 3.x
