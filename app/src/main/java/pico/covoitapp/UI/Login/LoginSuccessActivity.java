package pico.covoitapp.UI.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import pico.covoitapp.BusinessLogic.UtilisateurManager;
import pico.covoitapp.R;
import pico.covoitapp.UI.DashboardActivity;

public class LoginSuccessActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
      //  wait(100);
      //  UtilisateurManager userMng = new UtilisateurManager(getBaseContext(),this);

        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(intent);







    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
