import java.util.ArrayList;

public class Employe extends Personne {
    private String numeroEmploye;
    private String dateEmbauche;

    private static ArrayList<Employe> employes = new ArrayList<Employe>();

    public Employe(String identifiant, String nom, String adresse, String contact,
                   String numeroEmploye, String dateEmbauche) {
        super(identifiant, nom, adresse, contact);
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
    }

    public String getNumeroEmploye() { return numeroEmploye; }
    public void setNumeroEmploye(String numeroEmploye) { this.numeroEmploye = numeroEmploye; }
    public String getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(String dateEmbauche) { this.dateEmbauche = dateEmbauche; }

    public String obtenirRole() {
        return "Employé";
    }

    @Override
    public void obtenirInfos() {
        super.obtenirInfos();
        System.out.println("Numéro employé : " + numeroEmploye);
        System.out.println("Date embauche  : " + dateEmbauche);
        System.out.println("Rôle           : " + obtenirRole());
    }

    // CRUD
    public static void ajouter(Employe e) {
        employes.add(e);
    }

    public static ArrayList<Employe> getListe() {
        return employes;
    }

    public static Employe trouver(String numeroEmploye) {
        for (int i = 0; i < employes.size(); i++) {
            if (employes.get(i).getNumeroEmploye().equals(numeroEmploye)) {
                return employes.get(i);
            }
        }
        return null;
    }

    public static boolean supprimer(String numeroEmploye) {
        for (int i = 0; i < employes.size(); i++) {
            if (employes.get(i).getNumeroEmploye().equals(numeroEmploye)) {
                employes.remove(i);
                return true;
            }
        }
        return false;
    }
}
