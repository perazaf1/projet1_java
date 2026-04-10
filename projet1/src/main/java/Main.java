import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("   Système de réservation aérienne");
        System.out.println("========================================\n");

        // --- Création des aéroports ---
        Aeroport cdg = new Aeroport("CDG", "Paris", "Aéroport Charles de Gaulle");
        Aeroport jfk = new Aeroport("JFK", "New York", "Aéroport John F. Kennedy");
        Aeroport.ajouter(cdg);
        Aeroport.ajouter(jfk);

        // --- Création de l'avion ---
        Avion avion1 = new Avion("F-GKXA", "Airbus A320", 150);
        Avion.ajouter(avion1);

        // --- Création du pilote ---
        Pilote pilote1 = new Pilote("P001", "Dupont Jean", "12 rue de la Paix, Paris",
                "0612345678", "EMP001", "2015-03-10", "LIC-FR-001", 5000);
        Pilote.ajouter(pilote1);

        // --- Création du personnel cabine ---
        PersonnelCabine pca1 = new PersonnelCabine("PC001", "Martin Claire", "5 avenue Victor Hugo, Lyon",
                "0698765432", "EMP002", "2018-06-01", "Hôtesse confirmée");
        PersonnelCabine pca2 = new PersonnelCabine("PC002", "Leroy Marc", "8 boulevard Haussmann, Paris",
                "0611223344", "EMP003", "2020-01-15", "Stewart de cabine");
        PersonnelCabine.ajouter(pca1);
        PersonnelCabine.ajouter(pca2);

        // --- Création des passagers ---
        Passager passager1 = new Passager("PA001", "Durand Alice", "3 rue Molière, Paris",
                "0655443322", "PP123456");
        Passager passager2 = new Passager("PA002", "Bernard Thomas", "22 rue Lafayette, Bordeaux",
                "0677889900", "PP789012");
        Passager.ajouter(passager1);
        Passager.ajouter(passager2);

        // --- Création et planification du vol ---
        Vol vol1 = new Vol("AF101", "Paris CDG", "New York JFK",
                "2025-04-20 10:00", "2025-04-20 13:00");
        Vol.ajouter(vol1);
        vol1.planifierVol();

        System.out.println();

        // --- Affecter l'avion au vol ---
        avion1.affecterVol(vol1);
        vol1.setAvion(avion1);

        // --- Affecter l'équipage au vol ---
        ArrayList<PersonnelCabine> equipe = new ArrayList<PersonnelCabine>();
        equipe.add(pca1);
        equipe.add(pca2);
        vol1.affecterVol(pilote1, equipe);

        // --- Affecter le vol aux aéroports ---
        cdg.affecterVol(vol1, true);
        jfk.affecterVol(vol1, false);

        System.out.println();

        // --- Réservations ---
        System.out.println("=== Réservations ===");
        Reservation r1 = passager1.reserverVol(vol1, "2025-04-15");
        Reservation r2 = passager2.reserverVol(vol1, "2025-04-15");

        System.out.println();

        // --- Listing des passagers du vol ---
        vol1.listingPassager();

        System.out.println();

        // --- Infos du vol ---
        System.out.println("=== Infos du vol ===");
        vol1.obtenirInfos();

        System.out.println();

        // --- Infos passager ---
        System.out.println("=== Infos passager 1 ===");
        passager1.obtenirInfos();
        passager1.obtenirReservations();

        System.out.println();

        // --- Annulation de réservation ---
        System.out.println("=== Annulation de réservation ===");
        passager1.annulerReservation(r1.getNumeroReservation());

        System.out.println();

        // --- Infos pilote ---
        System.out.println("=== Infos pilote ===");
        pilote1.obtenirInfos();

        System.out.println();

        // --- Vérification disponibilité avion ---
        System.out.println("=== Disponibilité avion ===");
        System.out.println("Avion " + avion1.getImmatriculation() + " disponible : " + avion1.verifierDisponibilite());

        System.out.println();

        // --- Infos aéroport ---
        System.out.println("=== Infos aéroport CDG ===");
        cdg.obtenirInfos();

        System.out.println();

        // --- Annulation du vol ---
        System.out.println("=== Annulation du vol ===");
        vol1.annulerVol();

        System.out.println("\n========================================");
        System.out.println("   Fin de la démonstration");
        System.out.println("========================================");
    }
}
