import java.util.ArrayList;
import java.util.List;

public class Itineraire {

        private String depart;
        private String destination;
        private List<String> arretsIntermediaires; // Optional intermediate stops

        public Itineraire(String depart, String destination) {
            this.depart = depart;
            this.destination = destination;
            this.arretsIntermediaires = new ArrayList<>();
        }

        // Add methods to manage stops
        public void ajouterArret(String arret) {
            arretsIntermediaires.add(arret);
        }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<String> getArretsIntermediaires() {
        return arretsIntermediaires;
    }

    public void setArretsIntermediaires(List<String> arretsIntermediaires) {
        this.arretsIntermediaires = arretsIntermediaires;
    }
}
