package pico.covoitapp.UI.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pico.covoitapp.DataLayer.RetrofitHelper;
import pico.covoitapp.Model.Api.Reservation;
import pico.covoitapp.R;
import pico.covoitapp.Utils.Interface.Retrofit.IReservation;
import retrofit2.Retrofit;


public class ReservationAdapter extends Adapter<Reservation>  {

    @BindView(R.id.reservation_image)
    ImageView img;
    @BindView(R.id.reservation_name)
    TextView tvName;
    @BindView(R.id.reservation_trajet)
    TextView tvTrajet;
    @BindView(R.id.resrevation_btn_yes)
    Button btnYes;
    @BindView(R.id.resrevation_btn_no)
    Button btnNo;

    Reservation reservation;

    public ReservationAdapter(List<Reservation> mData, Context ctx) {
        super(mData, ctx);

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int j = i;
        if (view == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.adapter_list_reservation, viewGroup, false);
            ButterKnife.bind(this,view);
        }
        btnNo = view.findViewById(R.id.resrevation_btn_no);
        btnYes = view.findViewById(R.id.resrevation_btn_yes);

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reservation.setValidate(false);
                Log.e("CovoitApp.Button", "No");
                Toast.makeText(view.getContext(), "Nok", Toast.LENGTH_LONG).show();
                RetrofitHelper.saveReservation(reservation, new IReservation() {
                    @Override
                    public void onRetrofitResult(boolean okay) {
                        mData.remove(j);
                        notifyDataSetChanged();

                    }
                });
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reservation.setValidate(true);
                Log.e("CovoitApp.Button", "Yes");
                Toast.makeText(view.getContext(), "ok", Toast.LENGTH_LONG).show();
                RetrofitHelper.saveReservation(reservation, new IReservation() {
                    @Override
                    public void onRetrofitResult(boolean okay) {
                        mData.remove(j);
                        notifyDataSetChanged();

                    }
                });

            }
        });
        reservation = getItem(i);
        tvName.setText(reservation.getUtilisateur().getPrenom() +
                        " " +
                reservation.getUtilisateur().getNom());

        tvTrajet.setText(reservation.getCovoiturage().getDepart()+
                " " +
        reservation.getCovoiturage().getArrive());





    return view;
    }

}
