import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite représentant un utilisateur du système de covoiturage
 */
public abstract class Utilisateur {
    // Attributs de base
    protected String nom;
    protected String prenom;
    protected String matricule;
    protected double reputation;

    // Attributs pour le covoiturage
    protected Statut statut;
    protected Itineraire itineraire;
    protected Preferences preferences;
    protected Horaire horaire;
    protected TypeCourse typeCourse;
    protected boolean estBanni;

    // Liste des évaluations reçues
    protected List<Evaluation> evaluationsRecues;

    /**
     * Constructeur pour initialiser un utilisateur
     */
    public Utilisateur(String nom, String prenom, String matricule) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.reputation = 5.0; // Réputation initiale neutre (sur 5)
        this.statut = null; // Statut non défini par défaut
        this.estBanni = false;
        this.evaluationsRecues = new ArrayList<>();
    }

    /**
     * Méthode pour évaluer un autre utilisateur
     */
    public void evaluerUtilisateur(Utilisateur utilisateur, int note, String commentaire) {
        if (note < 1 || note > 5) {
            throw new IllegalArgumentException("La note doit être entre 1 et 5");
        }

        Evaluation evaluation = new Evaluation(this, utilisateur, note, commentaire);
        utilisateur.ajouterEvaluation(evaluation);
    }

    /**
     * Méthode pour ajouter une évaluation reçue
     */
    private void ajouterEvaluation(Evaluation evaluation) {
        this.evaluationsRecues.add(evaluation);
        mettreAJourReputation();
    }

    /**
     * Méthode pour mettre à jour la réputation en fonction des évaluations
     */
    private void mettreAJourReputation() {
        if (evaluationsRecues.isEmpty()) {
            return;
        }

        double somme = 0;
        for (Evaluation evaluation : evaluationsRecues) {
            somme += evaluation.getNote();
        }

        this.reputation = somme / evaluationsRecues.size();
    }

    /**
     * Méthode abstraite à implémenter par chaque sous-classe
     * pour identifier le type d'utilisateur
     */
    public abstract String getTypeUtilisateur();

    // Getters et setters

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public double getReputation() {
        return reputation;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Itineraire getItineraire() {
        return itineraire;
    }

    public void setItineraire(Itineraire itineraire) {
        this.itineraire = itineraire;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public Horaire getHoraire() {
        return horaire;
    }

    public void setHoraire(Horaire horaire) {
        this.horaire = horaire;
    }

    public TypeCourse getTypeCourse() {
        return typeCourse;
    }

    public void setTypeCourse(TypeCourse typeCourse) {
        this.typeCourse = typeCourse;
    }

    public boolean estBanni() {
        return estBanni;
    }

    public void setBanni(boolean estBanni) {
        this.estBanni = estBanni;
    }

    public List<Evaluation> getEvaluationsRecues() {
        return new ArrayList<>(evaluationsRecues);
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s, %s)", prenom, nom, getTypeUtilisateur(), matricule);
    }

    // Enums internes
    public enum Statut {
        CHAUFFEUR, PASSAGER
    }

    public enum TypeCourse {
        ALLER_RETOUR, ALLER_SIMPLE, RETOUR_SIMPLE
    }
}