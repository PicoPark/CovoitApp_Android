package pico.covoitapp.Model.Api;

public class User {

    private String email;

    private String password;

    private String firstname;

    private String phone;

    private String lastname;
    private String voiture;
    private String imageURL;

    public User() {
    }

    public User(String email, String password, String firstname, String phone, String lastname, String voiture, String imageURL) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.phone = phone;
        this.lastname = lastname;
        this.voiture = voiture;
        this.imageURL = imageURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVoiture() {
        return voiture;
    }

    public void setVoiture(String voiture) {
        this.voiture = voiture;
    }
}
