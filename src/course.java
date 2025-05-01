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

    // Constructor
    public Course(Utilisateur chauffeur, Itineraire itineraire, TypeCourse typeCourse,
                  LocalDateTime dateDepart) {
        this.idCourse = UUID.randomUUID().toString(); // Generate a unique ID
        this.chauffeur = chauffeur;
        this.passagers = new ArrayList<>();
        this.typeCourse = typeCourse;
        this.dateDepart = dateDepart;
        this.statut = Statut.PLANIFIEE;
        this.itineraire = itineraire;
    }

    // Add a passenger to the course
    public boolean ajouterPassager(Utilisateur passager) {
        if (passager.getStatut() != Utilisateur.Statut.PASSAGER) {
            return false; // Only passengers can join
        }

        if (statut != Statut.PLANIFIEE) {
            return false; // Can only add passengers to planned courses
        }

        // Check compatibility
        if (!itineraire.estCompatibleAvec(passager.getItineraire())) {
            return false; // Itineraries are not compatible
        }

        // Check preferences
        if (chauffeur.getPreferences() != null &&
                passager.getPreferences() != null &&
                !chauffeur.getPreferences().estCompatibleAvec(passager.getPreferences())) {
            return false; // Preferences are not compatible
        }

        passagers.add(passager);
        return true;
    }

    // Start the course
    public boolean demarrer() {
        if (statut != Statut.PLANIFIEE) {
            return false;
        }

        statut = Statut.EN_COURS;
        return true;
    }

    // Complete the course
    public boolean terminer() {
        if (statut != Statut.EN_COURS) {
            return false;
        }

        statut = Statut.TERMINEE;
        dateArrivee = LocalDateTime.now();
        return true;
    }

    // Cancel the course
    public boolean annuler() {
        if (statut == Statut.TERMINEE) {
            return false; // Cannot cancel a completed course
        }

        statut = Statut.ANNULEE;
        return true;
    }

    // Getters and setters
    public String getIdCourse() {
        return idCourse;
    }

    public Utilisateur getChauffeur() {
        return chauffeur;
    }

    public List<Utilisateur> getPassagers() {
        return new ArrayList<>(passagers); // Return a copy
    }

    public TypeCourse getTypeCourse() {
        return typeCourse;
    }

    public LocalDateTime getDateDepart() {
        return dateDepart;
    }

    public LocalDateTime getDateArrivee() {
        return dateArrivee;
    }

    public Statut getStatut() {
        return statut;
    }

    public Itineraire getItineraire() {
        return itineraire;
    }

    // Enum for course status
    public enum Statut {
        PLANIFIEE, EN_COURS, TERMINEE, ANNULEE
    }

    // Enum for course type
    public enum TypeCourse {
        ALLER_RETOUR, ALLER_SIMPLE, RETOUR_SIMPLE
    }

    @Override
    public String toString() {
        return String.format("Course %s: %s -> %s passagers, statut: %s",
                idCourse, chauffeur.getNom(), passagers.size(), statut);
    }
}