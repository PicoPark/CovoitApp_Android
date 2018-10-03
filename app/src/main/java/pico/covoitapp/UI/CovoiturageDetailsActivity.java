package pico.covoitapp.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pico.covoitapp.Utils.RetrofitHelper;

import pico.covoitapp.Model.Api.MCovoiturage;
import pico.covoitapp.Model.Api.Reservation;
import pico.covoitapp.R;
import pico.covoitapp.Utils.Interface.Retrofit.IReservation;
import pico.covoitapp.Utils.Tools;

public class CovoiturageDetailsActivity extends AppCompatActivity {

    private static final String TAG = "covoitApp.covoitDetail";

    @BindView(R.id.covoit_details_bt_go)
    Button reserve;

    @BindView(R.id.covoit_details_bt_return)
    Button btnReturn;

    @BindView(R.id.detail_covoit_name)
    TextView tv_name;
    @BindView(R.id.detail_covoit_date)
    TextView tv_date;
    @BindView(R.id.detail_covoit_tarif)
    TextView tv_tarif;
    @BindView(R.id.detail_covoit_trajet)
    TextView tv_trajet;


    public static final String EXTRA_COVOIT = "covoiturage";
    public static final String FROM_ADD = "add";

    private boolean isFromAdd;
    private MCovoiturage mCovoiturage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covoiturage_details);

        ButterKnife.bind(this);


        Bundle bundle = this.getIntent().getExtras();

        if(bundle != null){
            isFromAdd = bundle.getBoolean(FROM_ADD);
            setData( bundle.getSerializable(EXTRA_COVOIT));
            if(isFromAdd)
                reserve.setVisibility(View.INVISIBLE);
        }else
            finish();


        reserve.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Reservation resa = new Reservation(0,false,mCovoiturage,
                        RetrofitHelper.me);
                RetrofitHelper.saveReservation(resa, new IReservation() {
                    @Override
                    public void onRetrofitResult(boolean okay) {
                        if(okay){
                            Toast.makeText(getApplicationContext(),"La demande de reservation a bien été faite, attendez la réponse du conducteur", Toast.LENGTH_LONG);

                        }else{
                            Toast.makeText(getApplicationContext(),"l y a eu une erreur lors de l'enregistrement",Toast.LENGTH_LONG);
                        }
                    }
                });

            }
        });
    }

    @OnClick(R.id.covoit_details_bt_return)
    public void onClickReturn(){
        finish();
    }

    private void setData(Serializable covoit ) {
        mCovoiturage = (MCovoiturage) covoit;
        if(isFromAdd){
            tv_name.setText(RetrofitHelper.me.getPrenom() + " " + RetrofitHelper.me.getNom());
        }else{
            tv_name.setText(RetrofitHelper.mUser.getPrenom() + " " + RetrofitHelper.mUser.getNom());
        }
        tv_trajet.setText(mCovoiturage.getDepart() +" - "+ mCovoiturage.getArrive());
        tv_date.setText(Tools.showDateHeure(mCovoiturage));
        tv_tarif.setText(""+mCovoiturage.getTarif() + " €");

    }
}
