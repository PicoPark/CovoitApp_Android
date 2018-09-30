package pico.covoitapp.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import pico.covoitapp.DataLayer.RetrofitHelper;
import pico.covoitapp.R;
import pico.covoitapp.UI.Adapter.ReservationAdapter;
import pico.covoitapp.Utils.Interface.Retrofit.IReservation;

public class ReservationActivity extends AppCompatActivity {

    private static final String TAG = "covoitApp.Reservation";
    @BindView(R.id.reservation_lv_reservation)
    ListView lvReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        ButterKnife.bind(this);

        RetrofitHelper.getReservationFromConducteur(RetrofitHelper.me.getId(), new IReservation() {
            @Override
            public void onRetrofitResult(boolean okay) {
                if(okay) {
                    if (lvReservation != null) {
                        BaseAdapter sa = new ReservationAdapter(RetrofitHelper.mListReservations, getApplicationContext());
                        lvReservation.setAdapter(sa);
                        sa.notifyDataSetChanged();
                    }else{
                        Toast.makeText(getApplicationContext(),"Erreur", Toast.LENGTH_LONG).show();
                        Log.e(TAG, "List null");
                    }
                }else{
                    Log.e(TAG, "Retrofit Failed");
                }
            }
        });

    }
}
