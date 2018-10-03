package pico.covoitapp.UI.Login;


import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pico.covoitapp.BusinessLogic.UtilisateurManager;
import pico.covoitapp.Utils.FireBaseHelper;
import pico.covoitapp.Utils.RetrofitHelper;
import pico.covoitapp.Model.Api.MUtilisateur;
import pico.covoitapp.R;
import pico.covoitapp.Utils.Interface.Retrofit.IUser;

public class LoginActivity extends AppCompatActivity{

    @BindView(R.id.et_username)
    AutoCompleteTextView etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_go)
    Button btGo;
    @BindView(R.id.cv)
    CardView cv;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.checkboxStayConnected)
    CheckBox checkboxStayConnected;

    UtilisateurManager userMng;

    private final String TAG ="covoitApp.Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        userMng = new UtilisateurManager(this, this);

        ButterKnife.bind(this);
        askPermission();


    }

    protected void askPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        0);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @OnClick({R.id.bt_go, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(null);
                    getWindow().setEnterTransition(null);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }

                break;
            case R.id.bt_go:

                RetrofitHelper.connect( new MUtilisateur(etUsername.getText().toString(),etPassword.getText().toString()), new IUser(){
                    @Override
                    public void onRetrofitResult(boolean okay) {
                        if(okay){
                            goToLoginSuccesAnimation();
                        }else{
                            Toast.makeText(getApplicationContext(), "Echec de connexion", Toast.LENGTH_LONG).show();
                        }
                    }
                });


               // userMng.connect(new UserLogin(etUsername.getText().toString(),etPassword.getText().toString()),checkboxStayConnected.isChecked());
                break;
        }
    }

    private void goToLoginSuccesAnimation() {

        Intent intent = new Intent(getApplicationContext(), LoginSuccessActivity.class);
        startActivity(intent);
      //  Toast.makeText(getApplicationContext(),"connexion on" , )
      // Explode explode = new Explode();

      // getWindow().setExitTransition(explode);
      // getWindow().setEnterTransition(explode);
      // ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
      // Intent i2 = new Intent(getApplicationContext(), LoginSuccessActivity.class);
      // startActivity(i2, oc2.toBundle());
      // explode.setDuration(500);

    }



}
