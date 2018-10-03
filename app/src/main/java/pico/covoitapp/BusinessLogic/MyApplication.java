package pico.covoitapp.BusinessLogic;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import pico.covoitapp.Utils.FireBaseHelper;

public class MyApplication extends Application {

    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();

        Realm.init(getApplicationContext());

        RealmConfiguration config = new RealmConfiguration.
                Builder().
                deleteRealmIfMigrationNeeded().
                name(Realm.DEFAULT_REALM_NAME).
                schemaVersion(0).
                build();
        Realm.setDefaultConfiguration(config);


        FireBaseHelper.init();


    }


    public static Context getAppContext() {
        return MyApplication.context;
    }



}

