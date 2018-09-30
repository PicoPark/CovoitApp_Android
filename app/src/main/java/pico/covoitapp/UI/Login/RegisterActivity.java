package pico.covoitapp.UI.Login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import pico.covoitapp.BusinessLogic.UtilisateurManager;
import pico.covoitapp.DataLayer.RetrofitHelper;
import pico.covoitapp.Model.Api.MUtilisateur;
import pico.covoitapp.R;
import pico.covoitapp.UI.DashboardActivity;
import pico.covoitapp.Utils.Interface.Retrofit.IUser;


public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.cv_add)
    CardView cvAdd;
    @BindView(R.id.bt_go)
    Button bt_go;

    @BindView(R.id.registery_image)
    ImageView img;

    @BindView(R.id.edit_email)
    EditText edit_email;
    @BindView(R.id.edit_password)
    EditText edit_password;
    @BindView(R.id.edit_firstname)
    EditText edit_firstname;
    @BindView(R.id.edit_lastname)
    EditText edit_lastname;
    @BindView(R.id.edit_numero)
    EditText edit_numero;


    private final String TAG = "covoitApp.Register";
    private StorageReference mStorageRef;
    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        img.setImageResource(R.drawable.user);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });

        bt_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUer();
            }
        });
    }


    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementEnterTransition(transition);
        }

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        Animator mAnimator = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
        }
        mAnimator.setDuration(200);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
        }
        mAnimator.setDuration(200);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @Override
    public void onBackPressed() {
        animateRevealClose();
    }
    private void addUer(){
        Log.e(TAG, "drawable activity : " + img.getDrawable());
        Log.e(TAG, "drawable context : " + getApplicationContext().getDrawable(R.drawable.user));

        if(img.getDrawable().equals( getApplicationContext().getDrawable(R.drawable.user))){
            Toast.makeText(getApplicationContext(),"Nous avons besoin d'une photo de profil",Toast.LENGTH_SHORT).show();

            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);

        }else{


            RetrofitHelper.verification(edit_email.getText().toString(), new IUser() {
                @Override
                public void onRetrofitResult(boolean okay) {
                    if (okay) {
                        MUtilisateur user = new MUtilisateur();
                        user.setMail(edit_email.getText().toString());
                        user.setPrenom(edit_firstname.getText().toString());
                        user.setNom(edit_lastname.getText().toString());
                        user.setPassword(edit_password.getText().toString());

                        RetrofitHelper.addUser(user, new IUser() {
                            @Override
                            public void onRetrofitResult(boolean okay) {
                                Toast.makeText(getApplicationContext(), "Votre compte a bien été créer", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getBaseContext(), DashboardActivity.class);
                                startActivity(intent);
                            }
                        });

                    } else {
                        Toast.makeText(getApplicationContext(), "un compte éxiste déjà avec l'adresse mail : " + edit_email.getText(), Toast.LENGTH_LONG).show();
                    }
                }
            });

            //user.setPhone(edit_numero.getText().toString());

          //  Uri.fromFile(new File( ))

        //    ImageManager.getInstance().uploadImage(img.get);


        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(photo);

        }
    }

}
