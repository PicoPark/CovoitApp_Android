package pico.covoitapp.Utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FireBaseHelper {

    private static FireBaseHelper _instance;
    FirebaseAuth _auth ;

    private FireBaseHelper(){
        signInAnonymously();

    }

    public static void init(){
        if(_instance == null)
            _instance = new FireBaseHelper();
    }


    public static FireBaseHelper get_instance(){
        if(_instance == null)
            _instance = new FireBaseHelper();
        return  _instance;
    }




    public FirebaseAuth getAnonymousUser(){
        if(_auth == null)
            signInAnonymously();
        return _auth;

    }

    private void signInAnonymously() {
        _auth = FirebaseAuth.getInstance();
        _auth.signInAnonymously();

    }



}
