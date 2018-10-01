package pico.covoitapp.Model.Api;

public class MVehicule {

    private int id_vehicule;
    private String marque;
    private String model;
    private int nb_place;

    public MVehicule() {
    }

    public MVehicule(int id_vehicule, String marque, String model, int nb_place) {
        this.id_vehicule = id_vehicule;
        this.marque = marque;
        this.model = model;
        this.nb_place = nb_place;
    }

    public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }
}
