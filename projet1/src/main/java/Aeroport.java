import java.util.ArrayList;

public class Aeroport {
    private String nom;
    private String ville;
    private String description;
    private ArrayList<Vol> volsDepart;
    private ArrayList<Vol> volsArrivee;

    private static ArrayList<Aeroport> aeroports = new ArrayList<Aeroport>();

    public Aeroport(String nom, String ville, String description) {
        this.nom = nom;
        this.ville = ville;
        this.description = description;
        this.volsDepart = new ArrayList<Vol>();
        this.volsArrivee = new ArrayList<Vol>();
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public ArrayList<Vol> getVolsDepart() { return volsDepart; }
    public ArrayList<Vol> getVolsArrivee() { return volsArrivee; }

    public void affecterVol(Vol vol, boolean estDepart) {
        if (estDepart) {
            volsDepart.add(vol);
            System.out.println("Vol " + vol.getNumeroVol() + " ajouté aux départs de " + nom);
        } else {
            volsArrivee.add(vol);
            System.out.println("Vol " + vol.getNumeroVol() + " ajouté aux arrivées de " + nom);
        }
    }

    public void obtenirInfos() {
        System.out.println("Aéroport       : " + nom);
        System.out.println("Ville          : " + ville);
        System.out.println("Description    : " + description);
        System.out.println("Vols départ    : " + volsDepart.size());
        System.out.println("Vols arrivée   : " + volsArrivee.size());
    }

    // CRUD
    public static void ajouter(Aeroport a) {
        aeroports.add(a);
    }

    public static ArrayList<Aeroport> getListe() {
        return aeroports;
    }

    public static Aeroport trouver(String nom) {
        for (int i = 0; i < aeroports.size(); i++) {
            if (aeroports.get(i).getNom().equals(nom)) {
                return aeroports.get(i);
            }
        }
        return null;
    }

    public static boolean supprimer(String nom) {
        for (int i = 0; i < aeroports.size(); i++) {
            if (aeroports.get(i).getNom().equals(nom)) {
                aeroports.remove(i);
                return true;
            }
        }
        return false;
    }

    public static void vider() {
        aeroports.clear();
    }
}
