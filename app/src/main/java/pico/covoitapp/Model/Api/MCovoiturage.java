package pico.covoitapp.Model.Api;

import java.io.Serializable;

public class MCovoiturage implements Serializable {

    private int id;
    private String depart;
    private String arrive;
    private int annee;
    private int mois;
    private int jours;
    private int heure;
    private int minutes;
    private int tarif;
    private String date_creation;
    private int nb_place;
    private boolean isFull;
    private int id_utilisateur;

    public MCovoiturage() {
    }


    public MCovoiturage(int id, String depart, String arrive, int annee, int mois, int jours, int heure, int minute, int tarif, String date_creation, int nb_place, boolean isFull, int id_utilisateur) {
        this.id = id;
        this.depart = depart;
        this.arrive = arrive;
        this.annee = annee;
        this.mois = mois;
        this.jours = jours;
        this.heure = heure;
        this.minutes = minute;
        this.tarif = tarif;
        this.date_creation = date_creation;
        this.nb_place = nb_place;
        this.isFull = isFull;
        this.id_utilisateur = id_utilisateur;
    }


    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getJours() {
        return jours;
    }

    public void setJours(int jours) {
        this.jours = jours;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }


    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
}

