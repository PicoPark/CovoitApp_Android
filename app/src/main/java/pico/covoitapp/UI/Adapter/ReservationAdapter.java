package pico.covoitapp.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import pico.covoitapp.Model.Api.Reservation;
import pico.covoitapp.R;

public class ReservationAdapter extends Adapter<Reservation> {

    public ReservationAdapter(List<Reservation> mData, Context ctx) {
        super(mData, ctx);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.adapter_list_covoiturage, viewGroup, false);
        }

        // tvCardText = (TextView) view.findViewById( R.id.main_lv_text );

        if( tvCardText != null ) {
            //   tvCardText.setText( getItem(i) );
        }
    return view;
    }

}
