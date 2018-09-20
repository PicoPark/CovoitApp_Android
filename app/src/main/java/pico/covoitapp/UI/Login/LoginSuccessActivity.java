package pico.covoitapp.UI.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import pico.covoitapp.BusinessLogic.UserManager;
import pico.covoitapp.R;

public class LoginSuccessActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        UserManager userMng = new UserManager(getBaseContext(),this);







    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
