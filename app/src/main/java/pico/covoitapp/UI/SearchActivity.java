package pico.covoitapp.UI;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pico.covoitapp.Utils.RetrofitHelper;
import pico.covoitapp.Model.Api.MCovoiturage;
import pico.covoitapp.R;
import pico.covoitapp.UI.Adapter.CovoiturageAdapter;
import pico.covoitapp.Utils.Interface.Retrofit.ICovoiturage;
import pico.covoitapp.Utils.Interface.Retrofit.IUser;
import pico.covoitapp.Utils.Tools;

public class SearchActivity extends AppCompatActivity {


    private final String TAG = "Covoit.Search";

    @BindView(R.id.search_et_depart)
    TextInputEditText edDepart;
    @BindView(R.id.search_et_arrive)
    TextInputEditText edArrive;
    @BindView(R.id.dashboard_et_date)
    EditText edDate;
    @BindView(R.id.search_btn)
    ImageButton searchButton;
    @BindView(R.id.search_lv_covoiturage)
    ListView lvCovoiturage;

    private List<MCovoiturage> covoiturageList = null;
    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);




        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MCovoiturage covoit = new MCovoiturage();
                covoit.setDepart(edDepart.getText().toString());
                covoit.setArrive(edArrive.getText().toString());

                covoit.setAnnee(mYear);
                covoit.setMois(mMonth);
                covoit.setJours(mDay);

                RetrofitHelper.getAllCovoiturages(covoit,
                        new ICovoiturage() {
                            @Override
                            public void onRetrofitResult(boolean okay) {
                                if(okay){
                                    if(lvCovoiturage != null){
                                        BaseAdapter sa = new CovoiturageAdapter(RetrofitHelper.mListCovoiturages, getApplicationContext());
                                        lvCovoiturage.setAdapter(sa);
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

        });

        lvCovoiturage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final MCovoiturage covoit = RetrofitHelper.mListCovoiturages.get(i);
                RetrofitHelper.getUserInfo(covoit.getId_utilisateur(), new IUser() {
                    @Override
                    public void onRetrofitResult(boolean okay) {
                        Intent intent = new Intent(SearchActivity.this, CovoiturageDetailsActivity.class );
                        intent.putExtra(CovoiturageDetailsActivity.EXTRA_COVOIT, covoit);
                        intent.putExtra(CovoiturageDetailsActivity.FROM_ADD, false);
                        startActivity(intent);
                    }
                });


            }


        });

    }


    @OnClick(R.id.dashboard_et_date)
    public void onClickDate(){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        mYear = year;
                        mMonth = monthOfYear +1;
                        mDay = dayOfMonth;
                        edDate.setText(Tools.showDate(year, mMonth,dayOfMonth));

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
        datePickerDialog.show();

    }

}
