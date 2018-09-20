package pico.covoitapp.DataLayer;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pico.covoitapp.Model.Api.Covoiturage;
import pico.covoitapp.Model.Api.User;
import pico.covoitapp.Utils.Interface.Realm.ICovoiturage;
import pico.covoitapp.Utils.Interface.Realm.IUserInterface;
import pico.covoitapp.Utils.Interface.Retrofit.CovoiturageChannel;
import pico.covoitapp.Utils.Interface.Retrofit.UserChannel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {


    private static final String BASE_URL = "http://localhost:56263/Service1.svc/";

    private static Retrofit mRetrofit = null;
    private static CovoiturageChannel mCovoiturageChannel = null;
    private static UserChannel mUserChannel = null;

    public static List<Covoiturage> mListCovoiturages;
    public static User mUser;

    public static void init( ) {
        // Gson gson = new GsonBuilder( ).setDateFormat( "yyyy-MM-dd'T'HH:mm:ssZ" ).create( );

        mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl( BASE_URL ).build( );
        Log.e("retro", "build ok");
        mCovoiturageChannel = mRetrofit.create( CovoiturageChannel.class );
        mUserChannel = mRetrofit.create( UserChannel.class );

    }

    public static CovoiturageChannel getmCovoitirageChannel(){
        if(mCovoiturageChannel==null){
            init();
        }
        return mCovoiturageChannel;
    }

    public static UserChannel getmUserChannel(){
        if(mUserChannel==null){
            init();
        }
        return mUserChannel;
    }

    public static void getAllCovoiturages( String depart, String arrive, String date, final ICovoiturage listener ) {
        if (listener != null) {
            RetrofitHelper.getmCovoitirageChannel().getCovoiturages(depart,arrive,date).enqueue(new Callback<List<Covoiturage>>() {
                @Override
                public void onResponse(Call<List<Covoiturage>> call, Response<List<Covoiturage>> response) {
                    if (response != null) {
                        List<Covoiturage> channels = response.body();

                        if (channels != null) {
                            mListCovoiturages = channels;
                            listener.onRetrofitResult(true);
                        } else {
                            listener.onRetrofitResult(false);
                        }
                    } else {
                        listener.onRetrofitResult(false);
                    }
                }

                @Override
                public void onFailure(Call<List<Covoiturage>> call, Throwable t) {
                    listener.onRetrofitResult(false);
                }
            });



        }
    }

    public static void getUserInfo(int id, final IUserInterface listener){
        if (listener != null) {
            RetrofitHelper.getmUserChannel().getUserInfo(id).enqueue(new Callback<User>(){
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response != null) {
                        User channel = response.body();
                        if (channel != null) {
                            mUser = channel;
                            listener.onRetrofitResult(true);
                        } else {
                            listener.onRetrofitResult(false);
                        }
                    } else {
                        listener.onRetrofitResult(false);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    listener.onRetrofitResult(false);
                }
            });
        }

    }
}
