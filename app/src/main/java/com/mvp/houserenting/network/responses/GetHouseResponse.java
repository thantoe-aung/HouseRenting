package com.mvp.houserenting.network.responses;

import com.google.gson.annotations.SerializedName;
import com.mvp.houserenting.data.VOS.HouseVO;
import com.mvp.houserenting.utils.HouseConstants;

import java.util.List;

public class GetHouseResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private List<HouseVO> houseList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<HouseVO> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<HouseVO> houseList) {
        this.houseList = houseList;
    }

    public boolean isResponseOk(){
        return code== HouseConstants.EVENT_RESPONSE_OK && houseList != null;
    }
}
