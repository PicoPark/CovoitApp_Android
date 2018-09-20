package pico.covoitapp.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pico.covoitapp.DataLayer.RetrofitHelper;
import pico.covoitapp.R;
import retrofit2.Retrofit;

public class DashboardActivity extends AppCompatActivity {

    @BindView(R.id.dashboard_tv_mail)
    TextView tvMail;
    @BindView(R.id.dashboard_tv_phone)
    TextView tvPhone;
    @BindView(R.id.dashboard_tv_prenom)
    TextView tvPrenom;
    @BindView(R.id.dashboard_tv_nom)
    TextView tvNom;
    @BindView(R.id.dashboard_tv_voiture)
    TextView tvVoiture;

    @BindView(R.id.dashboard_btn_add)
    Button btnAdd;
    @BindView(R.id.dashboard_btn_reservation)
    Button btnReservation;
    @BindView(R.id.dashboard_notif)
    ImageView imgNotif;
    @BindView(R.id.dashboard_btn_message)
    Button btnMessage;
    @BindView(R.id.dashboard_btn_search)
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ButterKnife.bind(this);

        //Todo : set value

        tvMail.setText(RetrofitHelper.mUser.getEmail());
        tvPhone.setText(RetrofitHelper.mUser.getPhone());
        tvPrenom.setText(RetrofitHelper.mUser.getFirstname());
        tvNom.setText(RetrofitHelper.mUser.getLastname());
        tvVoiture.setText(RetrofitHelper.mUser.getVoiture());

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
                Intent intent = new Intent(v.getContext(), MessageActivity.class);
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

    }
}
