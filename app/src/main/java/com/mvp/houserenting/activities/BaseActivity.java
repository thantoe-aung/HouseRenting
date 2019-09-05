package com.mvp.houserenting.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mvp.houserenting.data.models.HouseModel;
import com.mvp.houserenting.data.models.HouseModelImpl;
import com.mvp.houserenting.delegates.HouseItemDelegate;

public abstract class BaseActivity extends AppCompatActivity {
    protected HouseModel HouseModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HouseModel= HouseModelImpl.getObjInstance();
    }

}
