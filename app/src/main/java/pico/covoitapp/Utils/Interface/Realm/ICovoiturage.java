package pico.covoitapp.Utils.Interface.Realm;

import pico.covoitapp.Model.Api.Covoiturage;
import pico.covoitapp.Model.Realm.CovoiturageRealm;
import pico.covoitapp.Utils.Interface.IServiceResultListener;

public interface ICovoiturage {

    public void onRetrofitResult( boolean okay );
}
