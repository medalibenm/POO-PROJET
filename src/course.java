import java.time.LocalDate;
import java.util.List;
public class course {
    protected String idCourse;
    protected utilisateur chauffeur;
    protected List<utilisateur> passager ;
    protected enum typeCourse {
        ALLER_RETOUR, ALLER_SIMPLE, RETOUR_SIMPLE

    }
    protected LocalDate horairesDisponibles;

    protected enum statut {
        PLANIFIEE, EN_COURS, TERMINEE, ANNULEE
    }







}
