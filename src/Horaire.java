import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant les horaires disponibles pour un utilisateur
 */
public class Horaire {
    private TypeHoraire type;
    private List<PlageHoraire> plagesHoraires;

    /**
     * Constructeur pour initialiser un horaire
     */
    public Horaire(TypeHoraire type) {
        this.type = type;
        this.plagesHoraires = new ArrayList<>();
    }

    /**
     * Ajoute une plage horaire à cet horaire
     */
    public void ajouterPlageHoraire(DayOfWeek jour, LocalTime heureDebut, LocalTime heureFin) {
        PlageHoraire plage = new PlageHoraire(jour, heureDebut, heureFin);
        plagesHoraires.add(plage);
    }

    /**
     * Vérifie si cet horaire chevauche un autre horaire
     */
    public boolean chevauche(Horaire autre) {
        for (PlageHoraire plage1 : this.plagesHoraires) {
            for (PlageHoraire plage2 : autre.plagesHoraires) {
                if (plage1.chevauche(plage2)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Getters et setters

    public TypeHoraire getType() {
        return type;
    }

    public void setType(TypeHoraire type) {
        this.type = type;
    }

    public List<PlageHoraire> getPlagesHoraires() {
        return new ArrayList<>(plagesHoraires);
    }

    // Classe interne pour représenter une plage horaire
    public static class PlageHoraire {
        private DayOfWeek jour;
        private LocalTime heureDebut;
        private LocalTime heureFin;

        public PlageHoraire(DayOfWeek jour, LocalTime heureDebut, LocalTime heureFin) {
            this.jour = jour;
            this.heureDebut = heureDebut;
            this.heureFin = heureFin;
        }

        /**
         * Vérifie si cette plage horaire chevauche une autre
         */
        public boolean chevauche(PlageHoraire autre) {
            // Doit être le même jour pour pouvoir chevaucher
            if (this.jour != autre.jour) {
                return false;
            }

            // Vérifie si les plages se chevauchent
            return !(this.heureFin.isBefore(autre.heureDebut) ||
                    this.heureDebut.isAfter(autre.heureFin));
        }

        // Getters et setters

        public DayOfWeek getJour() {
            return jour;
        }

        public void setJour(DayOfWeek jour) {
            this.jour = jour;
        }

        public LocalTime getHeureDebut() {
            return heureDebut;
        }

        public void setHeureDebut(LocalTime heureDebut) {
            this.heureDebut = heureDebut;
        }

        public LocalTime getHeureFin() {
            return heureFin;
        }

        public void setHeureFin(LocalTime heureFin) {
            this.heureFin = heureFin;
        }
    }

    // Énumération pour les types d'horaires
    public enum TypeHoraire {
        QUOTIDIEN, HEBDOMADAIRE, PONCTUEL
    }
}