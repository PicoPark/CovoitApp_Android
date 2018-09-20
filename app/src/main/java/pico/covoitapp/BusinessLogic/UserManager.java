package pico.covoitapp.BusinessLogic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Explode;
import android.widget.Toast;

import java.util.ArrayList;


import io.realm.Realm;
import pico.covoitapp.DataLayer.Api.UserApi;
import pico.covoitapp.DataLayer.Realm.UserController;
import pico.covoitapp.Model.Api.User;
import pico.covoitapp.Model.Api.UserInfo;
import pico.covoitapp.Model.Api.UserLogin;
import pico.covoitapp.Model.Realm.UserInfoRealm;
import pico.covoitapp.Model.Realm.UserRealm;
import pico.covoitapp.R;
import pico.covoitapp.UI.Login.LoginActivity;
import pico.covoitapp.UI.Login.LoginSuccessActivity;
import pico.covoitapp.UI.MenuActivity;
import pico.covoitapp.Utils.ClientRetrofit;
import pico.covoitapp.Utils.Interface.IServiceResultListener;
import pico.covoitapp.Utils.Interface.Retrofit.UserChannel;
import pico.covoitapp.Utils.ServiceResult;
import retrofit2.Retrofit;

public class UserManager {


    Context context;
    Activity activityReference;

    Realm realm;
    UserChannel userInterface;
    UserApi userApi = new UserApi();


    public UserManager(Context context, Activity activityReference) {
        this.context = context;
        this.activityReference = activityReference;
        realm = UserController.with(activityReference).getRealm();
        Retrofit retrofit = ClientRetrofit.getClient();
        userInterface = retrofit.create(UserChannel.class);

    }

    private void createOrUpdateUserInfoRealm(ArrayList<UserInfo> userInfos) {

        for (int i = 0; i < userInfos.size(); i++) {

            UserInfoRealm userInfoRealm = new UserInfoRealm(userInfos.get(i).getEmail(), userInfos.get(i).get_id(), userInfos.get(i).getFirstname(), userInfos.get(i).getLastname());
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(userInfoRealm);
            realm.commitTransaction();
        }
       /* if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            goToMainAnimation();
        } else {*/
        //}
    }

    /*
    Permet de se déconnecter
     */
    public void logout() {
        final UserRealm userRealm = UserController.getInstance().getUserConnected(true);

        if (!(userRealm == null)) {


            realm.executeTransaction(new Realm.Transaction() {
                public void execute(Realm realm) {
                    userRealm.setConnected(false);
                    userRealm.setKeepConnection(false);
                    realm.copyToRealm(userRealm); // could be copyToRealmOrUpdate
                    goToLogin();

                }

            });

        }

    }

    /*
    Récupération de l'user connecté
     */
    public UserRealm getUserConnected() {
        return UserController.getInstance().getUserConnected(true);
    }


    /*
    Vérifie si un user a choisi de rester connecté (keepConnection), si oui alors go to mai,
     */



    /*
    Récupere les info de l'user connecté pour les mettre à jour dans realm
     */
    public void getInfo(final UserRealm userRealm) {

        //progressDialog = ProgressDialog.getProgress(context.getString(R.string.titreAttente), context.getString(R.string.textAttenteLogin), context);





        userApi.getInfo(userRealm, new IServiceResultListener<UserInfo>() {
            @Override
            public void onResult(final ServiceResult<UserInfo> result) {


                if (result.getmError() == null) {
                    realm.executeTransaction(new Realm.Transaction() {
                        public void execute(Realm realm) {
                            userRealm.setFirstName(result.getmData().getFirstname());
                            userRealm.setLastName(result.getmData().getLastname());
                            realm.copyToRealmOrUpdate(userRealm);

                        }

                    });
                }
            }
        });

    }


    /*
    Connecte un user
     */
    public void connect(final UserLogin userLogin, final boolean stayConneted) {

       // progressDialog = ProgressDialog.getProgress(context.getString(R.string.titreAttente), context.getString(R.string.textAttenteLogin), context);



        userApi.connect(userLogin, new IServiceResultListener<String>() {
            @Override
            public void onResult(final ServiceResult<String> result) {


                if (result.getmError() == null) {
                    final boolean connected = stayConneted;


                    //on verifie si il y a des user qui se sont déja connecté avec ce device
                    // si jamais, on en creer un avec id = 1
                    //sinon on verifie si il existe pas déja pour update le token et mettre isConnected a true sinon on en creer un  nouveau avec id = total + 1

                    realm.executeTransaction(new Realm.Transaction() { // must be in transaction for this to work
                        @Override
                        public void execute(Realm realm) {
                            // increment index
                            Number currentIdNum = realm.where(UserRealm.class).count();
                            int nextId;
                            if (currentIdNum == null) {
                                nextId = 1;
                                UserRealm userRealm = new UserRealm(nextId, userLogin.getEmail(), userLogin.getPassword(), result.getmData(), "", "", true, connected); // unmanaged
                                userRealm.setId(nextId);
                                realm.copyToRealm(userRealm); // using insert API

                                getInfo(userRealm);
                            } else {
                                UserRealm userR = UserController.getInstance().getUser(userLogin.getEmail());

                                if (userR == null) {
                                    nextId = currentIdNum.intValue() + 1;
                                    UserRealm userRealm = new UserRealm(nextId, userLogin.getEmail(), userLogin.getPassword(), result.getmData(), "", "", true, connected); // unmanaged
                                    userRealm.setId(nextId);
                                    realm.copyToRealm(userRealm); // using insert API
                                    getInfo(userRealm);

                                } else {
                                    userR.setConnected(true);
                                    userR.setKeepConnection(connected);
                                    userR.setToken(result.getmData());
                                    realm.copyToRealm(userR);

                                }
                            }

                        }
                    });

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                        goToLoginSuccesAnimation();
                    } else {
                        goToLoginSucces();
                    }
                } else {

                    //gestion error


                    if(result.getmError().getCode()==0){
                        Toast.makeText(context, context.getString(R.string.msgErrorNetwork), Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(context, context.getString(R.string.msgErrorLogin), Toast.LENGTH_SHORT).show();

                    }
//todo: à enlever
                    //bouchon

                    UserRealm userR = new UserRealm();

                    userR.setConnected(true);
                    userR.setKeepConnection(true);
                    userR.setToken("token");
                    //realm.copyToRealm(userR);
                    goToLoginSucces();


                }
            }
        });


    }

    /*
    Inscrit un user
    */
    public void register(final User user) {




        userApi.create(user, new IServiceResultListener<String>() {
            @Override
            public void onResult(final ServiceResult<String> result) {


                if (result.getmError() == null) {
                    Realm realm = UserController.with(activityReference).getRealm();


                    //on verifie si il y a des user qui se sont déja connecté avec ce device
                    // si jamais, on en creer un avec id = 1
                    //ou si Il n'existe pas dans la BDD local on en creer un  nouveau avec id = total + 1

                    realm.executeTransaction(new Realm.Transaction() { // must be in transaction for this to work
                        @Override
                        public void execute(Realm realm) {
                            // increment index
                            Number currentIdNum = realm.where(UserRealm.class).count();
                            int nextId;
                            if (currentIdNum == null) {
                                nextId = 1;
                                UserRealm userRealm = new UserRealm(nextId, user.getEmail(), user.getPassword(), result.getmData(), user.getFirstname(), user.getLastname(), false, false); // unmanaged
                                userRealm.setId(nextId);
                                realm.copyToRealm(userRealm); // using insert API
                            } else {
                                UserRealm userR = UserController.getInstance().getUser(user.getEmail());

                                if (userR == null) {
                                    nextId = currentIdNum.intValue() + 1;
                                    UserRealm userRealm = new UserRealm(nextId, user.getEmail(), user.getPassword(), result.getmData(), user.getFirstname(), user.getLastname(), false, false); // unmanaged
                                    userRealm.setId(nextId);
                                    realm.copyToRealm(userRealm); // using insert API
                                }
                            }

                        }
                    });

                    Toast.makeText(context, context.getString(R.string.textInscriptionReussi), Toast.LENGTH_SHORT).show();
                } else {

                    if(result.getmError().getCode()==0){
                        Toast.makeText(context, context.getString(R.string.msgErrorNetwork), Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(context, context.getString(R.string.textInscriptionError), Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });


    }




    private void goToLogin() {

        Intent goToLogin = new Intent(context, LoginActivity.class);
        context.startActivity(goToLogin);

    }

    private void goToLoginSucces() {

        Intent goToMain = new Intent(context, LoginSuccessActivity.class);
        context.startActivity(goToMain);
        activityReference.finish();

    }


    private void goToLoginSuccesAnimation() {


        Explode explode = new Explode();

        activityReference.getWindow().setExitTransition(explode);
        activityReference.getWindow().setEnterTransition(explode);
        ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(activityReference);
        Intent i2 = new Intent(activityReference.getApplicationContext(), LoginSuccessActivity.class);
        activityReference.startActivity(i2, oc2.toBundle());
        explode.setDuration(500);

    }

    private void goToMain() {

        Intent goToMain = new Intent(context, MenuActivity.class);
        activityReference.startActivity(goToMain);
        activityReference.finish();


    }


    private void goToMainAnimation() {


        Explode explode = new Explode();

        activityReference.getWindow().setExitTransition(explode);
        activityReference.getWindow().setEnterTransition(explode);
        ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(activityReference);
        Intent i2 = new Intent(activityReference.getApplicationContext(), MenuActivity.class);
        activityReference.startActivity(i2, oc2.toBundle());
        explode.setDuration(500);


        //activityReference.finish();


    }

    public void isConnected() {

        UserRealm userRealm = UserController.getInstance().getUserConnected(true);

        if (!(userRealm == null)) {

            if (userRealm.isKeepConnection()) {
                UserManager manageUser = new UserManager(context, activityReference);
                if (userRealm.getFirstName().equals("")) {
                    manageUser.getInfo(userRealm);
                }

                goToLoginSucces();
            }

        }


    }


}
