package com.example.idscomercial.examen1.vm;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.idscomercial.examen1.repository.Repository;

public class MainViewModel extends AndroidViewModel {
    private RepositoryCallback mRepo;
    private MutableLiveData<Boolean> mutableLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepo = Repository.getInstance(application);
        if (mutableLiveData  == null)
            mutableLiveData = new MutableLiveData<>();
    }

    public void getActivateEnrollmentStatus(Context context){
        mRepo.getEnrollmentPreferences(context, new ReturnDataFromPreferences() {
            @Override
            public void returnData(Boolean dataReturned) {
                mutableLiveData.setValue(dataReturned);
            }
        });
    }

    public MutableLiveData<Boolean> getWebLiveData(){
        return mutableLiveData;
    }
}