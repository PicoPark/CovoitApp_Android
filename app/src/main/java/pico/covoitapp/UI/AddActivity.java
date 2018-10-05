package pico.covoitapp.UI;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pico.covoitapp.Utils.RetrofitHelper;
import pico.covoitapp.Model.Api.MCovoiturage;
import pico.covoitapp.R;
import pico.covoitapp.Utils.Interface.Retrofit.ICovoiturage;
import pico.covoitapp.Utils.Tools;

public class AddActivity extends AppCompatActivity {

    @BindView(R.id.add_edit_ville_depart)
    EditText edDepart;
    @BindView(R.id.add_edit_ville_arrive)
    EditText edArrive;
    @BindView(R.id.add_edit_date_depart)
    EditText edDate;
    @BindView(R.id.add_edit_heure_depart)
    EditText edheure;
    @BindView(R.id.add_edit_nb_place)
    EditText edNbPlace;
    @BindView(R.id.add_edit_tarif)
    EditText edTarif;

    @BindView(R.id.add_bt_save)
    Button btnSave;
    private int mYear, mMonth, mDay, mHour, mMinute;
;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_bt_save)
    public void onClickSave(){
        final MCovoiturage covoit = new MCovoiturage();
        covoit.setDepart(edDepart.getText().toString());
        covoit.setArrive(edArrive.getText().toString());
        //covoit.setDate_depart(edDate.getText().toString().replace("/", "-")); //+ " " + edheure.getText().toString());

        covoit.setAnnee(mYear);
        covoit.setMois(mMonth);
        covoit.setJours(mDay);
        covoit.setMinutes(mMinute);
        covoit.setHeure(mHour);

        covoit.setNb_place(Integer.parseInt(edNbPlace.getText().toString()));
        covoit.setTarif(Integer.parseInt(edTarif.getText().toString()));
        covoit.setDate_creation(Tools.simpleDate.format(new Date()));
        covoit.setId_utilisateur(RetrofitHelper.me.getId());

        RetrofitHelper.saveCovoiturage(covoit, new ICovoiturage() {
            @Override
            public void onRetrofitResult(boolean okay) {
                if (okay){
                    goToDetailsActivity(RetrofitHelper.mCovoiturage);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Erreur", Toast.LENGTH_LONG);
                }
            }
        });
    }





    public void goToDetailsActivity(final MCovoiturage covoit){
        Intent intent = new Intent(AddActivity.this, CovoiturageDetailsActivity.class);
        intent.putExtra(CovoiturageDetailsActivity.EXTRA_COVOIT, covoit);
        intent.putExtra(CovoiturageDetailsActivity.FROM_ADD, true);
        //look how put object
        startActivity(intent);
    }

    @OnClick (R.id.add_edit_date_depart)
    public void onClickDate(){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH) ;
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

    @OnClick (R.id.add_edit_heure_depart)
    public void onClickHeure(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        String tmpHour = String.valueOf(hourOfDay);
                        String tmpMin = String.valueOf(minute);
                        mHour = hourOfDay;
                        mMinute = minute;
                     if (minute < 10){
                         tmpMin = "0"+tmpMin;
                     }
                     if( hourOfDay<10){
                         tmpHour = "0"+tmpHour;
                     }
                        edheure.setText(tmpHour + ":" + tmpMin);
                    }
                }, mHour, mMinute, true);

        timePickerDialog.show();

    }


}
