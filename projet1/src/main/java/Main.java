import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        System.out.println("   Système de réservation aérienne");

        //Creation de l'aéroport
        // Aéroport
        Aeroport cdg = new Aeroport("CDG", "Paris", "Aéroport Charles de Gaulle");
        Aeroport jfk = new Aeroport("JFK", "New York", "Aéroport John F. Kennedy");
        Aeroport.ajouter(cdg);
        Aeroport.ajouter(jfk);

        // Avion
        Avion avion1 = new Avion("F-GKXA", "Airbus A320", 150);
        Avion.ajouter(avion1);

        //Pilote
        Pilote pilote1 = new Pilote("P001", "Dupont Jean", "12 rue de la Paix, Paris",
                "0612345678", "EMP001", "2015-03-10", "LIC-FR-001", 5000);
        Pilote.ajouter(pilote1);

        //Personnel cabine
        PersonnelCabine pca1 = new PersonnelCabine("PC001", "Martin Claire", "5 avenue Victor Hugo, Lyon",
                "0698765432", "EMP002", "2018-06-01", "Hôtesse confirmée");
        PersonnelCabine pca2 = new PersonnelCabine("PC002", "Leroy Marc", "8 boulevard Haussmann, Paris",
                "0611223344", "EMP003", "2020-01-15", "Stewart de cabine");
        PersonnelCabine.ajouter(pca1);
        PersonnelCabine.ajouter(pca2);

        //Passager
        Passager passager1 = new Passager("PA001", "Durand Alice", "3 rue Molière, Paris",
                "0655443322", "PP123456");
        Passager passager2 = new Passager("PA002", "Bernard Thomas", "22 rue Lafayette, Bordeaux",
                "0677889900", "PP789012");
        Passager.ajouter(passager1);
        Passager.ajouter(passager2);

        // Vol & planification du vol
        Vol vol1 = new Vol("AF101", "Paris CDG", "New York JFK",
                "2025-04-20 10:00", "2025-04-20 13:00");
        Vol.ajouter(vol1);
        vol1.planifierVol();

        System.out.println();

        //Avion mis sur un vol
        avion1.affecterVol(vol1);
        vol1.setAvion(avion1);

        //Equipage mis sur un vol
        ArrayList<PersonnelCabine> equipe = new ArrayList<PersonnelCabine>();
        equipe.add(pca1);
        equipe.add(pca2);
        vol1.affecterVol(pilote1, equipe);

        // Affecter vol aux aéroports
        cdg.affecterVol(vol1, true);
        jfk.affecterVol(vol1, false);

        System.out.println();

        //Résa
        System.out.println(" Réservations ");
        Reservation r1 = passager1.reserverVol(vol1, "2025-04-15");
        Reservation r2 = passager2.reserverVol(vol1, "2025-04-15");

        System.out.println();

        //liste des passagers
        vol1.listingPassager();

        System.out.println();

        //infos du vol
        System.out.println(" Infos du vol ");
        vol1.obtenirInfos();

        System.out.println();

        //infos des passagers
        System.out.println(" Infos passager 1 ");
        passager1.obtenirInfos();
        passager1.obtenirReservations();

        System.out.println();

        //annulation de résa
        System.out.println(" Annulation de réservation ");
        passager1.annulerReservation(r1.getNumeroReservation());

        System.out.println();

        //infos du pilote
        System.out.println(" Infos pilote ");
        pilote1.obtenirInfos();

        System.out.println();

        //check si avion dispo ?
        System.out.println(" Disponibilité avion ");
        System.out.println("Avion " + avion1.getImmatriculation() + " disponible : " + avion1.verifierDisponibilite());

        System.out.println();

        //infos aéroport
        System.out.println(" Infos aéroport CDG ");
        cdg.obtenirInfos();

        System.out.println();

        //annulation vol
        System.out.println(" Annulation du vol ");
        vol1.annulerVol();

        System.out.println("   Fin de la démonstration");

    }
}
