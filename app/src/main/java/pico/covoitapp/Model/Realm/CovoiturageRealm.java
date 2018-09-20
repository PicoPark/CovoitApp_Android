package pico.covoitapp.Model.Realm;

import java.util.Date;

import io.realm.RealmObject;

public class CovoiturageRealm extends RealmObject {

    private String _id;
    private int chauffeur;
    private String depart;
    private String arrive;
    private Date dateDepart;
    private int tarif;
    private Date dateCreation;
    private boolean isFull;


    public CovoiturageRealm() {
    }

    public CovoiturageRealm(String _id, int chauffeur, String depart, String arrive, Date dateDepart, int tarif, Date dateCreation) {
        this._id = _id;
        this.chauffeur = chauffeur;
        this.depart = depart;
        this.arrive = arrive;
        this.dateDepart = dateDepart;
        this.tarif = tarif;
        this.dateCreation = dateCreation;
        this.isFull = false;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(int chauffeur) {
        this.chauffeur = chauffeur;
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

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }
}
