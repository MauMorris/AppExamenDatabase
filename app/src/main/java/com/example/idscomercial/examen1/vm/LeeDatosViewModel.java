package com.example.idscomercial.examen1.vm;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;

import com.example.idscomercial.examen1.repository.CrudDatabaseRepository;
import com.example.idscomercial.examen1.datasource.DatabaseHelper;
import com.example.idscomercial.examen1.vm.datareturnutils.DatosConsultaHolder;

public class LeeDatosViewModel {
    private Context mContext;
    private CrudDatabaseRepositoryCallback mCrud;

    private MutableLiveData<DatosConsultaHolder> mLiveData;
    private MutableLiveData<String> mWebLiveData;

    public LeeDatosViewModel(Context context) {
        mContext = context;
        mCrud = CrudDatabaseRepository.getInstance(mContext);

        if(mLiveData == null){
            mLiveData = new MutableLiveData<>();
        }

        if(mWebLiveData == null){
            mWebLiveData = new MutableLiveData<>();
        }
    }

    public void getDataFromDB(Context context) {
        mCrud.readData(data -> mLiveData.setValue(data));
    }

    public MutableLiveData<DatosConsultaHolder> getLiveData(){
        return mLiveData;
    }

    public MutableLiveData<String> getWebLiveData(){return mWebLiveData;}

    public void getDataFromInternet(String test, String salary, String age) {
        mCrud.getDataFromWeb(test, salary, age, new ReturnDataFromWeb(){

            @Override
            public void returnWebData(String data) {
                mWebLiveData.setValue(data);
            }
        });
    }
}