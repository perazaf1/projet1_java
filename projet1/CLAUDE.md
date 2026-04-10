# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run

Maven project, Java 25, no external dependencies.

```bash
# Compiler
mvn compile

# Lancer le programme
mvn exec:java -Dexec.mainClass="Main"

# Ou directement depuis IntelliJ IDEA : bouton Run sur Main.java
```

Il n'y a pas de tests automatisés dans ce projet.

## Contrainte pédagogique

Ce projet est un mini-projet de cours (ISEP A1 S2 — II.1102 Algorithme et Programmation). **Seule la syntaxe et les méthodes vues en cours sont autorisées** :

- Pas de streams, lambdas, generics avancés, ni méthodes utilitaires hors cours
- Collections : uniquement `ArrayList<>` avec boucles `for` classiques (pas de forEach)
- Pas de packages déclarés — toutes les classes sont dans le default package (`src/main/java/`)
- Identifiants et noms de méthodes en français

## Architecture

Système de réservation aérienne basé sur une hiérarchie d'héritage :

```
Personne (abstract)
├── Employe
│   ├── Pilote
│   └── PersonnelCabine
└── Passager
```

Classes indépendantes (pas d'héritage) : `Vol`, `Avion`, `Aeroport`, `Reservation`.

**Pattern CRUD** : chaque classe possède une `static ArrayList<T>` et quatre méthodes statiques : `ajouter()`, `getListe()`, `trouver(String)`, `supprimer(String)`.

**Relations clés** :
- `Passager` crée des `Reservation` via `reserverVol(Vol, String)` — cette méthode appelle `vol.ajouterPassager()`, `Reservation.ajouter()`, et `r.confirmerReservation()` en une seule opération.
- `Vol` lie `Avion`, `Pilote`, `ArrayList<PersonnelCabine>` et `ArrayList<Passager>`.
- `Reservation` a un compteur statique auto-incrémenté qui génère les IDs `RES001`, `RES002`, etc.
- `Aeroport` maintient deux listes séparées : `volsDepart` et `volsArrivee`.

Le point d'entrée `Main.java` contient un scénario de démonstration complet.
