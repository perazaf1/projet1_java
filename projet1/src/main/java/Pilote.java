import java.util.ArrayList;

public class Pilote extends Employe {
    private String licence;
    private int heuresDeVol;
    private Vol volAssigne;

    private static ArrayList<Pilote> pilotes = new ArrayList<Pilote>();

    public Pilote(String identifiant, String nom, String adresse, String contact,
                  String numeroEmploye, String dateEmbauche,
                  String licence, int heuresDeVol) {
        super(identifiant, nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.licence = licence;
        this.heuresDeVol = heuresDeVol;
        this.volAssigne = null;
    }

    public String getLicence() { return licence; }
    public void setLicence(String licence) { this.licence = licence; }
    public int getHeuresDeVol() { return heuresDeVol; }
    public void setHeuresDeVol(int heuresDeVol) { this.heuresDeVol = heuresDeVol; }
    public Vol getVolAssigne() { return volAssigne; }

    public void affecterVol(Vol vol) {
        this.volAssigne = vol;
        System.out.println("Pilote " + getNom() + " affecté au vol " + vol.getNumeroVol());
    }

    public Vol obtenirVol() {
        return volAssigne;
    }

    @Override
    public String obtenirRole() {
        return "Pilote";
    }

    @Override
    public void obtenirInfos() {
        super.obtenirInfos();
        System.out.println("Licence        : " + licence);
        System.out.println("Heures de vol  : " + heuresDeVol);
        if (volAssigne != null) {
            System.out.println("Vol assigné    : " + volAssigne.getNumeroVol());
        } else {
            System.out.println("Vol assigné    : Aucun");
        }
    }

    // CRUD
    public static void ajouter(Pilote p) {
        pilotes.add(p);
    }

    public static ArrayList<Pilote> getListePilotes() {
        return pilotes;
    }

    public static Pilote trouver(String numeroEmploye) {
        for (int i = 0; i < pilotes.size(); i++) {
            if (pilotes.get(i).getNumeroEmploye().equals(numeroEmploye)) {
                return pilotes.get(i);
            }
        }
        return null;
    }

    public static boolean supprimer(String numeroEmploye) {
        for (int i = 0; i < pilotes.size(); i++) {
            if (pilotes.get(i).getNumeroEmploye().equals(numeroEmploye)) {
                pilotes.remove(i);
                return true;
            }
        }
        return false;
    }

    public static void vider() {
        pilotes.clear();
    }
}
