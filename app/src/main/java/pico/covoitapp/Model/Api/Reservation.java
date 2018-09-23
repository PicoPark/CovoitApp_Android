package pico.covoitapp.Model.Api;

public class Reservation {

    private Covoiturage covoiturage;
    private  User utilisateur;

    public Reservation() {
        this(new Covoiturage(),new User());
    }

    public Reservation(Covoiturage covoiturage, User utilisateur) {
        this.covoiturage = covoiturage;
        this.utilisateur = utilisateur;
    }

    public Covoiturage getCovoiturage() {
        return covoiturage;
    }

    public void setCovoiturage(Covoiturage covoiturage) {
        this.covoiturage = covoiturage;
    }

    public User getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(User utilisateur) {
        this.utilisateur = utilisateur;
    }
}
