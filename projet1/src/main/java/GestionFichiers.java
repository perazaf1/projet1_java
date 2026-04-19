import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestionFichiers {

    public static int importerVolsJSON(String chemin) {
        int nbImportes = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(chemin));
            StringBuilder sb = new StringBuilder();
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                sb.append(ligne);
            }
            reader.close();
            String json = sb.toString();

            int debutData = json.indexOf("\"data\":[");
            if (debutData == -1) return 0;
            int pos = debutData + 8;

            while (pos < json.length()) {
                int debutObj = json.indexOf("{", pos);
                if (debutObj == -1) break;
                String volJson = extraireObjetEquilibre(json, debutObj);
                if (volJson.isEmpty() || !volJson.contains("\"flight_date\"")) break;

                String flightJson = sousObjet(volJson, "flight");
                String iataVol = valeurChaine(flightJson, "iata");
                boolean codeshare = flightJson.contains("\"codeshared\":{");

                if (!codeshare && !iataVol.isEmpty() && Vol.trouver(iataVol) == null) {
                    String departJson = sousObjet(volJson, "departure");
                    String arriveeJson = sousObjet(volJson, "arrival");
                    String iataDepart    = valeurChaine(departJson, "iata");
                    String dateDepart    = valeurChaine(departJson, "scheduled");
                    String airportDepart = valeurChaine(departJson, "airport");
                    String iataArrivee    = valeurChaine(arriveeJson, "iata");
                    String dateArrivee    = valeurChaine(arriveeJson, "scheduled");
                    String airportArrivee = valeurChaine(arriveeJson, "airport");
                    String status = valeurChaine(volJson, "flight_status");

                    if (!iataDepart.isEmpty() && !iataArrivee.isEmpty()) {
                        Vol v = new Vol(iataVol, iataDepart, iataArrivee, dateDepart, dateArrivee);
                        v.setEtat(convertirEtat(status));
                        Vol.ajouter(v);
                        nbImportes++;

                        if (Aeroport.trouver(iataDepart) == null)
                            Aeroport.ajouter(new Aeroport(iataDepart, "", airportDepart));
                        if (Aeroport.trouver(iataArrivee) == null)
                            Aeroport.ajouter(new Aeroport(iataArrivee, "", airportArrivee));
                    }
                }
                pos = debutObj + volJson.length();
            }
            System.out.println(nbImportes + " vol(s) importe(s) depuis " + chemin);
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
        return nbImportes;
    }

    private static String convertirEtat(String status) {
        if (status.equals("cancelled")) return "Annulé";
        if (status.equals("active")) return "En cours";
        if (status.equals("landed")) return "Atterri";
        return "Planifié";
    }

    private static String valeurChaine(String json, String cle) {
        String pattern = "\"" + cle + "\":\"";
        int i = json.indexOf(pattern);
        if (i < 0) return "";
        i += pattern.length();
        int j = json.indexOf("\"", i);
        return j < 0 ? "" : json.substring(i, j);
    }

    private static String sousObjet(String json, String cle) {
        String pattern = "\"" + cle + "\":{";
        int i = json.indexOf(pattern);
        if (i < 0) return "";
        return extraireObjetEquilibre(json, i + pattern.length() - 1);
    }

    private static String extraireObjetEquilibre(String json, int debut) {
        int niveau = 0;
        for (int i = debut; i < json.length(); i++) {
            if (json.charAt(i) == '{') niveau++;
            else if (json.charAt(i) == '}') {
                niveau--;
                if (niveau == 0) return json.substring(debut, i + 1);
            }
        }
        return "";
    }

    public static int importerVols(String chemin) {
        int nbImportes = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(chemin));
            String ligne;
            reader.readLine();
            while ((ligne = reader.readLine()) != null) {
                String[] colonnes = ligne.split(";");
                if (colonnes.length == 5) {
                    String numeroVol   = colonnes[0].trim();
                    String origine     = colonnes[1].trim();
                    String destination = colonnes[2].trim();
                    String dateDepart  = colonnes[3].trim();
                    String dateArrivee = colonnes[4].trim();
                    if (Vol.trouver(numeroVol) == null) {
                        Vol.ajouter(new Vol(numeroVol, origine, destination, dateDepart, dateArrivee));
                        nbImportes++;
                    }
                }
            }
            reader.close();
            System.out.println(nbImportes + " vol(s) importe(s) depuis " + chemin);
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
        return nbImportes;
    }

    public static void exporterVols(String chemin) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(chemin, false));
            writer.write("NumeroVol;Origine;Destination;DateDepart;DateArrivee;Etat");
            writer.newLine();
            ArrayList<Vol> vols = Vol.getListe();
            for (int i = 0; i < vols.size(); i++) {
                Vol v = vols.get(i);
                writer.write(v.getNumeroVol() + ";" + v.getOrigine() + ";"
                        + v.getDestination() + ";" + v.getDateHeureDepart() + ";"
                        + v.getDateHeureArrivee() + ";" + v.getEtat());
                writer.newLine();
            }
            writer.close();
            System.out.println(vols.size() + " vol(s) exporte(s) dans " + chemin);
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    public static int importerPassagers(String chemin) {
        int nbImportes = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(chemin));
            String ligne;
            reader.readLine();
            while ((ligne = reader.readLine()) != null) {
                String[] colonnes = ligne.split(";");
                if (colonnes.length == 5) {
                    String id        = colonnes[0].trim();
                    String nom       = colonnes[1].trim();
                    String adresse   = colonnes[2].trim();
                    String contact   = colonnes[3].trim();
                    String passeport = colonnes[4].trim();
                    if (Passager.trouver(id) == null) {
                        Passager.ajouter(new Passager(id, nom, adresse, contact, passeport));
                        nbImportes++;
                    }
                }
            }
            reader.close();
            System.out.println(nbImportes + " passager(s) importe(s) depuis " + chemin);
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
        return nbImportes;
    }

    public static void sauvegarderPassagers(String chemin) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(chemin, false));
            writer.write("Identifiant;Nom;Adresse;Contact;Passeport");
            writer.newLine();
            ArrayList<Passager> passagers = Passager.getListe();
            for (int i = 0; i < passagers.size(); i++) {
                Passager p = passagers.get(i);
                writer.write(p.getIdentifiant() + ";" + p.getNom() + ";"
                        + p.getAdresse() + ";" + p.getContact() + ";" + p.getPasseport());
                writer.newLine();
            }
            writer.close();
            System.out.println(passagers.size() + " passager(s) sauvegarde(s) dans " + chemin);
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    public static void sauvegarderReservation(String chemin, Passager passager, Vol vol, String dateReservation) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(chemin, true));
            writer.write(passager.getPasseport() + ";" + passager.getNom() + ";"
                    + passager.getContact() + ";" + vol.getNumeroVol() + ";" + dateReservation);
            writer.newLine();
            writer.close();
            System.out.println("Reservation sauvegardee dans " + chemin);
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    public static int importerAeroports(String chemin) {
        int nbImportes = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(chemin));
            String ligne;
            reader.readLine();
            while ((ligne = reader.readLine()) != null) {
                String[] colonnes = ligne.split(";");
                if (colonnes.length == 3) {
                    String code       = colonnes[0].trim();
                    String ville      = colonnes[1].trim();
                    String nomComplet = colonnes[2].trim();
                    if (Aeroport.trouver(code) == null) {
                        Aeroport.ajouter(new Aeroport(code, ville, nomComplet));
                        nbImportes++;
                    }
                }
            }
            reader.close();
            System.out.println(nbImportes + " aeroport(s) importe(s) depuis " + chemin);
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
        return nbImportes;
    }
}
