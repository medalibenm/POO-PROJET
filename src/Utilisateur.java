import java.util.ArrayList;
import java.util.List;

public abstract class Utilisateur {
    protected String nom;
    protected String prenom;
    protected String matricule;
    protected double reputation;
    protected Statut statut;
    protected Preferences preferences;
    protected Horaire horaire;
    protected TypeCourse typeC;
    protected Itineraire itineraire;
    protected List<Evaluation> evaluationsRecues = new ArrayList<>();
    protected boolean banni = false;

    public enum Statut { CHAUFFEUR, PASSAGER }
    public enum TypeCourse { ALLER_RETOUR, ALLER_SIMPLE, RETOUR_SIMPLE }

    public Utilisateur(String nom, String prenom, String matricule) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.reputation = 5.0;
    }

    public void devenirChauffeur(String nomVehicule, int placesDisponibles, Itineraire itineraire) {
        this.statut = Statut.CHAUFFEUR;
        this.itineraire = itineraire;
    }

    public void devenirPassager(Itineraire itineraire) {
        this.statut = Statut.PASSAGER;
        this.itineraire = itineraire;
    }

    public boolean estChauffeur() {
        return this.statut == Statut.CHAUFFEUR;
    }

    public boolean estPassager() {
        return this.statut == Statut.PASSAGER;
    }

    // Getters and setters
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getMatricule() { return matricule; }
    public double getReputation() { return reputation; }
    public Statut getStatut() { return statut; }
    public Preferences getPreferences() { return preferences; }
    public Horaire getHoraire() { return horaire; }
    public Itineraire getItineraire() { return itineraire; }
    public List<Evaluation> getEvaluationsRecues() { return evaluationsRecues; }
    public boolean estBanni() { return banni; }
    public void setBanni(boolean banni) { this.banni = banni; }
    public void setPreferences(Preferences preferences) { this.preferences = preferences; }
    public void setHoraire(Horaire horaire) { this.horaire = horaire; }
    public void setStatut(Statut statut) { this.statut = statut; }
    public void setItineraire(Itineraire itineraire) { this.itineraire = itineraire; }
}