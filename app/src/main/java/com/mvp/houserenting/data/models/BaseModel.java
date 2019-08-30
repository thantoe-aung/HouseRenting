package com.mvp.houserenting.data.models;

import com.mvp.houserenting.network.dataagents.HouseDataAgent;
import com.mvp.houserenting.network.dataagents.HttpUrlConnectionDataAgentImpl;

public abstract class BaseModel {
  protected   HouseDataAgent mDataAgent;

    BaseModel(){
        mDataAgent= HttpUrlConnectionDataAgentImpl.getObjInstance();
    }
}
