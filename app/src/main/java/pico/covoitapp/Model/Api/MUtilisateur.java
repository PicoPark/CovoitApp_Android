package pico.covoitapp.Model.Api;

public class MUtilisateur {

    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private String password;
    private String profile_image;
    private String numero;

    public MUtilisateur() {
    }

    public MUtilisateur(String mmail, String password) {
        this.mail = mmail;
        this.password = password;
    }

    public MUtilisateur(int id, String nom, String prenom, String mail, String password, String profil_image, String numero) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.profile_image = profil_image;
        this.numero = numero;
    }


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mmail) {
        this.mail = mmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfil_image() {
        return profile_image;
    }

    public void setProfil_image(String profil_image) {
        this.profile_image = profil_image;
    }


}


