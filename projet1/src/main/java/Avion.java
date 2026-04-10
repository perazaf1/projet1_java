import java.util.ArrayList;

public class Avion {
    private String immatriculation;
    private String modele;
    private int capacite;
    private Vol volAssigne;

    private static ArrayList<Avion> avions = new ArrayList<Avion>();

    public Avion(String immatriculation, String modele, int capacite) {
        this.immatriculation = immatriculation;
        this.modele = modele;
        this.capacite = capacite;
        this.volAssigne = null;
    }

    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }
    public String getModele() { return modele; }
    public void setModele(String modele) { this.modele = modele; }
    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }
    public Vol getVolAssigne() { return volAssigne; }
    public void setVolAssigne(Vol volAssigne) { this.volAssigne = volAssigne; }

    public void affecterVol(Vol vol) {
        this.volAssigne = vol;
        System.out.println("Avion " + immatriculation + " affecté au vol " + vol.getNumeroVol());
    }

    public boolean verifierDisponibilite() {
        return volAssigne == null;
    }

    public void obtenirInfos() {
        System.out.println("Immatriculation : " + immatriculation);
        System.out.println("Modèle          : " + modele);
        System.out.println("Capacité        : " + capacite);
        if (volAssigne != null) {
            System.out.println("Vol assigné     : " + volAssigne.getNumeroVol());
        } else {
            System.out.println("Vol assigné     : Aucun");
        }
    }

    // CRUD
    public static void ajouter(Avion a) {
        avions.add(a);
    }

    public static ArrayList<Avion> getListe() {
        return avions;
    }

    public static Avion trouver(String immatriculation) {
        for (int i = 0; i < avions.size(); i++) {
            if (avions.get(i).getImmatriculation().equals(immatriculation)) {
                return avions.get(i);
            }
        }
        return null;
    }

    public static boolean supprimer(String immatriculation) {
        for (int i = 0; i < avions.size(); i++) {
            if (avions.get(i).getImmatriculation().equals(immatriculation)) {
                avions.remove(i);
                return true;
            }
        }
        return false;
    }

    public static void vider() {
        avions.clear();
    }
}
