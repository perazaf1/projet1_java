import java.util.ArrayList;

public class Passager extends Personne {
    private String passeport;
    private ArrayList<Reservation> reservations;

    private static ArrayList<Passager> passagers = new ArrayList<Passager>();

    public Passager(String identifiant, String nom, String adresse, String contact, String passeport) {
        super(identifiant, nom, adresse, contact);
        this.passeport = passeport;
        this.reservations = new ArrayList<Reservation>();
    }

    public String getPasseport() { return passeport; }
    public void setPasseport(String passeport) { this.passeport = passeport; }
    public ArrayList<Reservation> getReservations() { return reservations; }

    public Reservation reserverVol(Vol vol, String dateReservation) {
        Reservation r = new Reservation(dateReservation, this, vol);
        reservations.add(r);
        Reservation.ajouter(r);
        vol.ajouterPassager(this);
        r.confirmerReservation();
        System.out.println(getNom() + " a réservé le vol " + vol.getNumeroVol());
        return r;
    }

    public void annulerReservation(String numeroReservation) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getNumeroReservation().equals(numeroReservation)) {
                reservations.get(i).annulerReservation();
                reservations.remove(i);
                return;
            }
        }
        System.out.println("Réservation " + numeroReservation + " introuvable pour " + getNom());
    }

    public void obtenirReservations() {
        System.out.println(" Réservations de " + getNom());
        if (reservations.size() == 0) {
            System.out.println("Aucune réservation.");
        }
        for (int i = 0; i < reservations.size(); i++) {
            reservations.get(i).obtenirInfos();
            System.out.println("---");
        }
    }

    @Override
    public void obtenirInfos() {
        super.obtenirInfos();
        System.out.println("Passeport      : " + passeport);
        System.out.println("Réservations   : " + reservations.size());
    }

    // CRUD
    public static void ajouter(Passager p) {
        passagers.add(p);
    }

    public static ArrayList<Passager> getListe() {
        return passagers;
    }

    public static Passager trouver(String identifiant) {
        for (int i = 0; i < passagers.size(); i++) {
            if (passagers.get(i).getIdentifiant().equals(identifiant)) {
                return passagers.get(i);
            }
        }
        return null;
    }

    public static boolean supprimer(String identifiant) {
        for (int i = 0; i < passagers.size(); i++) {
            if (passagers.get(i).getIdentifiant().equals(identifiant)) {
                passagers.remove(i);
                return true;
            }
        }
        return false;
    }

    public static void vider() {
        passagers.clear();
    }
}
