package project.java.note.repository;

import project.java.note.model.Note;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note , Long> { 
}
