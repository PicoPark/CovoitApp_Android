package pico.covoitapp.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pico.covoitapp.DataLayer.RetrofitHelper;
import pico.covoitapp.Model.Api.Reservation;
import pico.covoitapp.R;
import pico.covoitapp.Utils.Interface.Realm.IUser;
import pico.covoitapp.Utils.Interface.Realm.IUserInterface;
import pico.covoitapp.Utils.Interface.Realm.IUserI;

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
        if (view == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.adapter_list_reservation, viewGroup, false);
            ButterKnife.bind(this,view);
        }

        reservation = getItem(i);
        RetrofitHelper.getUserInfo(reservation.getId_utilisateur(),this);




    return view;
    }

    @Override
    public void onRetrofitResult(boolean okay) {



    }
}
