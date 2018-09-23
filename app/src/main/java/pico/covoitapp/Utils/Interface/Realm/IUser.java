package pico.covoitapp.Utils.Interface.Realm;

import pico.covoitapp.Model.Api.User;
import pico.covoitapp.Model.Api.UserInfo;
import pico.covoitapp.Model.Api.UserLogin;
import pico.covoitapp.Model.Realm.UserRealm;
import pico.covoitapp.Utils.Interface.IServiceResultListener;

public interface IUser {

   // void create(User user, final IServiceResultListener<String>
   //         resultListener);
   // void connect(UserLogin userLogin, final IServiceResultListener<String>
   //         resultListener);
//
   // void getInfo(UserRealm userRealm, final IServiceResultListener<UserInfo>
   //         resultListener);


    public void onRetrofitResult( boolean okay );

}
