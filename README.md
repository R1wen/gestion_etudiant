# Gestion des étudiants et des notes - API REST

Cette application expose une API REST permettant de gérer les étudiants et leurs notes.

## Fonctionnalités

- Ajouter, modifier, supprimer des étudiants
- Ajouter, modifier, supprimer des notes pour chaque étudiant
- Calculer la moyenne générale d’un étudiant

## Endpoints API

### Étudiants (`/student`)

- **GET `/student`**  
  Récupère la liste de tous les étudiants.

- **POST `/student`**  
  Crée un nouvel étudiant.  
  Exemple de corps :
  ```json
  {
    "nom": "Nom",
    "prenom": "Prénom",
    "dateNaissance": "YYYY-MM-DD"
  }
  ```

- **GET `/student/{studentId}`**  
  Récupère la liste des notes de l’étudiant correspondant à l’id `studentId`.

- **GET `/student/moyenne/{studentId}`**  
  Calcule et retourne la moyenne générale de l’étudiant correspondant à l’id `studentId`.

- **PUT `/student/{studentId}`**  
  Modifie les informations d’un étudiant.  
  Exemple de corps :
  ```json
  {
    "nom": "NouveauNom",
    "prenom": "NouveauPrenom",
    "dateNaissance": "YYYY-MM-DD"
  }
  ```

- **DELETE `/student/{studentId}`**  
  Supprime l’étudiant correspondant à l’id `studentId`.

---

### Notes (`/notes`)

- **POST `/notes/{studentId}`**  
  Ajoute une note à l’étudiant correspondant à l’id `studentId`.  
  Exemple de corps :
  ```json
  {
    "matiere": "NomMatiere",
    "valeur": 15.5
  }
  ```

- **DELETE `/notes/{noteId}`**  
  Supprime la note identifiée par l’id `noteId`.

- **PUT `/notes/{noteId}`**  
  Modifie la valeur d’une note.  
  Exemple de corps :
  ```json
  {
    "valeur": 17.0
  }
  ```

---

## Démarrage rapide

1. Cloner le projet
2. Configurer la base de données dans `note/src/main/resources/application.properties`
3. Lancer le backend Spring Boot (`note/`)
4. Ouvrir le frontend (`frontend/`) dans un serveur local

---

## Licence

Ce projet est sous licence MIT

## Auteur

Komlan Erwin OKE