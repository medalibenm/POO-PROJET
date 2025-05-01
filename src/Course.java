import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Course {
    private String idCourse;
    private Utilisateur chauffeur;
    private List<Utilisateur> passagers;
    private TypeCourse typeCourse;
    private LocalDateTime dateDepart;
    private LocalDateTime dateArrivee;
    private Statut statut;
    private Itineraire itineraire;

    public enum Statut {
        PLANIFIEE, EN_COURS, TERMINEE, ANNULEE
    }

    public enum TypeCourse {
        ALLER_RETOUR, ALLER_SIMPLE, RETOUR_SIMPLE
    }

    public Course(Utilisateur chauffeur, Itineraire itineraire, TypeCourse typeCourse, LocalDateTime dateDepart) {
        this.idCourse = UUID.randomUUID().toString();
        this.chauffeur = chauffeur;
        this.passagers = new ArrayList<>();
        this.typeCourse = typeCourse;
        this.dateDepart = dateDepart;
        this.statut = Statut.PLANIFIEE;
        this.itineraire = itineraire;
    }

    public boolean ajouterPassager(Utilisateur passager) {
        if (passager.getStatut() != Utilisateur.Statut.PASSAGER) {
            return false;
        }
        if (statut != Statut.PLANIFIEE) {
            return false;
        }
        if (!itineraire.estCompatibleAvec(passager.getItineraire())) {
            return false;
        }
        if (chauffeur.getPreferences() != null && passager.getPreferences() != null &&
                !chauffeur.getPreferences().estCompatibleAvec(passager.getPreferences())) {
            return false;
        }

        passagers.add(passager);
        return true;
    }

    public boolean demarrer() {
        if (statut != Statut.PLANIFIEE) {
            return false;
        }
        statut = Statut.EN_COURS;
        return true;
    }

    public boolean terminer() {
        if (statut != Statut.EN_COURS) {
            return false;
        }
        statut = Statut.TERMINEE;
        dateArrivee = LocalDateTime.now();
        return true;
    }

    public boolean annuler() {
        if (statut == Statut.TERMINEE) {
            return false;
        }
        statut = Statut.ANNULEE;
        return true;
    }

    // Getters
    public String getIdCourse() { return idCourse; }
    public Utilisateur getChauffeur() { return chauffeur; }
    public List<Utilisateur> getPassagers() { return new ArrayList<>(passagers); }
    public TypeCourse getTypeCourse() { return typeCourse; }
    public LocalDateTime getDateDepart() { return dateDepart; }
    public LocalDateTime getDateArrivee() { return dateArrivee; }
    public Statut getStatut() { return statut; }
    public Itineraire getItineraire() { return itineraire; }
}