import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un itinéraire pour le covoiturage
 */
public class Itineraire {
    private Localisation pointDepart;
    private List<Localisation> pointsArret;
    private static final double DISTANCE_MAX_DETOUR = 2.0; // en kilomètres

    /**
     * Constructeur pour initialiser un itinéraire avec un point de départ
     */
    public Itineraire(Localisation pointDepart) {
        this.pointDepart = pointDepart;
        this.pointsArret = new ArrayList<>();
    }

    /**
     * Ajoute un point d'arrêt à l'itinéraire
     */
    public void ajouterPointArret(Localisation localisation) {
        pointsArret.add(localisation);
    }

    /**
     * Vérifie si cet itinéraire est compatible avec un autre
     * Pour qu'un itinéraire de chauffeur soit compatible avec celui d'un passager:
     * 1. Le point de ramassage du passager doit être proche de l'itinéraire du chauffeur
     * 2. Le point de dépôt du passager doit être proche de l'itinéraire du chauffeur
     */
    public boolean estCompatibleAvec(Itineraire itinerairePassager) {
        // Si le chauffeur n'a pas d'itinéraire défini, tout itinéraire de passager est compatible
        if (pointsArret.isEmpty()) {
            return true;
        }

        // Si le passager n'a pas de point de ramassage/dépôt défini, pas compatible
        if (itinerairePassager.getPointsArret().isEmpty()) {
            return false;
        }

        Localisation pointRamassagePassager = itinerairePassager.getPointDepart();
        Localisation pointDepotPassager = itinerairePassager.getPointsArret().get(0);

        boolean pointRamassageProcheItineraire = estPointProcheItineraire(pointRamassagePassager);
        boolean pointDepotProcheItineraire = estPointProcheItineraire(pointDepotPassager);

        return pointRamassageProcheItineraire && pointDepotProcheItineraire;
    }

    /**
     * Vérifie si un point donné est proche de l'itinéraire
     */
    private boolean estPointProcheItineraire(Localisation point) {
        // Vérifie si le point est proche du point de départ
        if (pointDepart.distanceVers(point) <= DISTANCE_MAX_DETOUR) {
            return true;
        }

        // Vérifie si le point est proche d'un point d'arrêt
        for (Localisation localisation : pointsArret) {
            if (localisation.distanceVers(point) <= DISTANCE_MAX_DETOUR) {
                return true;
            }
        }

        // Vérifie si le point est proche d'un segment de l'itinéraire
        Localisation precedent = pointDepart;
        for (Localisation courant : pointsArret) {
            if (estPointProcheSegment(precedent, courant, point)) {
                return true;
            }
            precedent = courant;
        }

        return false;
    }

    /**
     * Vérifie si un point est proche d'un segment de l'itinéraire
     */
    private boolean estPointProcheSegment(Localisation debut, Localisation fin, Localisation point) {
        double longueurSegment = debut.distanceVers(fin);
        double distance1 = debut.distanceVers(point);
        double distance2 = fin.distanceVers(point);

        // Si le point est au-delà des extrémités du segment, utiliser la distance directe
        if (distance1 > longueurSegment + DISTANCE_MAX_DETOUR ||
                distance2 > longueurSegment + DISTANCE_MAX_DETOUR) {
            return false;
        }

        // Calcul de la distance perpendiculaire en utilisant la projection
        // Il s'agit d'un calcul simplifié pour la démonstration
        double projection = ((point.getLatitude() - debut.getLatitude()) *
                (fin.getLatitude() - debut.getLatitude()) +
                (point.getLongitude() - debut.getLongitude()) *
                        (fin.getLongitude() - debut.getLongitude())) /
                (longueurSegment * longueurSegment);

        projection = Math.max(0, Math.min(1, projection));

        double latProjetee = debut.getLatitude() + projection * (fin.getLatitude() - debut.getLatitude());
        double longProjetee = debut.getLongitude() + projection * (fin.getLongitude() - debut.getLongitude());

        Localisation pointProjete = new Localisation("Projeté", latProjetee, longProjetee);
        double distancePerpendiculaire = point.distanceVers(pointProjete);

        return distancePerpendiculaire <= DISTANCE_MAX_DETOUR;
    }

    // Getters et setters
    public Localisation getPointDepart() {
        return pointDepart;
    }

    public void setPointDepart(Localisation pointDepart) {
        this.pointDepart = pointDepart;
    }

    public List<Localisation> getPointsArret() {
        return pointsArret;
    }

    public void setPointsArret(List<Localisation> pointsArret) {
        this.pointsArret = pointsArret;
    }

    public static double getDistanceMaxDetour() {
        return DISTANCE_MAX_DETOUR;
    }
}