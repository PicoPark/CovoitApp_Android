package pico.covoitapp.UI;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pico.covoitapp.DataLayer.Api.CovoiturageAPI;
import pico.covoitapp.DataLayer.RetrofitHelper;
import pico.covoitapp.Model.Api.Covoiturage;
import pico.covoitapp.R;
import pico.covoitapp.UI.Adapter.CovoiturageAdapter;
import pico.covoitapp.UI.Fragment.DatePickerFragment;
import pico.covoitapp.Utils.Interface.Realm.ICovoiturage;

public class SearchActivity extends AppCompatActivity implements ICovoiturage{


    private final String TAG = "Covoit.Search";

    @BindView(R.id.search_et_depart)
    TextInputEditText edDepart;
    @BindView(R.id.search_et_arrive)
    TextInputEditText edArrive;
    @BindView(R.id.dashboard_et_date)
    EditText edDate;

    @BindView(R.id.search_btn)
    Button searchButton;
    @BindView(R.id.search_lv_covoiturage)
    ListView lvCovoiturage;

    private List<Covoiturage> covoiturageList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        final SearchActivity ctx = this;

        edDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                  DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }

        });

        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int day = dp.getDayOfMonth();
                int month = dp.getMonth() + 1;
                int year = dp.getYear();
                String date = day + "/"+ month+"/"+year;
                RetrofitHelper.getAllCovoiturages(edDepart.getText().toString(),
                        edArrive.getText().toString(), date,
                        ctx );
            }

        });

        lvCovoiturage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Covoiturage covoit = RetrofitHelper.mListCovoiturages.get(i);
                if(covoit != null){
                    Intent intent = new Intent(SearchActivity.this, CovoiturageDetailsActivity.class );
                    intent.putExtra(CovoiturageDetailsActivity.EXTRA_COVOIT, i);
                    startActivity(intent);
                }
            }


        });

    }

    @Override
    public void onRetrofitResult(boolean okay) {
        if(okay){
            if(lvCovoiturage != null){
                List<Covoiturage> lisCovoit = new ArrayList<>();

                for(Covoiturage data : RetrofitHelper.mListCovoiturages){
                    lisCovoit.add(data);
                }

                BaseAdapter sa = new CovoiturageAdapter(lisCovoit, this);

            }else{
                Toast.makeText(this,"Erreur", Toast.LENGTH_LONG);
                Log.e(TAG, "List null");
            }
        }else{
            Log.e(TAG, "Retrofit Failed");
        }

    }
}
