public class Conducteur {
    private final utilisateur user;
    private String nomVoiture;
    private int placesDisponibles;
    private Itineraire itineraire;

    public Conducteur(utilisateur user, String nomVoiture,
                      int places, Itineraire itineraire) {
        this.user = user;
        this.nomVoiture = nomVoiture;
        this.placesDisponibles = places;
        this.itineraire = itineraire;
    }

    // Delegated getters
    public String getMatricule() { return user.getMatricule(); }

    // Conducteur-specific methods
    public boolean peutAccepterPassager() {
        return placesDisponibles > 0;
    }
}