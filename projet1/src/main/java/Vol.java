import java.util.ArrayList;

public class Vol {
    private String numeroVol;
    private String origine;
    private String destination;
    private String dateHeureDepart;
    private String dateHeureArrivee;
    private String etat;
    private Avion avion;
    private Pilote pilote;
    private ArrayList<PersonnelCabine> personnelCabine;
    private ArrayList<Passager> passagers;

    private static ArrayList<Vol> vols = new ArrayList<Vol>();

    public Vol(String numeroVol, String origine, String destination,
               String dateHeureDepart, String dateHeureArrivee) {
        this.numeroVol = numeroVol;
        this.origine = origine;
        this.destination = destination;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.etat = "Planifié";
        this.personnelCabine = new ArrayList<PersonnelCabine>();
        this.passagers = new ArrayList<Passager>();
    }

    public String getNumeroVol() { return numeroVol; }
    public void setNumeroVol(String numeroVol) { this.numeroVol = numeroVol; }
    public String getOrigine() { return origine; }
    public void setOrigine(String origine) { this.origine = origine; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getDateHeureDepart() { return dateHeureDepart; }
    public void setDateHeureDepart(String dateHeureDepart) { this.dateHeureDepart = dateHeureDepart; }
    public String getDateHeureArrivee() { return dateHeureArrivee; }
    public void setDateHeureArrivee(String dateHeureArrivee) { this.dateHeureArrivee = dateHeureArrivee; }
    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }
    public Avion getAvion() { return avion; }
    public void setAvion(Avion avion) { this.avion = avion; }
    public Pilote getPilote() { return pilote; }
    public void setPilote(Pilote pilote) { this.pilote = pilote; }
    public ArrayList<PersonnelCabine> getPersonnelCabine() { return personnelCabine; }
    public ArrayList<Passager> getPassagers() { return passagers; }

    public void planifierVol() {
        this.etat = "Planifié";
        System.out.println("Vol " + numeroVol + " planifié de " + origine + " vers " + destination);
    }

    public void annulerVol() {
        this.etat = "Annulé";
        System.out.println("Vol " + numeroVol + " annulé.");
    }

    public void modifierVol(String dateHeureDepart, String dateHeureArrivee) {
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        System.out.println("Vol " + numeroVol + " modifié.");
    }

    public void affecterVol(Pilote pilote, ArrayList<PersonnelCabine> personnel) {
        this.pilote = pilote;
        pilote.affecterVol(this);
        this.personnelCabine = personnel;
        for (int i = 0; i < personnel.size(); i++) {
            personnel.get(i).affecterVol(this);
        }
        System.out.println("Équipage affecté au vol " + numeroVol);
    }

    public void ajouterPassager(Passager passager) {
        if (avion != null && passagers.size() >= avion.getCapacite()) {
            System.out.println("Vol " + numeroVol + " complet, impossible d'ajouter le passager.");
            return;
        }
        passagers.add(passager);
        System.out.println("Passager " + passager.getNom() + " ajouté au vol " + numeroVol);
    }

    public void listingPassager() {
        System.out.println("=== Passagers du vol " + numeroVol + " ===");
        if (passagers.size() == 0) {
            System.out.println("Aucun passager.");
        }
        for (int i = 0; i < passagers.size(); i++) {
            System.out.println((i + 1) + ". " + passagers.get(i).getNom()
                    + " (" + passagers.get(i).getIdentifiant() + ")");
        }
    }

    public void obtenirInfos() {
        System.out.println("Numéro vol      : " + numeroVol);
        System.out.println("Origine         : " + origine);
        System.out.println("Destination     : " + destination);
        System.out.println("Départ          : " + dateHeureDepart);
        System.out.println("Arrivée         : " + dateHeureArrivee);
        System.out.println("État            : " + etat);
        if (avion != null) {
            System.out.println("Avion           : " + avion.getImmatriculation());
        }
        if (pilote != null) {
            System.out.println("Pilote          : " + pilote.getNom());
        }
        System.out.println("Passagers       : " + passagers.size());
    }

    // CRUD
    public static void ajouter(Vol v) {
        vols.add(v);
    }

    public static ArrayList<Vol> getListe() {
        return vols;
    }

    public static Vol trouver(String numeroVol) {
        for (int i = 0; i < vols.size(); i++) {
            if (vols.get(i).getNumeroVol().equals(numeroVol)) {
                return vols.get(i);
            }
        }
        return null;
    }

    public static boolean supprimer(String numeroVol) {
        for (int i = 0; i < vols.size(); i++) {
            if (vols.get(i).getNumeroVol().equals(numeroVol)) {
                vols.remove(i);
                return true;
            }
        }
        return false;
    }

    public static void vider() {
        vols.clear();
    }
}
