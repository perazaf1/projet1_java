import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void lancer() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("\n===================================================");
            System.out.println("    SYSTEME DE RESERVATION - COMPAGNIE AERIENNE   ");
            System.out.println("===================================================");
            System.out.println("  1. Gestion des passagers");
            System.out.println("  2. Gestion des vols");
            System.out.println("  3. Gestion des reservations");
            System.out.println("  4. Gestion des pilotes");
            System.out.println("  5. Gestion du personnel cabine");
            System.out.println("  6. Gestion des avions");
            System.out.println("  7. Gestion des aeroports");
            System.out.println("  8. Statistiques");
            System.out.println("  0. Quitter");
            System.out.println("---------------------------------------------------");
            choix = saisirEntier("Votre choix : ");

            if (choix == 1) menuPassagers();
            else if (choix == 2) menuVols();
            else if (choix == 3) menuReservations();
            else if (choix == 4) menuPilotes();
            else if (choix == 5) menuPersonnelCabine();
            else if (choix == 6) menuAvions();
            else if (choix == 7) menuAeroports();
            else if (choix == 8) afficherStatistiques();
            else if (choix == 0) System.out.println("Au revoir !");
            else System.out.println("Choix invalide, veuillez reessayer.");
        }
    }

    // ==================== PASSAGERS ====================

    private void menuPassagers() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("\n--- Gestion des passagers ---");
            System.out.println("  1. Ajouter un passager");
            System.out.println("  2. Lister les passagers");
            System.out.println("  3. Rechercher un passager");
            System.out.println("  4. Supprimer un passager");
            System.out.println("  0. Retour au menu principal");
            choix = saisirEntier("Votre choix : ");

            if (choix == 1) ajouterPassager();
            else if (choix == 2) listerPassagers();
            else if (choix == 3) rechercherPassager();
            else if (choix == 4) supprimerPassager();
            else if (choix != 0) System.out.println("Choix invalide.");
        }
    }

    private void ajouterPassager() {
        System.out.println("\n-- Ajout d'un passager --");
        String id = saisirTexte("Identifiant (ex: PA010) : ");
        if (Passager.trouver(id) != null) {
            System.out.println("Un passager avec cet identifiant existe deja.");
            return;
        }
        String nom = saisirTexte("Nom complet : ");
        String adresse = saisirTexte("Adresse : ");
        String contact = saisirTexte("Telephone : ");
        String passeport = saisirTexte("Numero de passeport : ");

        Passager p = new Passager(id, nom, adresse, contact, passeport);
        Passager.ajouter(p);
        System.out.println("Passager " + nom + " enregistre avec succes.");
    }

    private void listerPassagers() {
        ArrayList<Passager> liste = Passager.getListe();
        System.out.println("\n-- Liste des passagers (" + liste.size() + ") --");
        if (liste.size() == 0) {
            System.out.println("Aucun passager enregistre.");
            return;
        }
        for (int i = 0; i < liste.size(); i++) {
            Passager p = liste.get(i);
            System.out.println("  " + (i + 1) + ". [" + p.getIdentifiant() + "] "
                    + p.getNom() + " | Passeport: " + p.getPasseport()
                    + " | Reservations: " + p.getReservations().size());
        }
    }

    private void rechercherPassager() {
        String id = saisirTexte("Identifiant du passager : ");
        Passager p = Passager.trouver(id);
        if (p == null) {
            System.out.println("Passager introuvable.");
        } else {
            System.out.println();
            p.obtenirInfos();
            p.obtenirReservations();
        }
    }

    private void supprimerPassager() {
        String id = saisirTexte("Identifiant du passager a supprimer : ");
        if (Passager.supprimer(id)) {
            System.out.println("Passager supprime.");
        } else {
            System.out.println("Passager introuvable.");
        }
    }

    // ==================== VOLS ====================

    private void menuVols() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("\n--- Gestion des vols ---");
            System.out.println("  1. Planifier un nouveau vol");
            System.out.println("  2. Lister tous les vols");
            System.out.println("  3. Informations sur un vol");
            System.out.println("  4. Affecter un equipage a un vol");
            System.out.println("  5. Affecter un avion a un vol");
            System.out.println("  6. Annuler un vol");
            System.out.println("  7. Liste des passagers d'un vol");
            System.out.println("  0. Retour au menu principal");
            choix = saisirEntier("Votre choix : ");

            if (choix == 1) planifierVol();
            else if (choix == 2) listerVols();
            else if (choix == 3) infosVol();
            else if (choix == 4) affecterEquipage();
            else if (choix == 5) affecterAvionVol();
            else if (choix == 6) annulerVol();
            else if (choix == 7) listingPassagersVol();
            else if (choix != 0) System.out.println("Choix invalide.");
        }
    }

    private void planifierVol() {
        System.out.println("\n-- Planification d'un vol --");
        String numero = saisirTexte("Numero de vol (ex: AF202) : ");
        if (Vol.trouver(numero) != null) {
            System.out.println("Un vol avec ce numero existe deja.");
            return;
        }
        String origine = saisirTexte("Origine : ");
        String destination = saisirTexte("Destination : ");
        String depart = saisirTexte("Date/heure de depart (ex: 2025-06-01 08:00) : ");
        String arrivee = saisirTexte("Date/heure d'arrivee (ex: 2025-06-01 12:00) : ");

        Vol v = new Vol(numero, origine, destination, depart, arrivee);
        Vol.ajouter(v);
        v.planifierVol();
    }

    private void listerVols() {
        ArrayList<Vol> liste = Vol.getListe();
        System.out.println("\n-- Liste des vols (" + liste.size() + ") --");
        if (liste.size() == 0) {
            System.out.println("Aucun vol planifie.");
            return;
        }
        for (int i = 0; i < liste.size(); i++) {
            Vol v = liste.get(i);
            System.out.println("  " + (i + 1) + ". [" + v.getNumeroVol() + "] "
                    + v.getOrigine() + " -> " + v.getDestination()
                    + " | Depart: " + v.getDateHeureDepart()
                    + " | Etat: " + v.getEtat()
                    + " | Passagers: " + v.getPassagers().size());
        }
    }

    private void infosVol() {
        String numero = saisirTexte("Numero de vol : ");
        Vol v = Vol.trouver(numero);
        if (v == null) {
            System.out.println("Vol introuvable.");
        } else {
            System.out.println();
            v.obtenirInfos();
        }
    }

    private void affecterEquipage() {
        String numero = saisirTexte("Numero de vol : ");
        Vol v = Vol.trouver(numero);
        if (v == null) {
            System.out.println("Vol introuvable.");
            return;
        }

        ArrayList<Pilote> pilotes = Pilote.getListePilotes();
        if (pilotes.size() == 0) {
            System.out.println("Aucun pilote disponible. Ajoutez d'abord un pilote.");
            return;
        }
        System.out.println("Pilotes disponibles :");
        for (int i = 0; i < pilotes.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + pilotes.get(i).getNom()
                    + " | Licence: " + pilotes.get(i).getLicence()
                    + " | " + pilotes.get(i).getHeuresDeVol() + "h de vol");
        }
        int idxPilote = saisirEntier("Choisir un pilote (numero) : ") - 1;
        if (idxPilote < 0 || idxPilote >= pilotes.size()) {
            System.out.println("Choix invalide.");
            return;
        }
        Pilote pilote = pilotes.get(idxPilote);

        ArrayList<PersonnelCabine> tousPC = PersonnelCabine.getListePersonnelCabine();
        if (tousPC.size() == 0) {
            System.out.println("Aucun personnel cabine disponible. Ajoutez d'abord du personnel.");
            return;
        }
        System.out.println("Personnel cabine disponible :");
        for (int i = 0; i < tousPC.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + tousPC.get(i).getNom()
                    + " | " + tousPC.get(i).getQualification());
        }
        int nbPC = saisirEntier("Combien de membres du personnel cabine affecter ? ");
        ArrayList<PersonnelCabine> equipe = new ArrayList<PersonnelCabine>();
        for (int i = 0; i < nbPC; i++) {
            int idxPC = saisirEntier("  Membre " + (i + 1) + " (numero) : ") - 1;
            if (idxPC >= 0 && idxPC < tousPC.size()) {
                equipe.add(tousPC.get(idxPC));
            } else {
                System.out.println("  Numero invalide, membre ignore.");
            }
        }

        v.affecterVol(pilote, equipe);
    }

    private void affecterAvionVol() {
        String numero = saisirTexte("Numero de vol : ");
        Vol v = Vol.trouver(numero);
        if (v == null) {
            System.out.println("Vol introuvable.");
            return;
        }

        ArrayList<Avion> avions = Avion.getListe();
        if (avions.size() == 0) {
            System.out.println("Aucun avion enregistre.");
            return;
        }
        System.out.println("Avions :");
        for (int i = 0; i < avions.size(); i++) {
            Avion a = avions.get(i);
            System.out.println("  " + (i + 1) + ". [" + a.getImmatriculation() + "] "
                    + a.getModele() + " | Capacite: " + a.getCapacite()
                    + " | Disponible: " + (a.verifierDisponibilite() ? "Oui" : "Non"));
        }
        int idx = saisirEntier("Choisir un avion (numero) : ") - 1;
        if (idx < 0 || idx >= avions.size()) {
            System.out.println("Choix invalide.");
            return;
        }
        Avion avion = avions.get(idx);
        if (!avion.verifierDisponibilite()) {
            System.out.println("Cet avion n'est pas disponible, il est deja affecte a un vol.");
            return;
        }
        avion.affecterVol(v);
        v.setAvion(avion);
    }

    private void annulerVol() {
        String numero = saisirTexte("Numero de vol a annuler : ");
        Vol v = Vol.trouver(numero);
        if (v == null) {
            System.out.println("Vol introuvable.");
        } else {
            v.annulerVol();
        }
    }

    private void listingPassagersVol() {
        String numero = saisirTexte("Numero de vol : ");
        Vol v = Vol.trouver(numero);
        if (v == null) {
            System.out.println("Vol introuvable.");
        } else {
            v.listingPassager();
        }
    }

    // ==================== RESERVATIONS ====================

    private void menuReservations() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("\n--- Gestion des reservations ---");
            System.out.println("  1. Effectuer une reservation");
            System.out.println("  2. Annuler une reservation");
            System.out.println("  3. Lister toutes les reservations");
            System.out.println("  4. Informations sur une reservation");
            System.out.println("  0. Retour au menu principal");
            choix = saisirEntier("Votre choix : ");

            if (choix == 1) effectuerReservation();
            else if (choix == 2) annulerReservation();
            else if (choix == 3) listerReservations();
            else if (choix == 4) infosReservation();
            else if (choix != 0) System.out.println("Choix invalide.");
        }
    }

    private void effectuerReservation() {
        System.out.println("\n-- Nouvelle reservation --");
        listerPassagers();
        String idPassager = saisirTexte("Identifiant du passager : ");
        Passager p = Passager.trouver(idPassager);
        if (p == null) {
            System.out.println("Passager introuvable.");
            return;
        }

        listerVols();
        String numeroVol = saisirTexte("Numero de vol : ");
        Vol v = Vol.trouver(numeroVol);
        if (v == null) {
            System.out.println("Vol introuvable.");
            return;
        }
        if (v.getEtat().equals("Annule")) {
            System.out.println("Impossible de reserver : ce vol est annule.");
            return;
        }

        String date = saisirTexte("Date de reservation (ex: 2025-05-01) : ");
        p.reserverVol(v, date);
    }

    private void annulerReservation() {
        String idPassager = saisirTexte("Identifiant du passager : ");
        Passager p = Passager.trouver(idPassager);
        if (p == null) {
            System.out.println("Passager introuvable.");
            return;
        }
        p.obtenirReservations();
        if (p.getReservations().size() == 0) {
            return;
        }
        String numResa = saisirTexte("Numero de reservation a annuler : ");
        p.annulerReservation(numResa);
    }

    private void listerReservations() {
        ArrayList<Reservation> liste = Reservation.getListe();
        System.out.println("\n-- Liste des reservations (" + liste.size() + ") --");
        if (liste.size() == 0) {
            System.out.println("Aucune reservation enregistree.");
            return;
        }
        for (int i = 0; i < liste.size(); i++) {
            Reservation r = liste.get(i);
            System.out.println("  " + (i + 1) + ". [" + r.getNumeroReservation() + "] "
                    + r.getPassager().getNom()
                    + " | Vol: " + r.getVol().getNumeroVol()
                    + " (" + r.getVol().getDestination() + ")"
                    + " | Statut: " + r.getStatut());
        }
    }

    private void infosReservation() {
        String num = saisirTexte("Numero de reservation (ex: RES001) : ");
        Reservation r = Reservation.trouver(num);
        if (r == null) {
            System.out.println("Reservation introuvable.");
        } else {
            System.out.println();
            r.obtenirInfos();
        }
    }

    // ==================== PILOTES ====================

    private void menuPilotes() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("\n--- Gestion des pilotes ---");
            System.out.println("  1. Ajouter un pilote");
            System.out.println("  2. Lister les pilotes");
            System.out.println("  3. Informations sur un pilote");
            System.out.println("  4. Supprimer un pilote");
            System.out.println("  0. Retour au menu principal");
            choix = saisirEntier("Votre choix : ");

            if (choix == 1) ajouterPilote();
            else if (choix == 2) listerPilotes();
            else if (choix == 3) infosPilote();
            else if (choix == 4) supprimerPilote();
            else if (choix != 0) System.out.println("Choix invalide.");
        }
    }

    private void ajouterPilote() {
        System.out.println("\n-- Ajout d'un pilote --");
        String id = saisirTexte("Identifiant (ex: P010) : ");
        String nom = saisirTexte("Nom complet : ");
        String adresse = saisirTexte("Adresse : ");
        String contact = saisirTexte("Telephone : ");
        String numEmp = saisirTexte("Numero employe (ex: EMP010) : ");
        String dateEmb = saisirTexte("Date embauche (ex: 2020-01-15) : ");
        String licence = saisirTexte("Numero de licence : ");
        int heures = saisirEntier("Heures de vol : ");

        Pilote p = new Pilote(id, nom, adresse, contact, numEmp, dateEmb, licence, heures);
        Pilote.ajouter(p);
        System.out.println("Pilote " + nom + " enregistre avec succes.");
    }

    private void listerPilotes() {
        ArrayList<Pilote> liste = Pilote.getListePilotes();
        System.out.println("\n-- Liste des pilotes (" + liste.size() + ") --");
        if (liste.size() == 0) {
            System.out.println("Aucun pilote enregistre.");
            return;
        }
        for (int i = 0; i < liste.size(); i++) {
            Pilote p = liste.get(i);
            System.out.println("  " + (i + 1) + ". " + p.getNom()
                    + " | Licence: " + p.getLicence()
                    + " | " + p.getHeuresDeVol() + "h de vol"
                    + " | Vol assigne: " + (p.getVolAssigne() != null ? p.getVolAssigne().getNumeroVol() : "Aucun"));
        }
    }

    private void infosPilote() {
        String numEmp = saisirTexte("Numero employe du pilote : ");
        Pilote p = Pilote.trouver(numEmp);
        if (p == null) {
            System.out.println("Pilote introuvable.");
        } else {
            System.out.println();
            p.obtenirInfos();
        }
    }

    private void supprimerPilote() {
        String numEmp = saisirTexte("Numero employe du pilote a supprimer : ");
        if (Pilote.supprimer(numEmp)) {
            System.out.println("Pilote supprime.");
        } else {
            System.out.println("Pilote introuvable.");
        }
    }

    // ==================== PERSONNEL CABINE ====================

    private void menuPersonnelCabine() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("\n--- Gestion du personnel cabine ---");
            System.out.println("  1. Ajouter un membre");
            System.out.println("  2. Lister le personnel");
            System.out.println("  3. Informations sur un membre");
            System.out.println("  4. Supprimer un membre");
            System.out.println("  0. Retour au menu principal");
            choix = saisirEntier("Votre choix : ");

            if (choix == 1) ajouterPersonnelCabine();
            else if (choix == 2) listerPersonnelCabine();
            else if (choix == 3) infosPersonnelCabine();
            else if (choix == 4) supprimerPersonnelCabine();
            else if (choix != 0) System.out.println("Choix invalide.");
        }
    }

    private void ajouterPersonnelCabine() {
        System.out.println("\n-- Ajout du personnel cabine --");
        String id = saisirTexte("Identifiant (ex: PC010) : ");
        String nom = saisirTexte("Nom complet : ");
        String adresse = saisirTexte("Adresse : ");
        String contact = saisirTexte("Telephone : ");
        String numEmp = saisirTexte("Numero employe (ex: EMP010) : ");
        String dateEmb = saisirTexte("Date embauche (ex: 2020-01-15) : ");
        String qualification = saisirTexte("Qualification : ");

        PersonnelCabine pc = new PersonnelCabine(id, nom, adresse, contact, numEmp, dateEmb, qualification);
        PersonnelCabine.ajouter(pc);
        System.out.println("Personnel cabine " + nom + " enregistre avec succes.");
    }

    private void listerPersonnelCabine() {
        ArrayList<PersonnelCabine> liste = PersonnelCabine.getListePersonnelCabine();
        System.out.println("\n-- Personnel cabine (" + liste.size() + ") --");
        if (liste.size() == 0) {
            System.out.println("Aucun personnel cabine enregistre.");
            return;
        }
        for (int i = 0; i < liste.size(); i++) {
            PersonnelCabine pc = liste.get(i);
            System.out.println("  " + (i + 1) + ". " + pc.getNom()
                    + " | " + pc.getQualification()
                    + " | Vol assigne: " + (pc.getVolAssigne() != null ? pc.getVolAssigne().getNumeroVol() : "Aucun"));
        }
    }

    private void infosPersonnelCabine() {
        String numEmp = saisirTexte("Numero employe : ");
        PersonnelCabine pc = PersonnelCabine.trouver(numEmp);
        if (pc == null) {
            System.out.println("Personnel introuvable.");
        } else {
            System.out.println();
            pc.obtenirInfos();
        }
    }

    private void supprimerPersonnelCabine() {
        String numEmp = saisirTexte("Numero employe a supprimer : ");
        if (PersonnelCabine.supprimer(numEmp)) {
            System.out.println("Personnel cabine supprime.");
        } else {
            System.out.println("Personnel introuvable.");
        }
    }

    // ==================== AVIONS ====================

    private void menuAvions() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("\n--- Gestion des avions ---");
            System.out.println("  1. Ajouter un avion");
            System.out.println("  2. Lister les avions");
            System.out.println("  3. Informations sur un avion");
            System.out.println("  4. Verifier disponibilite d'un avion");
            System.out.println("  5. Supprimer un avion");
            System.out.println("  0. Retour au menu principal");
            choix = saisirEntier("Votre choix : ");

            if (choix == 1) ajouterAvion();
            else if (choix == 2) listerAvions();
            else if (choix == 3) infosAvion();
            else if (choix == 4) verifierDispoAvion();
            else if (choix == 5) supprimerAvion();
            else if (choix != 0) System.out.println("Choix invalide.");
        }
    }

    private void ajouterAvion() {
        System.out.println("\n-- Ajout d'un avion --");
        String immat = saisirTexte("Immatriculation (ex: F-GKXB) : ");
        if (Avion.trouver(immat) != null) {
            System.out.println("Un avion avec cette immatriculation existe deja.");
            return;
        }
        String modele = saisirTexte("Modele (ex: Airbus A320) : ");
        int capacite = saisirEntier("Capacite (nombre de sieges) : ");

        Avion a = new Avion(immat, modele, capacite);
        Avion.ajouter(a);
        System.out.println("Avion " + immat + " enregistre avec succes.");
    }

    private void listerAvions() {
        ArrayList<Avion> liste = Avion.getListe();
        System.out.println("\n-- Liste des avions (" + liste.size() + ") --");
        if (liste.size() == 0) {
            System.out.println("Aucun avion enregistre.");
            return;
        }
        for (int i = 0; i < liste.size(); i++) {
            Avion a = liste.get(i);
            System.out.println("  " + (i + 1) + ". [" + a.getImmatriculation() + "] "
                    + a.getModele()
                    + " | Capacite: " + a.getCapacite()
                    + " | Disponible: " + (a.verifierDisponibilite() ? "Oui" : "Non"));
        }
    }

    private void infosAvion() {
        String immat = saisirTexte("Immatriculation : ");
        Avion a = Avion.trouver(immat);
        if (a == null) {
            System.out.println("Avion introuvable.");
        } else {
            System.out.println();
            a.obtenirInfos();
        }
    }

    private void verifierDispoAvion() {
        String immat = saisirTexte("Immatriculation : ");
        Avion a = Avion.trouver(immat);
        if (a == null) {
            System.out.println("Avion introuvable.");
        } else {
            if (a.verifierDisponibilite()) {
                System.out.println("Avion " + immat + " : DISPONIBLE");
            } else {
                System.out.println("Avion " + immat + " : NON DISPONIBLE (affecte au vol "
                        + a.getVolAssigne().getNumeroVol() + ")");
            }
        }
    }

    private void supprimerAvion() {
        String immat = saisirTexte("Immatriculation a supprimer : ");
        if (Avion.supprimer(immat)) {
            System.out.println("Avion supprime.");
        } else {
            System.out.println("Avion introuvable.");
        }
    }

    // ==================== AEROPORTS ====================

    private void menuAeroports() {
        int choix = -1;
        while (choix != 0) {
            System.out.println("\n--- Gestion des aeroports ---");
            System.out.println("  1. Ajouter un aeroport");
            System.out.println("  2. Lister les aeroports");
            System.out.println("  3. Informations sur un aeroport");
            System.out.println("  4. Affecter un vol a un aeroport");
            System.out.println("  5. Supprimer un aeroport");
            System.out.println("  0. Retour au menu principal");
            choix = saisirEntier("Votre choix : ");

            if (choix == 1) ajouterAeroport();
            else if (choix == 2) listerAeroports();
            else if (choix == 3) infosAeroport();
            else if (choix == 4) affecterVolAeroport();
            else if (choix == 5) supprimerAeroport();
            else if (choix != 0) System.out.println("Choix invalide.");
        }
    }

    private void ajouterAeroport() {
        System.out.println("\n-- Ajout d'un aeroport --");
        String code = saisirTexte("Code IATA (ex: CDG) : ");
        if (Aeroport.trouver(code) != null) {
            System.out.println("Un aeroport avec ce code existe deja.");
            return;
        }
        String ville = saisirTexte("Ville : ");
        String desc = saisirTexte("Nom complet : ");

        Aeroport a = new Aeroport(code, ville, desc);
        Aeroport.ajouter(a);
        System.out.println("Aeroport " + code + " enregistre avec succes.");
    }

    private void listerAeroports() {
        ArrayList<Aeroport> liste = Aeroport.getListe();
        System.out.println("\n-- Liste des aeroports (" + liste.size() + ") --");
        if (liste.size() == 0) {
            System.out.println("Aucun aeroport enregistre.");
            return;
        }
        for (int i = 0; i < liste.size(); i++) {
            Aeroport a = liste.get(i);
            System.out.println("  " + (i + 1) + ". [" + a.getNom() + "] "
                    + a.getDescription() + " - " + a.getVille()
                    + " | Departs: " + a.getVolsDepart().size()
                    + " | Arrivees: " + a.getVolsArrivee().size());
        }
    }

    private void infosAeroport() {
        String code = saisirTexte("Code IATA de l'aeroport : ");
        Aeroport a = Aeroport.trouver(code);
        if (a == null) {
            System.out.println("Aeroport introuvable.");
        } else {
            System.out.println();
            a.obtenirInfos();
        }
    }

    private void affecterVolAeroport() {
        String code = saisirTexte("Code IATA de l'aeroport : ");
        Aeroport a = Aeroport.trouver(code);
        if (a == null) {
            System.out.println("Aeroport introuvable.");
            return;
        }

        listerVols();
        String numVol = saisirTexte("Numero de vol : ");
        Vol v = Vol.trouver(numVol);
        if (v == null) {
            System.out.println("Vol introuvable.");
            return;
        }

        int type = saisirEntier("1. Depart   2. Arrivee : ");
        if (type == 1) {
            a.affecterVol(v, true);
        } else if (type == 2) {
            a.affecterVol(v, false);
        } else {
            System.out.println("Choix invalide.");
        }
    }

    private void supprimerAeroport() {
        String code = saisirTexte("Code IATA a supprimer : ");
        if (Aeroport.supprimer(code)) {
            System.out.println("Aeroport supprime.");
        } else {
            System.out.println("Aeroport introuvable.");
        }
    }

    // ==================== STATISTIQUES (BONUS) ====================

    private void afficherStatistiques() {
        System.out.println("\n===== RAPPORT STATISTIQUE =====");

        ArrayList<Vol> vols = Vol.getListe();
        ArrayList<Passager> passagers = Passager.getListe();
        ArrayList<Reservation> reservations = Reservation.getListe();
        ArrayList<Pilote> pilotes = Pilote.getListePilotes();
        ArrayList<PersonnelCabine> personnel = PersonnelCabine.getListePersonnelCabine();
        ArrayList<Avion> avions = Avion.getListe();
        ArrayList<Aeroport> aeroports = Aeroport.getListe();

        System.out.println("\n-- Inventaire --");
        System.out.println("  Vols planifies       : " + vols.size());
        System.out.println("  Passagers            : " + passagers.size());
        System.out.println("  Reservations         : " + reservations.size());
        System.out.println("  Pilotes              : " + pilotes.size());
        System.out.println("  Personnel cabine     : " + personnel.size());
        System.out.println("  Avions               : " + avions.size());
        System.out.println("  Aeroports            : " + aeroports.size());

        // Etat des vols
        if (vols.size() > 0) {
            int nbPlanifies = 0;
            int nbAnnules = 0;
            for (int i = 0; i < vols.size(); i++) {
                if (vols.get(i).getEtat().equals("Planifie")) nbPlanifies++;
                else if (vols.get(i).getEtat().equals("Annule")) nbAnnules++;
            }
            System.out.println("\n-- Etat des vols --");
            System.out.println("  Planifies            : " + nbPlanifies);
            System.out.println("  Annules              : " + nbAnnules);
        }

        // Vol avec le plus de passagers
        if (vols.size() > 0) {
            Vol volMax = vols.get(0);
            for (int i = 1; i < vols.size(); i++) {
                if (vols.get(i).getPassagers().size() > volMax.getPassagers().size()) {
                    volMax = vols.get(i);
                }
            }
            System.out.println("\n-- Vol le plus charge --");
            System.out.println("  " + volMax.getNumeroVol()
                    + " (" + volMax.getOrigine() + " -> " + volMax.getDestination() + ")"
                    + " : " + volMax.getPassagers().size() + " passager(s)");
        }

        // Destinations les plus populaires
        if (reservations.size() > 0) {
            System.out.println("\n-- Destinations les plus populaires --");
            ArrayList<String> destinations = new ArrayList<String>();
            ArrayList<Integer> compteurs = new ArrayList<Integer>();

            for (int i = 0; i < reservations.size(); i++) {
                String dest = reservations.get(i).getVol().getDestination();
                boolean trouve = false;
                for (int j = 0; j < destinations.size(); j++) {
                    if (destinations.get(j).equals(dest)) {
                        compteurs.set(j, compteurs.get(j) + 1);
                        trouve = true;
                        break;
                    }
                }
                if (!trouve) {
                    destinations.add(dest);
                    compteurs.add(1);
                }
            }

            for (int i = 0; i < destinations.size(); i++) {
                System.out.println("  " + destinations.get(i) + " : " + compteurs.get(i) + " reservation(s)");
            }
        }

        // Statut des reservations
        if (reservations.size() > 0) {
            int confirmees = 0;
            int annulees = 0;
            int enAttente = 0;
            for (int i = 0; i < reservations.size(); i++) {
                String statut = reservations.get(i).getStatut();
                if (statut.equals("Confirme")) confirmees++;
                else if (statut.equals("Annule")) annulees++;
                else enAttente++;
            }
            System.out.println("\n-- Statut des reservations --");
            System.out.println("  Confirmees           : " + confirmees);
            System.out.println("  Annulees             : " + annulees);
            System.out.println("  En attente           : " + enAttente);
        }

        // Avions disponibles
        if (avions.size() > 0) {
            int dispos = 0;
            for (int i = 0; i < avions.size(); i++) {
                if (avions.get(i).verifierDisponibilite()) dispos++;
            }
            System.out.println("\n-- Flotte --");
            System.out.println("  Avions disponibles   : " + dispos + " / " + avions.size());
        }

        System.out.println("\n================================");
    }

    // ==================== UTILITAIRES ====================

    private String saisirTexte(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private int saisirEntier(String message) {
        System.out.print(message);
        int valeur = 0;
        try {
            valeur = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entree invalide, 0 utilise par defaut.");
        }
        return valeur;
    }
}
