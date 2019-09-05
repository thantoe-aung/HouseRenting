package com.mvp.houserenting.data.models;

import com.mvp.houserenting.data.VOS.HouseVO;
import com.mvp.houserenting.network.dataagents.HouseDataAgent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HouseModelImpl extends BaseModel implements HouseModel {
    private static HouseModelImpl objInstance;
    private Map<Integer, HouseVO> mDataRepository;

    private  HouseModelImpl(){
        mDataRepository=new HashMap<>();
    }


    public static HouseModelImpl getObjInstance(){
        if(objInstance == null)
            objInstance=new HouseModelImpl();
        return objInstance;
    }

    @Override
    public void getHouses(final GetHousesFromDataLayerDelegate dataLayerDelegate) {
       mDataAgent.getHousesFromNetwork(new HouseDataAgent.GetHousesFromNetworkDelegate() {
           @Override
           public void onSuccess(List<HouseVO> houseList) {
               for(HouseVO house : houseList){
                   mDataRepository.put(house.getId(),house);
               }
               dataLayerDelegate.onSuccess(houseList);
           }

           @Override
           public void onFailure(String errorMessage) {
                dataLayerDelegate.onFailure(errorMessage);
           }
       });
    }

    @Override
    public HouseVO getHouseById(int houseId) {
        return mDataRepository.get(houseId);
    }

}
