public class ATS extends utilisateur{
    protected int anneRec;
    protected String serviceRatt;

    public ATS(String nom, String prenom, String matricule, String reputation, int anneRec, String serviceRatt) {
        super(nom, prenom, matricule);
        this.anneRec = anneRec;
        this.serviceRatt = serviceRatt;
    }
}
