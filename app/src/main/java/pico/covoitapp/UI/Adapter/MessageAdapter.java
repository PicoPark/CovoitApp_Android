package pico.covoitapp.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pico.covoitapp.Model.Api.Message;
import pico.covoitapp.R;

public class MessageAdapter extends Adapter<Message> {


    public MessageAdapter(List<Message> mData, Context ctx) {
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
