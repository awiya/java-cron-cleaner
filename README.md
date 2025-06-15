# Java Cron Cleaner

**Petite application Java (Spring Boot & Quartz) pour illustrer un traitement planifié (cronjob) visant à nettoyer automatiquement un dossier des fichiers “anciens”.**

---

## 📋 Description

Ce projet montre comment mettre en place, en Java, une tâche planifiée qui s’exécute périodiquement (cron) pour supprimer tous les fichiers dont la dernière modification est plus ancienne qu’un certain nombre de jours. L’application :

- Scanne un dossier configurable (par défaut : `/tmp/java-cron-cleaner`).
- Compare la date de dernière modification de chaque fichier avec un seuil (configurable, par défaut : 7 jours).
- Supprime automatiquement les fichiers dépassant ce seuil.
- Logge à la console et/ou dans un fichier (UTF-8) le début et la fin de chaque exécution, ainsi que les fichiers supprimés ou les éventuelles erreurs.

L’intérêt de ce projet est double :
1. Illustrer l’usage de Spring Boot pour “scheduler” une tâche cron (avec Quartz).
2. Présenter une architecture (option hexagonale) propre à découpler la logique métier (domain) des aspects d’infrastructure (accès disque, horloge système, logging).

---

## 🔧 Fonctionnalités principales

- **Planification** : Exécution automatique selon une expression cron paramétrable (`application.properties`).
- **Nettoyage intelligent** : Suppression uniquement des fichiers “âgés” d’un certain nombre de jours.
- **Paramétrage via `application.properties`** :
    - `croncleaner.target.folder` : le chemin du dossier à nettoyer.
    - `croncleaner.retention.days` : nombre de jours de rétention avant suppression.
    - `croncleaner.schedule.cron` : expression cron (format Spring) pour la planification.
- **Logging** :
    - Affichage du moment “Début du cron…” et “Fin du cron…” à chaque exécution.
    - Détail des fichiers supprimés avec date de dernière modification.
    - Encodage UTF-8 pour conserver les accents et caractères spéciaux.
- **Tests unitaires** (disponibles dans `src/test/java`) :
    - Vérification de la détection et de la suppression d’un fichier “ancien” (avec ossature Mockito/JUnit ou test en mémoire).

---

## ⚙️ Technologies & dépendances

- **Java 17+** 
- **Spring Boot 3.x**
    - Starter Quartz (`spring-boot-starter-quartz`) ou Starter Scheduling (`spring-boot-starter-scheduling`).
    - Starter Test (`spring-boot-starter-test`) pour les tests unitaires.
- **Maven 3.8+** pour la compilation et le packaging.
- **SLF4J + Logback** pour le logging (configuré en UTF-8).
- **Quartz 2.5.0** (via Spring Boot) pour la planification interne.
- **Architecture hexagonale (facultatif)** pour séparer :
    - `domain/` (logique métier pure).
    - `application/port` (interfaces entrantes et sortantes).
    - `adapter/in` (Scheduling + Spring).
    - `adapter/out` (FileSystem, Clock, Logging).

---

## 🚀 Installation & exécution

1. **Cloner le dépôt**
   ```bash
   git clone https://github.com/aidihia/java-cron-cleaner.git
   cd java-cron-cleaner
