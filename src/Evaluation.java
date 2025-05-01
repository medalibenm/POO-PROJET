import java.time.LocalDateTime;

/**
 * Classe représentant une évaluation donnée par un utilisateur à un autre
 */
public class Evaluation {
    private Utilisateur evaluateur;
    private Utilisateur evalue;
    private int note; // Note sur 5
    private String commentaire;
    private LocalDateTime dateHeure;

    /**
     * Constructeur pour créer une évaluation
     */
    public Evaluation(Utilisateur evaluateur, Utilisateur evalue, int note, String commentaire) {
        if (note < 1 || note > 5) {
            throw new IllegalArgumentException("La note doit être entre 1 et 5");
        }

        this.evaluateur = evaluateur;
        this.evalue = evalue;
        this.note = note;
        this.commentaire = commentaire;
        this.dateHeure = LocalDateTime.now();
    }

    // Getters (pas de setters car une évaluation ne doit pas être modifiée une fois créée)

    public Utilisateur getEvaluateur() {
        return evaluateur;
    }

    public Utilisateur getEvalue() {
        return evalue;
    }

    public int getNote() {
        return note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    @Override
    public String toString() {
        return String.format("Évaluation de %s %s pour %s %s: %d/5 - \"%s\"",
                evaluateur.getPrenom(), evaluateur.getNom(),
                evalue.getPrenom(), evalue.getNom(),
                note, commentaire);
    }
}
