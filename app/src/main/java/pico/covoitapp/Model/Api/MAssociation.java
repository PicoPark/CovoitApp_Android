package pico.covoitapp.Model.Api;

public class MAssociation {

    private MVehicule Vehicule;
    private MUtilisateur Utilisateur;

    public MAssociation(MVehicule vehicule, MUtilisateur utilisateur) {
        Vehicule = vehicule;
        Utilisateur = utilisateur;
    }

    public MAssociation() {
        Vehicule = new MVehicule();
        Utilisateur = new MUtilisateur();

    }

    public MVehicule getVehicule() {
        return Vehicule;
    }

    public void setVehicule(MVehicule vehicule) {
        Vehicule = vehicule;
    }

    public MUtilisateur getUtilisateur() {
        return Utilisateur;
    }

    public void setUtilisateur(MUtilisateur utilisateur) {
        Utilisateur = utilisateur;
    }
}
