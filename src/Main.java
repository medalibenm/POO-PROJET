import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;

public class Main {
    public static void main(String[] args) {
        // Initialize system
        GestionCovoiturage gestion = new GestionCovoiturage();

        // Create users
        Etudiant etudiant1 = new Etudiant("Dupont", "Jean", "E12345", 2022, "Informatique", "IA");
        Etudiant etudiant2 = new Etudiant("Martin", "Marie", "E23456", 2021, "Informatique", "SD");

        // Create locations
        Localisation usthb = new Localisation("USTHB", 36.7128, 3.1917);
        Localisation algerCentre = new Localisation("Alger Centre", 36.7539, 3.0589);

        // Create itineraries
        Itineraire itineraireChauffeur = new Itineraire(algerCentre);
        itineraireChauffeur.ajouterPointArret(usthb);

        Itineraire itinerairePassager = new Itineraire(algerCentre);
        itinerairePassager.ajouterPointArret(usthb);

        // Set up schedules
        Horaire horaire = new Horaire();
        horaire.ajouterPlageHoraire(DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(16, 0));

        // Configure users
        etudiant1.devenirChauffeur("Peugeot 208", 3, itineraireChauffeur);
        etudiant1.setHoraire(horaire);
        etudiant1.setPreferences(new Preferences(Preferences.Genre.INDIFFERENT, true, true));

        etudiant2.devenirPassager(itinerairePassager);
        etudiant2.setHoraire(horaire);
        etudiant2.setPreferences(new Preferences(Preferences.Genre.INDIFFERENT, false, true));

        // Add users to system
        gestion.ajouterUtilisateur(etudiant1);
        gestion.ajouterUtilisateur(etudiant2);

        // Create a ride
        Course course = new Course(etudiant1, itineraireChauffeur,
                Course.TypeCourse.ALLER_RETOUR,
                LocalDateTime.now().plusDays(1));

        // Add passenger to ride
        if (course.ajouterPassager(etudiant2)) {
            System.out.println("Passenger added successfully");
        }

        // Start the ride
        course.demarrer();
    }
}