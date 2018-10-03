package pico.covoitapp.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pico.covoitapp.Utils.RetrofitHelper;
import pico.covoitapp.Model.Api.MVehicule;
import pico.covoitapp.R;
import pico.covoitapp.Utils.Interface.Retrofit.IUser;

public class EditUtilisateurActivity extends AppCompatActivity {

    @BindView(R.id.edit_spinner)
    Spinner spinner;

    @BindView(R.id.edit_bt_go)
    Button bt_go;

    @BindView(R.id.edit_image)
    ImageView img;

    @BindView(R.id.edit_edit_email)
    EditText edit_email;
    @BindView(R.id.edit_edit_password)
    EditText edit_password;
    @BindView(R.id.edit_edit_firstname)
    EditText edit_firstname;
    @BindView(R.id.edit_edit_lastname)
    EditText edit_lastname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_utilisateur);
        ButterKnife.bind(this);

        edit_email.setText(RetrofitHelper.me.getMail());
        edit_firstname.setText(RetrofitHelper.me.getPrenom());
        edit_lastname.setText(RetrofitHelper.me.getNom());


        RetrofitHelper.getAllVoiture(new IUser() {
            @Override
            public void onRetrofitResult(boolean okay) {
                if (okay) {
                    List<String> array = new ArrayList<String>();
                    for (MVehicule item : RetrofitHelper.mListVehicule) {
                        array.add(item.getMarque() + " " + item.getModel());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            getApplicationContext(),
                            R.layout.support_simple_spinner_dropdown_item,
                            array
                    );
                    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                }
            }
        });



    }

    @OnClick(R.id.edit_bt_go)
    public void onClickGo(){
        RetrofitHelper.me.setMail(edit_email.getText().toString());
        RetrofitHelper.me.setNom(edit_lastname.getText().toString());
        RetrofitHelper.me.setPrenom(edit_firstname.getText().toString());
        RetrofitHelper.me.setPassword(edit_password.getText().toString());

        //create association

        RetrofitHelper.addUser(RetrofitHelper.me, new IUser() {
            @Override
            public void onRetrofitResult(boolean okay) {
                if(okay)
                    finish();
            }
        });




    }


}
