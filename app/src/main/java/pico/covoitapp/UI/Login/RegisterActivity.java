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
import com.victor.loading.rotate.RotateLoading;

import butterknife.BindView;
import butterknife.ButterKnife;
import pico.covoitapp.Utils.IImage;
import pico.covoitapp.Utils.RetrofitHelper;
import pico.covoitapp.Model.Api.MUtilisateur;
import pico.covoitapp.R;
import pico.covoitapp.UI.DashboardActivity;
import pico.covoitapp.Utils.ImageManager;
import pico.covoitapp.Utils.Interface.Retrofit.IUser;


public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_rotateloading)
    RotateLoading loader;

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


    private boolean isPhotoTaked = false;
    private final String TAG = "covoitApp.Register";
    private StorageReference mStorageRef;
    private static final int CAMERA_REQUEST = 1888;

    private Bitmap photo;

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
        Log.e(TAG, "click");
        Log.e(TAG,"isPhotoTaked " + isPhotoTaked);
        if(!isPhotoTaked){


            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
            Toast.makeText(getApplicationContext(),"Nous avons besoin d'une photo de profil",Toast.LENGTH_SHORT).show();

        }else{
            loader.start();
            Log.e(TAG,"Verification");
            RetrofitHelper.verification(edit_email.getText().toString(), new IUser() {
                @Override
                public void onRetrofitResult(boolean okay) {
                    Log.e(TAG,"verif ok " +okay);
                    if (okay && ! RetrofitHelper.mailExist) {

                        MUtilisateur me = new MUtilisateur();

                      me = new MUtilisateur();
                      me.setMail(edit_email.getText().toString());
                      me.setPrenom(edit_firstname.getText().toString());
                      me.setNom(edit_lastname.getText().toString());
                      me.setPassword(edit_password.getText().toString());
                      me.setProfil_image(edit_email.getText().toString() + ".jpg");
                      me.setNumero(edit_numero.getText().toString());
                     final MUtilisateur tmp = me;
                        RetrofitHelper.me = tmp;
                        Log.e(TAG, "User créer : " + me.getProfil_image());
                        ImageManager.getInstance().uploadImage(photo, RetrofitHelper.me.getMail() + ".jpg", new IImage() {
                            @Override
                            public void onFirebaseResult(boolean okay) {
                                if (okay){

                                    RetrofitHelper.addUser(tmp, new IUser() {
                                        @Override
                                        public void onRetrofitResult(boolean okay) {
                                            if(okay) {
                                                loader.stop();
                                                Log.e(TAG, "User image : " + RetrofitHelper.me.getProfil_image());
                                                Toast.makeText(getApplicationContext(), "Votre compte a bien été créer", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getBaseContext(), LoginSuccessActivity.class);
                                                startActivity(intent);
                                            }else
                                                Log.e(TAG, "erreur Retrofit");
                                            }
                                    });
                                }else
                                    Log.e(TAG, "erreur firebase");
                            }
                        });



                    } else {
                        Toast.makeText(getApplicationContext(), "un compte éxiste déjà avec l'adresse mail : " + edit_email.getText(), Toast.LENGTH_LONG).show();
                    }
                }
            });

            //user.setPhone(edit_numero.getText().toString());

          //  Uri.fromFile(new File( ))

        //


        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(photo);
            isPhotoTaked = true;

        }
    }

}
