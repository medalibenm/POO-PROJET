import java.util.ArrayList;
import java.util.List;

public abstract class utilisateur {
    protected String nom;
    protected String prenom;
    protected String matricule;
    protected double reputation;
    protected enum Statut {
        CHAUFFEUR, PASSAGER

    }

    protected Itineraire itineraire;


    public utilisateur(String nom, String prenom, String matricule) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.reputation = 0.0;
    }
    
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getMatricule() { return matricule; }
    public double getReputation() { return reputation; }
}

