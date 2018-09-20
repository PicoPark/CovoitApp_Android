package pico.covoitapp.DataLayer.Api;

import java.util.ArrayList;


import pico.covoitapp.Model.Api.User;
import pico.covoitapp.Model.Api.UserInfo;
import pico.covoitapp.Model.Api.UserLogin;
import pico.covoitapp.Model.Realm.UserRealm;
import pico.covoitapp.Utils.ClientRetrofit;
import pico.covoitapp.Utils.Enum.ServiceExceptionType;
import pico.covoitapp.Utils.Interface.IServiceResultListener;
import pico.covoitapp.Utils.Interface.Realm.IUserInterface;
import pico.covoitapp.Utils.Interface.Retrofit.UserChannel;
import pico.covoitapp.Utils.ServiceException;
import pico.covoitapp.Utils.ServiceResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserApi  {
    private UserChannel mRfuserService;


    private UserChannel getmRfuserService() {
        if (mRfuserService == null)
            mRfuserService = ClientRetrofit.getClient().create(UserChannel.class);
        return mRfuserService;
    }


    public void create(User user, final IServiceResultListener<String> resultListener) {



        Call<Void> call = getmRfuserService().createUser(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, final Response<Void> response) {
                ServiceResult<String> result = new ServiceResult<>();
                if(response.code() == 201)
                    result.setmData(response.headers().get("Resourceuri"));
                else
                    result.setmError(new ServiceException(response.code()));
                if(resultListener != null)
                    resultListener.onResult(result);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                ServiceResult<String> result = new ServiceResult<>();
                result.setmError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if(resultListener != null)
                    resultListener.onResult(result);

            }
        });


    }


    public void connect(UserLogin userLogin, final IServiceResultListener<String> resultListener) {


        Call<String> call = getmRfuserService().connectUser(userLogin);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                ServiceResult<String> result = new ServiceResult<>();
                if(response.code() == 200)
                    result.setmData(response.body());
                else
                    result.setmError(new ServiceException(response.code()));
                if(resultListener != null)
                    resultListener.onResult(result);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                ServiceResult<String> result = new ServiceResult<>();
                result.setmError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if(resultListener != null)
                    resultListener.onResult(result);

            }
        });
    }


    public void getInfo(UserRealm userRealm, final IServiceResultListener<UserInfo> resultListener) {


      // Call<UserInfo> call = getmRfuserService().getAuthorizedDriver("Bearer "+userRealm.getToken());
      // call.enqueue(new Callback<UserInfo>() {
      //     @Override
      //     public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
      //         ServiceResult<UserInfo> result = new ServiceResult<>();
      //         if(response.code() == 200)
      //             result.setmData(response.body());
      //         else
      //             result.setmError(new ServiceException(response.code()));
      //         if(resultListener != null)
      //             resultListener.onResult(result);
      //     }

      //     @Override
      //     public void onFailure(Call<UserInfo> call, Throwable t) {
      //         ServiceResult<UserInfo> result = new ServiceResult<>();
      //         result.setmError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
      //         if(resultListener != null)
      //             resultListener.onResult(result);

      //     }
      // });
    }



}
