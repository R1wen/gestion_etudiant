package project.java.note.repository;

import project.java.note.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;

//ORM : utiliser des methodes simplifiées pour communiquer avec la base de donnée
public interface StudentRepository extends JpaRepository<Student , Long> {
     
}