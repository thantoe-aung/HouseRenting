package com.mvp.houserenting.network.responses;

import com.mvp.houserenting.data.VOS.HouseVO;

import java.util.List;

public class GetHouseResponse {
    private String message;
    private int code;
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
}
