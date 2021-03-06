package pico.covoitapp.UI.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import pico.covoitapp.Utils.FireBaseHelper;
import pico.covoitapp.Utils.Interface.Retrofit.IUser;
import pico.covoitapp.Utils.RetrofitHelper;
import pico.covoitapp.R;
import pico.covoitapp.UI.DashboardActivity;
import pico.covoitapp.Utils.IImage;
import pico.covoitapp.Utils.ImageManager;

public class LoginSuccessActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        RetrofitHelper.getAllVoiture(null);

        ImageManager.getInstance().DownloadImage(RetrofitHelper.me.getProfil_image(), new IImage() {
                @Override
                public void onFirebaseResult(boolean okay) {
                  RetrofitHelper.getAssociation(RetrofitHelper.me.getId(), new IUser() {
                      @Override
                      public void onRetrofitResult(boolean okay) {
                          Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                          startActivity(intent);
                      }
                  });

                }
            });

    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
