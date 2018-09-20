package pico.covoitapp.Utils.Interface;

import pico.covoitapp.Utils.ServiceResult;

public interface IServiceResultListener<T> {
    void onResult(ServiceResult<T> result);
}