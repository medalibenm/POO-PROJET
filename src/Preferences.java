/**
 * Classe représentant les préférences d'un utilisateur pour le covoiturage
 */
public class Preferences {
    private Genre genrePreference;
    private boolean musiqueAutorisee;
    private boolean bagagesAutorises;

    /**
     * Constructeur pour initialiser les préférences
     */
    public Preferences(Genre genrePreference, boolean musiqueAutorisee, boolean bagagesAutorises) {
        this.genrePreference = genrePreference;
        this.musiqueAutorisee = musiqueAutorisee;
        this.bagagesAutorises = bagagesAutorises;
    }

    /**
     * Vérifie si ces préférences sont compatibles avec celles d'un autre utilisateur
     */
    public boolean estCompatibleAvec(Preferences autres) {
        // Vérification de la compatibilité des genres
        if (this.genrePreference != Genre.INDIFFERENT &&
                autres.genrePreference != Genre.INDIFFERENT) {
            // Si les deux ont des préférences spécifiques et différentes, ils ne sont pas compatibles
            if (this.genrePreference != autres.genrePreference) {
                return false;
            }
        }

        // Vérification de la compatibilité pour la musique
        if (!this.musiqueAutorisee && autres.musiqueAutorisee) {
            return false; // Si la personne ne veut pas de musique mais l'autre si
        }

        // Vérification de la compatibilité pour les bagages
        if (!this.bagagesAutorises && autres.bagagesAutorises) {
            return false; // Si la personne ne veut pas de bagages mais l'autre si
        }

        return true;
    }

    // Getters et setters

    public Genre getGenrePreference() {
        return genrePreference;
    }

    public void setGenrePreference(Genre genrePreference) {
        this.genrePreference = genrePreference;
    }

    public boolean isMusiqueAutorisee() {
        return musiqueAutorisee;
    }

    public void setMusiqueAutorisee(boolean musiqueAutorisee) {
        this.musiqueAutorisee = musiqueAutorisee;
    }

    public boolean isBagagesAutorises() {
        return bagagesAutorises;
    }

    public void setBagagesAutorises(boolean bagagesAutorises) {
        this.bagagesAutorises = bagagesAutorises;
    }

    // Énumération pour les préférences de genre
    public enum Genre {
        HOMME, FEMME, INDIFFERENT
    }
}