package com.mvp.houserenting.data.models;

import com.mvp.houserenting.network.dataagents.HouseDataAgent;
import com.mvp.houserenting.network.dataagents.HttpUrlConnectionDataAgentImpl;
import com.mvp.houserenting.network.dataagents.OkHttpDataAgentImpl;
import com.mvp.houserenting.network.dataagents.RetrofitDataAgentImpl;

public abstract class BaseModel {
  protected   HouseDataAgent mDataAgent;

    BaseModel(){
//        mDataAgent= HttpUrlConnectionDataAgentImpl.getObjInstance();
//        mDataAgent= OkHttpDataAgentImpl.getObjInstance();
        mDataAgent= RetrofitDataAgentImpl.getObjInstance();
    }
}
