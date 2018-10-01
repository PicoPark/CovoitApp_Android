package pico.covoitapp.Utils.Interface.Retrofit;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.util.List;

import pico.covoitapp.Model.Api.Reservation;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ReservationChannel {

    @GET("Reservation/{id}")
    Call<List<Reservation>> getReservationByConducteur(@Path("id")String id);

    @GET("Reservation/Utilisateur/{id}")
    Call<List<Reservation>> getReservationByPassager(@Path("id")String id);

    @POST("Reservation")
   Call<Reservation> postReservation(@Body Reservation reservation);




}
