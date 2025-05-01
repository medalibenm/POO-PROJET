/**
 * Classe représentant un membre du personnel ATS dans le système de covoiturage
 */
public class ATS extends Utilisateur {
    private int anneeRecrutement;
    private String serviceRattachement;

    /**
     * Constructeur pour initialiser un membre ATS
     */
    public ATS(String nom, String prenom, String matricule,
               int anneeRecrutement, String serviceRattachement) {
        super(nom, prenom, matricule);
        this.anneeRecrutement = anneeRecrutement;
        this.serviceRattachement = serviceRattachement;
    }

    @Override
    public String getTypeUtilisateur() {
        return "ATS";
    }

    // Getters et setters spécifiques

    public int getAnneeRecrutement() {
        return anneeRecrutement;
    }

    public void setAnneeRecrutement(int anneeRecrutement) {
        this.anneeRecrutement = anneeRecrutement;
    }

    public String getServiceRattachement() {
        return serviceRattachement;
    }

    public void setServiceRattachement(String serviceRattachement) {
        this.serviceRattachement = serviceRattachement;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" - %s (recruté en %d)", serviceRattachement, anneeRecrutement);
    }
}