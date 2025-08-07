package project.java.note.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Note {
    @Id
    @GeneratedValue
    private Long id;
    private String matiere;
    private double valeur;

    public Note(){}

    public Note(String matiere, double valeur) {
        this.matiere = matiere;
        this.valeur = valeur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public String afficherNote(){
        return "Matiere: " + matiere + " | note: " + valeur;
    }
}