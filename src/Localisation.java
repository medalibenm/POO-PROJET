/**
 * Classe pour représenter une localisation géographique
 */
public class Localisation {
    private String nom;
    private double latitude;
    private double longitude;

    /**
     * Constructeur pour initialiser une localisation
     */
    public Localisation(String nom, double latitude, double longitude) {
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Calcule la distance entre cette localisation et une autre
     * Utilise la formule de Haversine pour calculer la distance sur la surface terrestre
     */
    public double distanceVers(Localisation autre) {
        // Rayon de la Terre en km
        final double R = 6371.0;

        // Conversion des latitudes/longitudes de degrés en radians
        double lat1Rad = Math.toRadians(this.latitude);
        double lat2Rad = Math.toRadians(autre.latitude);
        double lon1Rad = Math.toRadians(this.longitude);
        double lon2Rad = Math.toRadians(autre.longitude);

        // Différences de latitude et longitude
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        // Formule de Haversine
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = R * c;

        return distance;
    }

    // Getters et setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return nom + " (" + latitude + ", " + longitude + ")";
    }
}