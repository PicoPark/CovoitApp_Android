package pico.covoitapp.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pico.covoitapp.Model.Api.Covoiturage;
import pico.covoitapp.R;

public abstract class Adapter<T> extends BaseAdapter {

    protected List<T> mData;
    protected Context ctx;
    protected TextView tvCardText = null;


    public Adapter(List<T> mData, Context ctx) {
        this.mData = mData;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int i) {
        if( i >= mData.size( ) ) {
            return null;
        }
        return mData.get( i );
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // TODO

    public abstract View getView(int i, View view, ViewGroup viewGroup);
}
