import java.util.ArrayList;

public class PersonnelCabine extends Employe {
    private String qualification;
    private Vol volAssigne;

    private static ArrayList<PersonnelCabine> personnelsCabine = new ArrayList<PersonnelCabine>();

    public PersonnelCabine(String identifiant, String nom, String adresse, String contact,
                           String numeroEmploye, String dateEmbauche,
                           String qualification) {
        super(identifiant, nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.qualification = qualification;
        this.volAssigne = null;
    }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }
    public Vol getVolAssigne() { return volAssigne; }

    public void affecterVol(Vol vol) {
        this.volAssigne = vol;
        System.out.println("Personnel cabine " + getNom() + " affecté au vol " + vol.getNumeroVol());
    }

    public Vol obtenirVol() {
        return volAssigne;
    }

    @Override
    public String obtenirRole() {
        return "Personnel Cabine";
    }

    @Override
    public void obtenirInfos() {
        super.obtenirInfos();
        System.out.println("Qualification  : " + qualification);
        if (volAssigne != null) {
            System.out.println("Vol assigné    : " + volAssigne.getNumeroVol());
        } else {
            System.out.println("Vol assigné    : Aucun");
        }
    }

    // CRUD
    public static void ajouter(PersonnelCabine pc) {
        personnelsCabine.add(pc);
    }

    public static ArrayList<PersonnelCabine> getListePersonnelCabine() {
        return personnelsCabine;
    }

    public static PersonnelCabine trouver(String numeroEmploye) {
        for (int i = 0; i < personnelsCabine.size(); i++) {
            if (personnelsCabine.get(i).getNumeroEmploye().equals(numeroEmploye)) {
                return personnelsCabine.get(i);
            }
        }
        return null;
    }

    public static boolean supprimer(String numeroEmploye) {
        for (int i = 0; i < personnelsCabine.size(); i++) {
            if (personnelsCabine.get(i).getNumeroEmploye().equals(numeroEmploye)) {
                personnelsCabine.remove(i);
                return true;
            }
        }
        return false;
    }

    public static void vider() {
        personnelsCabine.clear();
    }
}
