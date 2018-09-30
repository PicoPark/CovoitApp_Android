package pico.covoitapp.Utils.Interface.Retrofit;

import java.util.List;

import pico.covoitapp.Model.Api.MCovoiturage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CovoiturageChannel {

    @GET("Covoiturage/{id}")
    Call<MCovoiturage> getCovoiturage(@Query("id") String id);

    @POST("ListCovoiturage")
    Call<List<MCovoiturage>> getListCovoiturage(@Body MCovoiturage covoit);

    @POST("Covoiturage")
    Call<MCovoiturage> postCovoiturage(@Body MCovoiturage covoit);
}
