public class enseignant extends  utilisateur{
    protected int anneeRec;
    protected String faculte;

    public enseignant(String nom, String prenom, String matricule, String reputation, int anneeRec, String faculte) {
        super(nom, prenom, matricule);
        this.anneeRec = anneeRec;
        this.faculte = faculte;
    }
}
