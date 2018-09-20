package pico.covoitapp.Utils.Interface.Retrofit;

import java.util.ArrayList;

import pico.covoitapp.Model.Api.User;
import pico.covoitapp.Model.Api.UserInfo;
import pico.covoitapp.Model.Api.UserLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserChannel {
    public static final String ENDPOINT = "https://esgi-2017.herokuapp.com";


    @POST("/login")
    Call<String> connectUser(@Body UserLogin userLogin);


    @POST("/subscribe")
    Call<Void> createUser(@Body User user);

    @GET("/user")
    Call<User> getUserInfo(int id);



}