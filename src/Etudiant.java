/**
 * Classe représentant un étudiant dans le système de covoiturage
 */
public class Etudiant extends Utilisateur {
    private int anneeAdmission;
    private String faculte;
    private String specialite;

    /**
     * Constructeur pour initialiser un étudiant
     */
    public Etudiant(String nom, String prenom, String matricule,
                    int anneeAdmission, String faculte, String specialite) {
        super(nom, prenom, matricule);
        this.anneeAdmission = anneeAdmission;
        this.faculte = faculte;
        this.specialite = specialite;
    }

    @Override
    public String getTypeUtilisateur() {
        return "Etudiant";
    }

    // Getters et setters spécifiques

    public int getAnneeAdmission() {
        return anneeAdmission;
    }

    public void setAnneeAdmission(int anneeAdmission) {
        this.anneeAdmission = anneeAdmission;
    }

    public String getFaculte() {
        return faculte;
    }

    public void setFaculte(String faculte) {
        this.faculte = faculte;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" - %s, %s (%d)", faculte, specialite, anneeAdmission);
    }
}