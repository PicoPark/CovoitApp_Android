package pico.covoitapp.BusinessLogic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Explode;



import io.realm.Realm;
import pico.covoitapp.DataLayer.Realm.UserController;

import pico.covoitapp.Model.Realm.UserRealm;
import pico.covoitapp.UI.Login.LoginActivity;
import pico.covoitapp.UI.Login.LoginSuccessActivity;

import pico.covoitapp.Utils.Interface.Retrofit.UserChannel;

public class UtilisateurManager {


    Context context;
    Activity activityReference;

    Realm realm;
    UserChannel userInterface;

    //  UserApi userApi = new UserApi();

    private static UtilisateurManager _instance;

    public static UtilisateurManager GetInstance(Activity act) {
        if (_instance == null) {
            _instance = new UtilisateurManager(act);
        }
        _instance.activityReference = act;
        return _instance;
    }

    private UtilisateurManager(Activity activityReference) {
        this.context = activityReference.getApplicationContext();
        this.activityReference = activityReference;
        realm = UserController.with(activityReference).getRealm();


    }


    public void logout() {
        final UserRealm userRealm = UserController.getInstance().getUserConnected(true);

        if (!(userRealm == null)) {


            realm.executeTransaction(new Realm.Transaction() {
                public void execute(Realm realm) {
                    userRealm.setConnected(false);
                    userRealm.setKeepConnection(false);
                    realm.copyToRealm(userRealm); // could be copyToRealmOrUpdate


                }

            });

        }

    }
}





