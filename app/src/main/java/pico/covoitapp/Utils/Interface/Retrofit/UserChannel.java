package pico.covoitapp.Utils.Interface.Retrofit;

import java.util.ArrayList;

import pico.covoitapp.Model.Api.MUtilisateur;
import pico.covoitapp.Model.Api.UserInfo;
import pico.covoitapp.Model.Api.UserLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserChannel {



    @POST("Connexion")
    Call<MUtilisateur> connectUser(@Body MUtilisateur userLogin);


    @POST("Utilisateur")
    Call<MUtilisateur> createUser(@Body MUtilisateur user);

    @GET("Utilisateur/{id}")
    Call<MUtilisateur> getUserInfo(@Path("id") String id);

    @GET("Verification/{email}")
    Call<Boolean> verification(@Query("email")String email);



}