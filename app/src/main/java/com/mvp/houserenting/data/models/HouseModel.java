package com.mvp.houserenting.data.models;

import com.mvp.houserenting.data.VOS.HouseVO;

import java.util.List;

public interface HouseModel {
    void getHouses(GetHousesFromDataLayerDelegate dataLayerDelegate);

    interface GetHousesFromDataLayerDelegate{
        void onSuccess(List<HouseVO> houseList);
        void onFailure(String errorMessage);
    }
}
