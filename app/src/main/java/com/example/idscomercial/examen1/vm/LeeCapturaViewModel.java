package com.example.idscomercial.examen1.vm;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.idscomercial.examen1.repository.Repository;
import com.example.idscomercial.examen1.vm.datareturnutils.DatosConsultaHolder;

public class LeeCapturaViewModel {
    private static final String LOG_TAG = LeeCapturaViewModel.class.getSimpleName();

    private Context mContext;
    private RepositoryCallback mCrudRepoCallback;

    private MutableLiveData<DatosConsultaHolder> mLiveData;

    public LeeCapturaViewModel(Context context) {
        mContext = context;
        mCrudRepoCallback = Repository.getInstance(mContext);

        if(mLiveData == null){
            mLiveData = new MutableLiveData<>();
        }
    }

    public void getDataFromDB() {
        mCrudRepoCallback.readData(dataFromQuery -> mLiveData.setValue(dataFromQuery));
    }

    public MutableLiveData<DatosConsultaHolder> getmLiveData(){
        return mLiveData;
    }
}