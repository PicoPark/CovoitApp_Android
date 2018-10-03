package pico.covoitapp.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pico.covoitapp.Utils.RetrofitHelper;
import pico.covoitapp.R;
import pico.covoitapp.Utils.ImageManager;
import pico.covoitapp.Utils.Interface.Retrofit.IReservation;

public class DashboardActivity extends AppCompatActivity implements IReservation{

    @BindView(R.id.dashboard_image)
    ImageView imgProfil;

    @BindView(R.id.dashboard_tv_mail)
    TextView tvMail;
    @BindView(R.id.dashboard_tv_phone)
    TextView tvPhone;
    @BindView(R.id.dashboard_tv_prenom)
    TextView tvPrenom;

    @BindView(R.id.dashboard_tv_voiture)
    TextView tvVoiture;

    @BindView(R.id.dashboard_btn_edit)
    Button btngo;

    @BindView(R.id.dashboard_btn_add)
    ImageButton btnAdd;
    @BindView(R.id.dashboard_btn_reservation)
    ImageButton btnReservation;
    @BindView(R.id.dashboard_notif)
    ImageView imgNotif;
    @BindView(R.id.dashboard_btn_message)
    ImageButton btnMessage;
    @BindView(R.id.dashboard_btn_search)
    ImageButton btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ButterKnife.bind(this);
        imgNotif.setVisibility(View.INVISIBLE);

        //Todo : set value

        btnMessage.setEnabled(false);
        imgProfil.setImageBitmap(ImageManager.getInstance().getImage());
        tvMail.setText(RetrofitHelper.me.getMail());
        // tvPhone.setText(RetrofitHelper.mUser.getPhone());
        tvPrenom.setText(RetrofitHelper.me.getPrenom() +" "+ RetrofitHelper.me.getNom());
        // tvVoiture.setText(RetrofitHelper.me.getVoiture());

        RetrofitHelper.getReservationFromConducteur(RetrofitHelper.me.getId(), this);
        RetrofitHelper.getReservationFromPassager(RetrofitHelper.me.getId(), this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReservationActivity.class);
                v.getContext().startActivity(intent);

            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MessageActivity.class);
                v.getContext().startActivity(intent);

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), SearchActivity.class);
                v.getContext().startActivity(intent);

            }
        });

        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CovoitApp.Dashboard", "Click on Edit");
                Intent intent = new Intent(v.getContext(), EditUtilisateurActivity.class);
                startActivity(intent);
            }
        });

    }

    private void refreshScreen(){

        tvMail.setText(RetrofitHelper.me.getMail());
        // tvPhone.setText(RetrofitHelper.mUser.getPhone());
        tvPrenom.setText(RetrofitHelper.me.getPrenom()+" "+RetrofitHelper.me.getNom());
        imgProfil.setImageBitmap(ImageManager.getInstance().getImage());
        // tvVoiture.setText(RetrofitHelper.me.getVoiture());

        RetrofitHelper.getReservationFromConducteur(RetrofitHelper.me.getId(), this);
        RetrofitHelper.getReservationFromPassager(RetrofitHelper.me.getId(), this);
    }

    private void NotifyReservation() {
        Log.e("CovoitApp.Dashboard", "size : " + RetrofitHelper.mListReservationsConducteur.size() + " " + RetrofitHelper.mListReservationsPassager.size());
        if (RetrofitHelper.mListReservationsConducteur.size() != 0 ||
                RetrofitHelper.mListReservationsPassager.size() != 0) {
            Log.e("CovoitApp.Dashboard", "set image visible");
            imgNotif.setVisibility(View.VISIBLE);
        }else
            imgNotif.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        refreshScreen();
        NotifyReservation();

    }



    @Override
    public void onRetrofitResult(boolean okay) {
        NotifyReservation();
    }

}
