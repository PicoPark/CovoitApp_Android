package pico.covoitapp.Model.Api;

import java.io.Serializable;

public class Reservation implements Serializable {

    private int Id;
    private boolean isValidate;
    private MCovoiturage covoiturage;
    private MUtilisateur utilisateur;

    public Reservation() {
        covoiturage = new MCovoiturage();
        utilisateur = new MUtilisateur();
    }

    public Reservation(int id, boolean isValidate, MCovoiturage covoiturage, MUtilisateur utilisateur) {
        this.Id = id;
        this.isValidate = isValidate;
        this.covoiturage = covoiturage;
        this.utilisateur = utilisateur;
    }

    public MCovoiturage getCovoiturage() {
        return covoiturage;
    }

    public void setCovoiturage(MCovoiturage covoiturage) {
        this.covoiturage = covoiturage;
    }

    public MUtilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(MUtilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
