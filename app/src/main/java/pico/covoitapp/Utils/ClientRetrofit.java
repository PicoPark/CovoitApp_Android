package pico.covoitapp.Utils;

import pico.covoitapp.Utils.Interface.Retrofit.UserChannel;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ClientRetrofit {

    public ClientRetrofit() {
    }

    public static Retrofit getClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.api")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }


}
