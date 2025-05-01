import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class GestionCovoiturage {
    private List<Utilisateur> utilisateurs;
    private List<Course> courses;
    private static final double SEUIL_BANNISSEMENT = 2.0;

    public GestionCovoiturage() {
        this.utilisateurs = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }

    public boolean supprimerUtilisateur(String matricule) {
        return utilisateurs.removeIf(u -> u.getMatricule().equals(matricule));
    }

    public Utilisateur trouverUtilisateur(String matricule) {
        return utilisateurs.stream()
                .filter(u -> u.getMatricule().equals(matricule))
                .findFirst()
                .orElse(null);
    }

    public void ajouterCourse(Course course) {
        courses.add(course);
    }

    public Course trouverCourse(String idCourse) {
        return courses.stream()
                .filter(c -> c.getIdCourse().equals(idCourse))
                .findFirst()
                .orElse(null);
    }

    public List<Utilisateur> trouverChauffeursDisponibles(Utilisateur passager) {
        if (passager.getStatut() != Utilisateur.Statut.PASSAGER ||
                passager.getItineraire() == null ||
                passager.getHoraire() == null) {
            return Collections.emptyList();
        }

        return utilisateurs.stream()
                .filter(u -> !u.estBanni())
                .filter(u -> u.getStatut() == Utilisateur.Statut.CHAUFFEUR)
                .filter(u -> u.getItineraire() != null &&
                        u.getItineraire().estCompatibleAvec(passager.getItineraire()))
                .filter(u -> u.getHoraire() != null &&
                        u.getHoraire().chevauche(passager.getHoraire()))
                .filter(u -> u.getPreferences() == null ||
                        passager.getPreferences() == null ||
                        u.getPreferences().estCompatibleAvec(passager.getPreferences()))
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesEnCours() {
        return courses.stream()
                .filter(c -> c.getStatut() == Course.Statut.EN_COURS)
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesPlanifiees() {
        return courses.stream()
                .filter(c -> c.getStatut() == Course.Statut.PLANIFIEE)
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesDuJour(LocalDate date) {
        return courses.stream()
                .filter(c -> c.getDateDepart().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    public Map<String, Long> getNombreUtilisateursParType() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("Etudiants", utilisateurs.stream().filter(u -> u instanceof Etudiant).count());
        stats.put("Enseignants", utilisateurs.stream().filter(u -> u instanceof Enseignant).count());
        stats.put("ATS", utilisateurs.stream().filter(u -> u instanceof ATS).count());
        return stats;
    }

    public int bannirUtilisateursAvecMauvaiseReputation() {
        int count = 0;
        for (Utilisateur u : utilisateurs) {
            if (!u.estBanni() && u.getReputation() < SEUIL_BANNISSEMENT &&
                    !u.getEvaluationsRecues().isEmpty()) {
                u.setBanni(true);
                count++;
            }
        }
        return count;
    }
}