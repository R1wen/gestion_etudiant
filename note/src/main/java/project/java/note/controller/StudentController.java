package project.java.note.controller;

import project.java.note.model.Note;
import project.java.note.model.Student;
import project.java.note.repository.StudentRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentrepo;

    @PostMapping
    public Student ajouterEtudiant(@RequestBody Student e) {
        return studentrepo.save(e);
    }

    @GetMapping
    public List<Student> getAllEtudiants() {
        return studentrepo.findAll();
    }

    @GetMapping("/{studentId}")
    public List<Note> getStudentsNote(@PathVariable Long studentId) {
        Student student = studentrepo.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        return student.getListNotes();       
    }
    
    @GetMapping("/moyenne/{studentId}")
    public String getMoyenne(@PathVariable Long studentId){
        Student student = studentrepo.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        return student.calculMoyenne();      
    }
    
    @DeleteMapping("/{studentId}")
    public String supprimerEtudiant(@PathVariable Long studentId) {
        studentrepo.deleteById(studentId);
        return "Etudiant supprimé avec succès!";
    }

    @PutMapping("/{studentId}")
    public String modifierEtudiant(@PathVariable Long studentId, @RequestBody Student studentnew){
        Student student = studentrepo.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        student.setNom(studentnew.getNom());
        student.setPrenom(studentnew.getPrenom());
        student.setDateNaissance(studentnew.getDateNaissance());
        studentrepo.save(student);
        return "Informations modifiées";
    }
}