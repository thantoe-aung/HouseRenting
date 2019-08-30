package com.mvp.houserenting.network.dataagents;

import com.mvp.houserenting.data.VOS.HouseVO;

import java.util.List;

public interface HouseDataAgent {

    void getHousesFromNetwork(GetHousesFromNetworkDelegate networkDelegate);

    interface GetHousesFromNetworkDelegate{
        void onSuccess(List<HouseVO> houseList);
        void onFailure(String errorMessage);
    }
}
