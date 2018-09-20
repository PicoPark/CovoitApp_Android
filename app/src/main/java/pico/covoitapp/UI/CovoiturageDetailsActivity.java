package pico.covoitapp.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import pico.covoitapp.DataLayer.RetrofitHelper;
import pico.covoitapp.Model.Api.Covoiturage;
import pico.covoitapp.R;

public class CovoiturageDetailsActivity extends AppCompatActivity {


    Button reserve;


    public static final String EXTRA_COVOIT = "covoiturage";
    private Covoiturage mCovoiturage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covoiturage_details);

        ButterKnife.bind(this);

        Bundle bundle = this.getIntent().getExtras();

        if(bundle!= null){
            setData(bundle.getInt(EXTRA_COVOIT));
        }else{
            finish();
        }

        reserve.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO : create reservation
            }
        });
    }

    private void setData(int i) {
        mCovoiturage = RetrofitHelper.mListCovoiturages.get(i);
    }
}
