import java.util.ArrayList;

public class Reservation {
    private String numeroReservation;
    private String dateReservation;
    private String statut;
    private Passager passager;
    private Vol vol;

    private static int compteur = 1;
    private static ArrayList<Reservation> reservations = new ArrayList<Reservation>();

    public Reservation(String dateReservation, Passager passager, Vol vol) {
        this.numeroReservation = String.format("RES%03d", compteur);
        compteur++;
        this.dateReservation = dateReservation;
        this.statut = "En attente";
        this.passager = passager;
        this.vol = vol;
    }

    public String getNumeroReservation() { return numeroReservation; }
    public String getDateReservation() { return dateReservation; }
    public void setDateReservation(String dateReservation) { this.dateReservation = dateReservation; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public Passager getPassager() { return passager; }
    public Vol getVol() { return vol; }

    public void confirmerReservation() {
        this.statut = "Confirmé";
        System.out.println("Réservation " + numeroReservation + " confirmée.");
    }

    public void annulerReservation() {
        this.statut = "Annulé";
        System.out.println("Réservation " + numeroReservation + " annulée.");
    }

    public void modifierReservation(Vol nouveauVol) {
        this.vol = nouveauVol;
        System.out.println("Réservation " + numeroReservation + " modifiée vers vol " + nouveauVol.getNumeroVol());
    }

    public void obtenirInfos() {
        System.out.println("Numéro réservation : " + numeroReservation);
        System.out.println("Date réservation   : " + dateReservation);
        System.out.println("Statut             : " + statut);
        System.out.println("Passager           : " + passager.getNom());
        System.out.println("Vol                : " + vol.getNumeroVol());
    }

    // CRUD
    public static void ajouter(Reservation r) {
        reservations.add(r);
    }

    public static ArrayList<Reservation> getListe() {
        return reservations;
    }

    public static Reservation trouver(String numeroReservation) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getNumeroReservation().equals(numeroReservation)) {
                return reservations.get(i);
            }
        }
        return null;
    }

    public static boolean supprimer(String numeroReservation) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getNumeroReservation().equals(numeroReservation)) {
                reservations.remove(i);
                return true;
            }
        }
        return false;
    }

    public static void vider() {
        reservations.clear();
        compteur = 1;
    }
}
