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

    public LeeDatosViewModel(Context context) {
        mContext = context;
        mCrud = CrudDatabaseRepository.getInstance(mContext);

        if(mLiveData == null){
            mLiveData = new MutableLiveData<>();
        }
    }

    public void getDataFromDB(Context context) {
        mCrud.readData(data -> mLiveData.setValue(data));
    }

    public MutableLiveData<DatosConsultaHolder> getLiveData(){
        return mLiveData;
    }
}