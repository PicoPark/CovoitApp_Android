package pico.covoitapp.DataLayer.Realm;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import io.realm.Realm;
import io.realm.RealmResults;
import pico.covoitapp.Model.Realm.UserInfoRealm;
import pico.covoitapp.Model.Realm.UserRealm;

public class UserController {
    private static UserController instance;
    private final Realm realm;

    public UserController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static UserController with(Fragment fragment) {

        if (instance == null) {
            instance = new UserController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static UserController with(Activity activity) {

        if (instance == null) {
            instance = new UserController(activity.getApplication());
        }
        return instance;
    }

    public static UserController with(Application application) {

        if (instance == null) {
            instance = new UserController(application);
        }
        return instance;
    }

    public static UserController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }


    public RealmResults<UserRealm> getUsers() {

        return realm.where(UserRealm.class).findAll();
    }


    //find all objects in the Defi.class
    public UserRealm getUser(String email) {

        return realm.where(UserRealm.class).equalTo("email", email).findFirst();
    }

    public RealmResults<UserRealm> getAllUser(){
        return realm.where(UserRealm.class).findAll();
    }

    public UserRealm getUserConnected(Boolean isConnected) {

        return realm.where(UserRealm.class).equalTo("connected", isConnected).findFirst();
    }

    public UserInfoRealm getUserById(String id) {
        return realm.where(UserInfoRealm.class).equalTo("_id", id).findFirst();

    }


}
