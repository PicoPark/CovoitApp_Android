package pico.covoitapp.UI.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pico.covoitapp.Utils.RetrofitHelper;

import pico.covoitapp.Model.Api.MCovoiturage;
import pico.covoitapp.R;
import pico.covoitapp.Utils.Tools;

public class CovoiturageAdapter extends Adapter<MCovoiturage> {

    private static final String TAG = "covoitApp.covoitAdap";
    @BindView(R.id.covoit_trajet)
    TextView tvTrajet;
    @BindView(R.id.covoit_tarif)
    TextView tvTarif ;
    @BindView(R.id.covoit_date)
    TextView tvDate ;

    public CovoiturageAdapter(List<MCovoiturage> mData, Context ctx) {
        super(mData, ctx);
    }

    // TODO
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.adapter_list_covoiturage, viewGroup, false);
        }
        ButterKnife.bind(this,view);

        MCovoiturage covoiturage = RetrofitHelper.mListCovoiturages.get(i);
        Log.e(TAG, "annee"  +covoiturage.getAnnee());
        Log.e(TAG, "mois"  +covoiturage.getMois());
        Log.e(TAG, "jours"  +covoiturage.getJours());
        Log.e(TAG, "heure"  +covoiturage.getHeure());
        Log.e(TAG, "minutes"  +covoiturage.getMinutes());


        tvTarif.setText(covoiturage.getTarif()+ "€");
        tvTrajet.setText(covoiturage.getDepart()+ " - " + covoiturage.getArrive());
        tvDate.setText(Tools.showDateHeure(covoiturage));



        return view;
    }
}
