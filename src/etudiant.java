public class etudiant extends  utilisateur {

    protected int anneAdm;
    protected String faculte;
    protected String spec;

    public etudiant(String nom, String prenom, String matricule, String reputation, int anneAdm, String faculte, String spec) {
        super(nom, prenom, matricule);
        this.anneAdm = anneAdm;
        this.faculte = faculte;
        this.spec = spec;
    }
}
