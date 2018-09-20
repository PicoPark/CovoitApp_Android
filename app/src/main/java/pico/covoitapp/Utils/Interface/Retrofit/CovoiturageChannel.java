package pico.covoitapp.Utils.Interface.Retrofit;

import java.util.List;

import pico.covoitapp.Model.Api.Covoiturage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CovoiturageChannel {

    @GET("/covoiturages")
    Call<List<Covoiturage>> getCovoiturages(@Query("covoiturage") String depart, String arrrive, String date);

    @GET("/covoiturage")
    Call<Covoiturage> getCovoiturage(@Query("covoiturage") int id);

}
