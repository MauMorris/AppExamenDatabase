package com.example.idscomercial.examen1.vm;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.idscomercial.examen1.device.notifications.NotificationUtils;
import com.example.idscomercial.examen1.repository.Repository;

public class EnrollmentCodigoAutorizacionViewModel extends AndroidViewModel {
    private RepositoryCallback mRepo;
    private MutableLiveData<String> mWebLiveData;

    public EnrollmentCodigoAutorizacionViewModel(@NonNull Application application) {
        super(application);
        mRepo = Repository.getInstance(application);
        if(mWebLiveData == null){
            mWebLiveData = new MutableLiveData<>();
        }
    }

    public void requestValidCode(Context context){
        NotificationUtils.setNotificationForValidCode(context);
    }

    public MutableLiveData<String> getWebLiveData(){return mWebLiveData;}

    public void getDataFromInternet(String test, String salary, String age) {
        mRepo.getDataFromWeb(test, salary, age, new ReturnDataFromWeb(){
            @Override
            public void returnWebData(String data) {
                mWebLiveData.setValue(data);
            }
        });
    }
}