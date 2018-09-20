package pico.covoitapp.Model.Api;

public class UserInfo {

    private String _id;

    private String email;

    private String firstname;

    private String lastname;


    public UserInfo(String _id, String email, String firstname, String lastname) {
        this._id = _id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
