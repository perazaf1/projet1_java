public abstract class Personne {
    private String identifiant;
    private String nom;
    private String adresse;
    private String contact;

    public Personne(String identifiant, String nom, String adresse, String contact) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
    }

    public String getIdentifiant() { return identifiant; }
    public void setIdentifiant(String identifiant) { this.identifiant = identifiant; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public void obtenirInfos() {
        System.out.println("Identifiant : " + identifiant);
        System.out.println("Nom         : " + nom);
        System.out.println("Adresse     : " + adresse);
        System.out.println("Contact     : " + contact);
    }
}
