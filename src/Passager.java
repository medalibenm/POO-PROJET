public class Passager {
    private final utilisateur user;
    private Itineraire itineraire;

    public Passager(utilisateur user, Itineraire itineraire) {
        this.user = user;
        this.itineraire = itineraire;
    }

    // Delegated getters
    public String getNom() { return user.getNom(); }

    // Passager-specific methods
    public Itineraire getItineraire() { return itineraire; }
}