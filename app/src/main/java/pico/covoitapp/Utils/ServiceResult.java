package pico.covoitapp.Utils;

public class ServiceResult<T> {

    T mData;
    ServiceException mError;

    public T getmData() {
        return mData;
    }

    public void setmData(T mData) {
        this.mData = mData;
    }

    public ServiceException getmError() {
        return mError;
    }

    public void setmError(ServiceException mError) {
        this.mError = mError;
    }

}
