package pico.covoitapp.DataLayer;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

import pico.covoitapp.Model.Api.MCovoiturage;
import pico.covoitapp.Model.Api.MUtilisateur;
import pico.covoitapp.Model.Api.MVehicule;
import pico.covoitapp.Model.Api.Reservation;
import pico.covoitapp.Utils.Interface.Retrofit.CovoiturageChannel;
import pico.covoitapp.Utils.Interface.Retrofit.ICovoiturage;
import pico.covoitapp.Utils.Interface.Retrofit.IReservation;
import pico.covoitapp.Utils.Interface.Retrofit.IUser;
import pico.covoitapp.Utils.Interface.Retrofit.ReservationChannel;
import pico.covoitapp.Utils.Interface.Retrofit.UserChannel;
import pico.covoitapp.Utils.Tools;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static final String TAG = "covoitApp.Retrofit";
    private static final String BASE_URL = "http://scmp1-expea-001.azurewebsites.net/api/Service1.svc/";

    private static Retrofit mRetrofit = null;
    private static CovoiturageChannel mCovoiturageChannel = null;
    private static UserChannel mUserChannel = null;
    private static ReservationChannel mReservationChannel = null;

    public static List<MCovoiturage> mListCovoiturages;
    public static List<MVehicule> mListVehicule;
    public static List<Reservation> mListReservationsConducteur;
    public static List<Reservation> mListReservationsPassager;
    public static MUtilisateur mUser;
    public static MUtilisateur me;
    public static Reservation mresa;
    public static MCovoiturage mCovoiturage;

    public static void init() {
        // Gson gson = new GsonBuilder( ).setDateFormat( "yyyy-MM-dd'T'HH:mm:ssZ" ).create( );
        mListReservationsConducteur = new ArrayList<>();
        mListReservationsPassager = new ArrayList<>();
        mListVehicule = new ArrayList<>();
        mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
        mCovoiturageChannel = mRetrofit.create(CovoiturageChannel.class);
        mUserChannel = mRetrofit.create(UserChannel.class);
        mReservationChannel = mRetrofit.create(ReservationChannel.class);


    }

    public static CovoiturageChannel getmCovoiturageChannel() {
        if (mCovoiturageChannel == null) {
            init();
        }
        return mCovoiturageChannel;
    }

    public static UserChannel getmUserChannel() {
        if (mUserChannel == null) {
            init();
        }
        return mUserChannel;
    }

    public static ReservationChannel getmReservationChannel() {
        if (mReservationChannel == null) {
            init();
        }
        return mReservationChannel;
    }

    public static void connect(MUtilisateur user, final IUser listener) {
        if (listener != null) {
            RetrofitHelper.getmUserChannel().connectUser(user).enqueue(new Callback<MUtilisateur>() {
                @Override
                public void onResponse(Call<MUtilisateur> call, Response<MUtilisateur> response) {
                    if (response != null) {
                        MUtilisateur result = response.body();
                        if (result != null) {
                            me = result;
                            listener.onRetrofitResult(true);
                        } else {
                            listener.onRetrofitResult(false);
                        }
                    } else {
                        listener.onRetrofitResult(false);
                    }
                }

                @Override
                public void onFailure(Call<MUtilisateur> call, Throwable t) {
                    listener.onRetrofitResult(false);

                }
            });
        }

    }

    public static void verification(String email, final IUser listener) {

        RetrofitHelper.getmUserChannel().verification(email).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean result = response.body();
                if (result) {
                    listener.onRetrofitResult(true);
                } else {
                    listener.onRetrofitResult(false);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onRetrofitResult(false);
            }
        });

    }

    public static void addUser(MUtilisateur user, final IUser listener) {
        if (listener != null) {
            RetrofitHelper.getmUserChannel().createUser(user).enqueue(new Callback<MUtilisateur>() {
                @Override
                public void onResponse(Call<MUtilisateur> call, Response<MUtilisateur> response) {
                    if (response != null) {
                        MUtilisateur result = response.body();
                        if (result.getMail() != null) {
                            me = result;
                            listener.onRetrofitResult(true);
                        } else
                            listener.onRetrofitResult(false);
                    } else
                        listener.onRetrofitResult(false);
                }

                @Override
                public void onFailure(Call<MUtilisateur> call, Throwable t) {
                    listener.onRetrofitResult(false);

                }
            });
        }

    }

    public static void getAllCovoiturages(MCovoiturage covoit, final ICovoiturage listener) {
        Request query = RetrofitHelper.getmCovoiturageChannel().getListCovoiturage(covoit).request();
        Log.e(TAG, "body : " + Tools.bodyToString(query));
        if (listener != null) {
            RetrofitHelper.getmCovoiturageChannel().getListCovoiturage(covoit).enqueue(new Callback<List<MCovoiturage>>() {
                @Override
                public void onResponse(Call<List<MCovoiturage>> call, Response<List<MCovoiturage>> response) {
                    if (response != null) {
                        Log.e(TAG, "response : " + response.toString());
                        List<MCovoiturage> channels = response.body();
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
                public void onFailure(Call<List<MCovoiturage>> call, Throwable t) {
                    listener.onRetrofitResult(false);
                }
            });


        }
    }

    public static void getUserInfo(int id, final IUser listener) {
        Log.e(TAG, String.valueOf(id));
        Request query = RetrofitHelper.getmUserChannel().getUserInfo("7").request();
        Log.e(TAG, "body : " + query.url());
        if (listener != null) {
            RetrofitHelper.getmUserChannel().getUserInfo("7").enqueue(new Callback<MUtilisateur>() {
                @Override
                public void onResponse(Call<MUtilisateur> call, Response<MUtilisateur> response) {
                    if (response != null) {
                        MUtilisateur channel = response.body();
                        Log.e(TAG, response.message());
                        if (channel != null) {
                            mUser = channel;
                            listener.onRetrofitResult(true);
                        } else
                            listener.onRetrofitResult(false);
                    } else
                        listener.onRetrofitResult(false);
                }

                @Override
                public void onFailure(Call<MUtilisateur> call, Throwable t) {
                    listener.onRetrofitResult(false);
                }
            });
        }

    }

    public static void saveReservation(Reservation resa, final IReservation listener) {
        Request query = RetrofitHelper.getmReservationChannel().postReservation(resa).request();
        Log.e(TAG, Tools.bodyToString(query));
        RetrofitHelper.getmReservationChannel().postReservation(resa).enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                if (response != null) {
                    Reservation channel = response.body();
                    Log.e(TAG, response.message());
                    if (channel != null) {
                        mresa = channel;
                        listener.onRetrofitResult(true);
                    } else
                        listener.onRetrofitResult(false);
                } else
                    listener.onRetrofitResult(false);
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                listener.onRetrofitResult(false);
            }
        });


    }

    public static void saveCovoiturage(MCovoiturage covoit, final ICovoiturage listener) {
        RetrofitHelper.getmCovoiturageChannel().postCovoiturage(covoit).enqueue(new Callback<MCovoiturage>() {
            @Override
            public void onResponse(Call<MCovoiturage> call, Response<MCovoiturage> response) {
                if (response != null) {
                    MCovoiturage result = response.body();
                    if (result.getArrive() != null) {
                        mCovoiturage = result;
                        listener.onRetrofitResult(true);
                    } else
                        listener.onRetrofitResult(false);
                } else
                    listener.onRetrofitResult(false);
            }

            @Override
            public void onFailure(Call<MCovoiturage> call, Throwable t) {
                listener.onRetrofitResult(false);
            }
        });

    }

    public static void getAllVoiture(final IUser listener){
        RetrofitHelper.getmUserChannel().getAllVehicule().enqueue(new Callback<List<MVehicule>>() {
            @Override
            public void onResponse(Call<List<MVehicule>> call, Response<List<MVehicule>> response) {
                Log.e(TAG, "response : " + response.toString());
                if (response != null) {
                    List<MVehicule> channels = response.body();
                    Log.e(TAG, "response : " + response.toString());
                    if (channels != null) {
                        mListVehicule = channels;
                        listener.onRetrofitResult(true);
                    } else {
                        listener.onRetrofitResult(false);
                    }
                } else {
                    listener.onRetrofitResult(false);

                }
            }

            @Override
            public void onFailure(Call<List<MVehicule>> call, Throwable t) {
                listener.onRetrofitResult(false);
            }

        });

    }

    public static void getReservationFromConducteur(int id, final IReservation listener) {
        Request query = RetrofitHelper.getmReservationChannel().getReservationByConducteur(String.valueOf(id)).request();
        Log.e(TAG, "body : " + query.url());
        RetrofitHelper.getmReservationChannel().getReservationByConducteur(String.valueOf(id)).enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                if (response != null) {
                    Log.e(TAG, response.toString());
                    List<Reservation> result = response.body();
                    if (result.size() != 0) {
                        mListReservationsConducteur = result;
                        listener.onRetrofitResult(true);
                    } else
                        listener.onRetrofitResult(false);
                } else
                    listener.onRetrofitResult(false);

            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {

            }
        });
    }

    public static void getReservationFromPassager(int id, final IReservation listener) {
        Request query = RetrofitHelper.getmReservationChannel().getReservationByPassager(String.valueOf(id)).request();
        Log.e(TAG, "body : " + query.url());
        RetrofitHelper.getmReservationChannel().getReservationByPassager(String.valueOf(id)).enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                if (response != null) {
                    Log.e(TAG, response.toString());
                    List<Reservation> result = response.body();
                    if (result.size() != 0) {
                        mListReservationsPassager = result;
                        listener.onRetrofitResult(true);
                    }else
                        listener.onRetrofitResult(false);
                }else
                    listener.onRetrofitResult(false);
            }


            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                listener.onRetrofitResult(false);
            }
        });
    }

}

