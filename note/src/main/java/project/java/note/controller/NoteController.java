package project.java.note.controller;

import project.java.note.model.Note;
import project.java.note.model.Student;
import project.java.note.repository.NoteRepository;
import project.java.note.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteRepository noterepo;
    @Autowired
    private StudentRepository studentrepo;

    public static class NoteValeurDTO {
        public double valeur;
    }

    @PostMapping("/{studentId}")
    public Note ajouterNote(@PathVariable Long studentId,@RequestBody Note note) {
        Student student = studentrepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        student.ajouterNote(note);
        noterepo.save(note);
        studentrepo.save(student);
        return note;
    }

    @DeleteMapping("/{noteId}")
    public void supprimerNote(@PathVariable Long noteId) {
        noterepo.deleteById(noteId);
    }

    @PutMapping("/{noteId}")
    public Note modifierNote(@PathVariable Long noteId, @RequestBody NoteValeurDTO dto) {
        Note note = noterepo.findById(noteId).orElseThrow();
        note.setValeur(dto.valeur);
        return noterepo.save(note);
    }
}
