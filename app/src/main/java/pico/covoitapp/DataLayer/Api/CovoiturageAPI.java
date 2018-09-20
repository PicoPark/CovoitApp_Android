package pico.covoitapp.DataLayer.Api;

import java.util.List;

import pico.covoitapp.Model.Api.CovoitCall;
import pico.covoitapp.Model.Api.Covoiturage;
import pico.covoitapp.Model.Realm.CovoiturageRealm;
import pico.covoitapp.Utils.ClientRetrofit;
import pico.covoitapp.Utils.Enum.ServiceExceptionType;
import pico.covoitapp.Utils.Interface.IServiceResultListener;
import pico.covoitapp.Utils.Interface.Realm.ICovoiturage;
import pico.covoitapp.Utils.Interface.Retrofit.CovoiturageChannel;
import pico.covoitapp.Utils.ServiceException;
import pico.covoitapp.Utils.ServiceResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovoiturageAPI {

    private CovoiturageChannel mRfcovoiturageService;

    private CovoiturageChannel getmRfcovoiturageService() {
        if (mRfcovoiturageService == null)
            mRfcovoiturageService = ClientRetrofit.getClient().create(CovoiturageChannel.class);
        return mRfcovoiturageService;
    }

//
 //   @Override
 //   public void getInfo(CovoitCall call, final IServiceResultListener<List<Covoiturage>> resultListener) {
 //       Call<CovoitCall> call = getmRfcovoiturageService().getCovoiturage(call);
 //       call.enqueue(new Callback<List<Covoiturage>>() {
 //           @Override
 //           public void onResponse(Call<CovoitCall> call, final Response<List<Covoiturage>> response) {
 //               ServiceResult<List<Covoiturage>> result = new ServiceResult<>();
 //               if(response.code() == 201)
 //                   result.setmData(response.body());
 //               else
 //                   result.setmError(new ServiceException(response.code()));
 //               if(resultListener != null)
 //                   resultListener.onResult(result);
 //           }

 //           @Override
 //           public void onFailure(Call<Void> call, Throwable t) {
//
 //               ServiceResult<String> result = new ServiceResult<>();
 //               result.setmError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
 //               if(resultListener != null)
 //                   resultListener.onResult(result);
//
 //           }
 //       });
 //   }

  // @Override
  // public void getCovoiturage(int id, final IServiceResultListener<Covoiturage> resultListener) {
  //     Call<CovoitCall> call = getmRfcovoiturageService().getCovoiturage(call);
  //     call.enqueue(new Callback<List<Covoiturage>>() {
  //         @Override
  //         public void onResponse(Call<CovoitCall> call, final Response<List<Covoiturage>> response) {
  //             ServiceResult<List<Covoiturage>> result = new ServiceResult<>();
  //             if(response.code() == 201)
  //                 result.setmData(response.body()));
  //             else
  //             result.setmError(new ServiceException(response.code()));
  //             if(resultListener != null)
  //                 resultListener.onResult(result);
  //         }

  //         @Override
  //         public void onFailure(Call<Void> call, Throwable t) {

  //             ServiceResult<String> result = new ServiceResult<>();
  //             result.setmError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
  //             if(resultListener != null)
  //                 resultListener.onResult(result);

  //         }
  //     });
  // }
}
