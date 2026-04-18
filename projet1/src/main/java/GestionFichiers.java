import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestionFichiers {

    // =========================================================
    //  VOLS
    // =========================================================

    /**
     * Importe les vols depuis un fichier CSV.
     * Format attendu : NumeroVol;Origine;Destination;DateDepart;DateArrivee
     */
    public static int importerVols(String chemin) {
        int nbImportes = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(chemin));
            String ligne;
            reader.readLine(); // ignorer l'en-tete
            while ((ligne = reader.readLine()) != null) {
                String[] colonnes = ligne.split(";");
                if (colonnes.length == 5) {
                    String numeroVol  = colonnes[0].trim();
                    String origine    = colonnes[1].trim();
                    String destination = colonnes[2].trim();
                    String dateDepart  = colonnes[3].trim();
                    String dateArrivee = colonnes[4].trim();
                    if (Vol.trouver(numeroVol) == null) {
                        Vol v = new Vol(numeroVol, origine, destination, dateDepart, dateArrivee);
                        Vol.ajouter(v);
                        nbImportes++;
                    }
                }
            }
            reader.close();
            System.out.println(nbImportes + " vol(s) importe(s) depuis " + chemin);
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
        return nbImportes;
    }

    /**
     * Exporte tous les vols dans un fichier CSV.
     * Format : NumeroVol;Origine;Destination;DateDepart;DateArrivee;Etat
     */
    public static void exporterVols(String chemin) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(chemin, false));
            writer.write("NumeroVol;Origine;Destination;DateDepart;DateArrivee;Etat");
            writer.newLine();
            ArrayList<Vol> vols = Vol.getListe();
            for (int i = 0; i < vols.size(); i++) {
                Vol v = vols.get(i);
                writer.write(v.getNumeroVol() + ";"
                        + v.getOrigine() + ";"
                        + v.getDestination() + ";"
                        + v.getDateHeureDepart() + ";"
                        + v.getDateHeureArrivee() + ";"
                        + v.getEtat());
                writer.newLine();
            }
            writer.close();
            System.out.println(vols.size() + " vol(s) exporte(s) dans " + chemin);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'ecriture du fichier : " + e.getMessage());
        }
    }

    // =========================================================
    //  PASSAGERS
    // =========================================================

    /**
     * Importe les passagers depuis un fichier CSV.
     * Format attendu : Identifiant;Nom;Adresse;Contact;Passeport
     */
    public static int importerPassagers(String chemin) {
        int nbImportes = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(chemin));
            String ligne;
            reader.readLine(); // ignorer l'en-tete
            while ((ligne = reader.readLine()) != null) {
                String[] colonnes = ligne.split(";");
                if (colonnes.length == 5) {
                    String id        = colonnes[0].trim();
                    String nom       = colonnes[1].trim();
                    String adresse   = colonnes[2].trim();
                    String contact   = colonnes[3].trim();
                    String passeport = colonnes[4].trim();
                    if (Passager.trouver(id) == null) {
                        Passager p = new Passager(id, nom, adresse, contact, passeport);
                        Passager.ajouter(p);
                        nbImportes++;
                    }
                }
            }
            reader.close();
            System.out.println(nbImportes + " passager(s) importe(s) depuis " + chemin);
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
        return nbImportes;
    }

    /**
     * Sauvegarde tous les passagers dans un fichier CSV.
     * Format : Identifiant;Nom;Adresse;Contact;Passeport
     */
    public static void sauvegarderPassagers(String chemin) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(chemin, false));
            writer.write("Identifiant;Nom;Adresse;Contact;Passeport");
            writer.newLine();
            ArrayList<Passager> passagers = Passager.getListe();
            for (int i = 0; i < passagers.size(); i++) {
                Passager p = passagers.get(i);
                writer.write(p.getIdentifiant() + ";"
                        + p.getNom() + ";"
                        + p.getAdresse() + ";"
                        + p.getContact() + ";"
                        + p.getPasseport());
                writer.newLine();
            }
            writer.close();
            System.out.println(passagers.size() + " passager(s) sauvegarde(s) dans " + chemin);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'ecriture du fichier : " + e.getMessage());
        }
    }

    // =========================================================
    //  RESERVATIONS
    // =========================================================

    /**
     * Ajoute une reservation a la fin du fichier CSV (mode append).
     * Format : Passeport;Nom;Contact;NumeroVol;DateReservation
     * Correspond au schema du cours (slide 6) : saveReservationFile()
     */
    public static void sauvegarderReservation(String chemin, Passager passager, Vol vol, String dateReservation) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(chemin, true));
            writer.write(passager.getPasseport() + ";"
                    + passager.getNom() + ";"
                    + passager.getContact() + ";"
                    + vol.getNumeroVol() + ";"
                    + dateReservation);
            writer.newLine();
            writer.close();
            System.out.println("Reservation sauvegardee dans " + chemin);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'ecriture du fichier : " + e.getMessage());
        }
    }

    // =========================================================
    //  AEROPORTS
    // =========================================================

    /**
     * Importe les aeroports depuis un fichier CSV (codes ISO IATA).
     * Format attendu : CodeIATA;Ville;NomComplet
     */
    public static int importerAeroports(String chemin) {
        int nbImportes = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(chemin));
            String ligne;
            reader.readLine(); // ignorer l'en-tete
            while ((ligne = reader.readLine()) != null) {
                String[] colonnes = ligne.split(";");
                if (colonnes.length == 3) {
                    String code       = colonnes[0].trim();
                    String ville      = colonnes[1].trim();
                    String nomComplet = colonnes[2].trim();
                    if (Aeroport.trouver(code) == null) {
                        Aeroport a = new Aeroport(code, ville, nomComplet);
                        Aeroport.ajouter(a);
                        nbImportes++;
                    }
                }
            }
            reader.close();
            System.out.println(nbImportes + " aeroport(s) importe(s) depuis " + chemin);
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
        return nbImportes;
    }
}
