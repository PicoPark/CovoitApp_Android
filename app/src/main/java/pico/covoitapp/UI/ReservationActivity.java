package pico.covoitapp.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pico.covoitapp.Utils.RetrofitHelper;
import pico.covoitapp.Model.Api.Reservation;
import pico.covoitapp.R;
import pico.covoitapp.UI.Adapter.ReservationAdapter;

public class ReservationActivity extends AppCompatActivity {

    private static final String TAG = "covoitApp.Reservation";
    @BindView(R.id.reservation_lv_reservation)
    ListView lvReservation;
    @BindView(R.id.reservation_tv_empty)
    TextView tvEmpty;

    List<Reservation> listReservation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        ButterKnife.bind(this);
        Log.e("CovoitApp.Dashboard", "size : " + RetrofitHelper.mListReservationsConducteur.size() + " " +RetrofitHelper.mListReservationsPassager.size());
        listReservation = new ArrayList<Reservation>();
        listReservation.addAll(RetrofitHelper.mListReservationsConducteur);
        listReservation.addAll(RetrofitHelper.mListReservationsPassager);

        if(listReservation.size() == 0)
            tvEmpty.setVisibility(View.VISIBLE);
        else
            tvEmpty.setVisibility(View.INVISIBLE);



        if (lvReservation != null) {
            BaseAdapter sa = new ReservationAdapter(listReservation, getApplicationContext());
            lvReservation.setAdapter(sa);
            sa.notifyDataSetChanged();
        }else{
            Toast.makeText(getApplicationContext(),"Erreur", Toast.LENGTH_LONG).show();
            Log.e(TAG, "List null");

        }


    }

}
