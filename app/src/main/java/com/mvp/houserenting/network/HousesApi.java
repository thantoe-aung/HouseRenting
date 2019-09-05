package com.mvp.houserenting.network;

import com.mvp.houserenting.network.responses.GetHouseResponse;
import com.mvp.houserenting.utils.HouseConstants;

import retrofit2.Call;
import retrofit2.http.POST;

public interface HousesApi {

    @POST(HouseConstants.GET_HOUSES)
    Call<GetHouseResponse> getAllHouses();
}
