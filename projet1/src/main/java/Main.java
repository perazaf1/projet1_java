import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // ===== Initialisation des donnees de depart =====

        // Aeroports
        Aeroport cdg = new Aeroport("CDG", "Paris", "Aeroport Charles de Gaulle");
        Aeroport jfk = new Aeroport("JFK", "New York", "Aeroport John F. Kennedy");
        Aeroport mad = new Aeroport("MAD", "Madrid", "Aeroport Adolfo Suarez");
        Aeroport.ajouter(cdg);
        Aeroport.ajouter(jfk);
        Aeroport.ajouter(mad);

        // Avions
        Avion avion1 = new Avion("F-GKXA", "Airbus A320", 150);
        Avion avion2 = new Avion("F-GKXB", "Boeing 777", 300);
        Avion.ajouter(avion1);
        Avion.ajouter(avion2);

        // Pilotes
        Pilote pilote1 = new Pilote("P001", "Dupont Jean", "12 rue de la Paix, Paris",
                "0612345678", "EMP001", "2015-03-10", "LIC-FR-001", 5000);
        Pilote pilote2 = new Pilote("P002", "Moreau Sophie", "8 avenue Foch, Lyon",
                "0623456789", "EMP002", "2018-07-22", "LIC-FR-002", 3200);
        Pilote.ajouter(pilote1);
        Pilote.ajouter(pilote2);

        // Personnel cabine
        PersonnelCabine pca1 = new PersonnelCabine("PC001", "Martin Claire", "5 avenue Victor Hugo, Lyon",
                "0698765432", "EMP003", "2018-06-01", "Hotesse confirmee");
        PersonnelCabine pca2 = new PersonnelCabine("PC002", "Leroy Marc", "8 boulevard Haussmann, Paris",
                "0611223344", "EMP004", "2020-01-15", "Steward de cabine");
        PersonnelCabine pca3 = new PersonnelCabine("PC003", "Petit Julie", "3 rue du Midi, Toulouse",
                "0644332211", "EMP005", "2021-09-01", "Hotesse junior");
        PersonnelCabine.ajouter(pca1);
        PersonnelCabine.ajouter(pca2);
        PersonnelCabine.ajouter(pca3);

        // Passagers
        Passager passager1 = new Passager("PA001", "Durand Alice", "3 rue Moliere, Paris",
                "0655443322", "PP123456");
        Passager passager2 = new Passager("PA002", "Bernard Thomas", "22 rue Lafayette, Bordeaux",
                "0677889900", "PP789012");
        Passager passager3 = new Passager("PA003", "Nguyen Linh", "14 rue des Lilas, Marseille",
                "0688776655", "PP345678");
        Passager.ajouter(passager1);
        Passager.ajouter(passager2);
        Passager.ajouter(passager3);

        // Vols
        Vol vol1 = new Vol("AF101", "Paris CDG", "New York JFK",
                "2025-06-15 10:00", "2025-06-15 13:00");
        Vol vol2 = new Vol("IB205", "Paris CDG", "Madrid MAD",
                "2025-06-20 08:30", "2025-06-20 10:45");
        Vol.ajouter(vol1);
        Vol.ajouter(vol2);

        // Planification des vols
        vol1.planifierVol();
        vol2.planifierVol();

        // Affecter avions
        avion1.affecterVol(vol1);
        vol1.setAvion(avion1);
        avion2.affecterVol(vol2);
        vol2.setAvion(avion2);

        // Affecter equipage au vol 1
        ArrayList<PersonnelCabine> equipe1 = new ArrayList<PersonnelCabine>();
        equipe1.add(pca1);
        equipe1.add(pca2);
        vol1.affecterVol(pilote1, equipe1);

        // Affecter equipage au vol 2
        ArrayList<PersonnelCabine> equipe2 = new ArrayList<PersonnelCabine>();
        equipe2.add(pca3);
        vol2.affecterVol(pilote2, equipe2);

        // Affecter vols aux aeroports
        cdg.affecterVol(vol1, true);
        jfk.affecterVol(vol1, false);
        cdg.affecterVol(vol2, true);
        mad.affecterVol(vol2, false);

        // Reservations initiales
        passager1.reserverVol(vol1, "2025-05-10");
        passager2.reserverVol(vol1, "2025-05-11");
        passager3.reserverVol(vol2, "2025-05-12");

        System.out.println("\nDonnees de depart chargees avec succes !");
        System.out.println("  - " + Aeroport.getListe().size() + " aeroports");
        System.out.println("  - " + Avion.getListe().size() + " avions");
        System.out.println("  - " + Pilote.getListePilotes().size() + " pilotes");
        System.out.println("  - " + PersonnelCabine.getListePersonnelCabine().size() + " membres du personnel cabine");
        System.out.println("  - " + Passager.getListe().size() + " passagers");
        System.out.println("  - " + Vol.getListe().size() + " vols planifies");
        System.out.println("  - " + Reservation.getListe().size() + " reservations");

        // ===== Lancement du menu interactif =====
        Menu menu = new Menu();
        menu.lancer();
    }
}
