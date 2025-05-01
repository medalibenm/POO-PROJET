/**
 * Classe représentant un enseignant dans le système de covoiturage
 */
public class Enseignant extends Utilisateur {
    private int anneeRecrutement;
    private String faculte;

    /**
     * Constructeur pour initialiser un enseignant
     */
    public Enseignant(String nom, String prenom, String matricule,
                      int anneeRecrutement, String faculte) {
        super(nom, prenom, matricule);
        this.anneeRecrutement = anneeRecrutement;
        this.faculte = faculte;
    }

    @Override
    public String getTypeUtilisateur() {
        return "Enseignant";
    }

    // Getters et setters spécifiques

    public int getAnneeRecrutement() {
        return anneeRecrutement;
    }

    public void setAnneeRecrutement(int anneeRecrutement) {
        this.anneeRecrutement = anneeRecrutement;
    }

    public String getFaculte() {
        return faculte;
    }

    public void setFaculte(String faculte) {
        this.faculte = faculte;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" - %s (recruté en %d)", faculte, anneeRecrutement);
    }
}