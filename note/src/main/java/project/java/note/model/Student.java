package project.java.note.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity //Definie la classe comme une table dans la base de donnée
public class Student {
    @Id //Definir le prochain attribue comme la clé primaire
    @GeneratedValue //Auto-incrémation de l'ID
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    @OneToMany //L'utilisateur peut avoir plusieurs notes et composition
    @JoinColumn(name="student_id") //liaison vas représenter la clée étrangère dans note
    private List<Note> listNotes = new ArrayList<>(); //Qui va stocker plusieurs objet "Note"

    public Student() {} //Constructeur par défaut

    public Student(String nom, String prenom, LocalDate dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public List<Note> getListNotes() {
        return listNotes;
    }

    public void setListNotes(List<Note> listNotes) {
        this.listNotes = listNotes;
    }

    public void ajouterNote(Note note) {
        listNotes.add(note);
    }

    public String calculMoyenne(){
        double moyenne = 0;
        for (Note note : listNotes){
           moyenne += note.getValeur();
        }
        moyenne = moyenne/listNotes.size();
        return "La moyenne générale de " + nom + " " + prenom + " est: " + moyenne;
    }
}