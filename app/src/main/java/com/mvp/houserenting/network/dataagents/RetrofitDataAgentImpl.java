package com.mvp.houserenting.network.dataagents;

import com.mvp.houserenting.network.HousesApi;
import com.mvp.houserenting.network.responses.GetHouseResponse;
import com.mvp.houserenting.utils.HouseConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements HouseDataAgent {
    private static RetrofitDataAgentImpl objInstance;
    private HousesApi mHouseApi;

    private RetrofitDataAgentImpl(){
        OkHttpClient httpClient=new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(HouseConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        mHouseApi=retrofit.create(HousesApi.class);
    }

    public static RetrofitDataAgentImpl getObjInstance(){
        if(objInstance == null)
            objInstance=new RetrofitDataAgentImpl();
        return objInstance;
    }


    @Override
    public void getHousesFromNetwork(final GetHousesFromNetworkDelegate networkDelegate) {
        Call<GetHouseResponse> houseResponseCall=mHouseApi.getAllHouses();
        houseResponseCall.enqueue(new Callback<GetHouseResponse>() {
            @Override
            public void onResponse(Call<GetHouseResponse> call, Response<GetHouseResponse> response) {
                GetHouseResponse houseResponse=response.body();
                networkDelegate.onSuccess(houseResponse.getHouseList());
            }

            @Override
            public void onFailure(Call<GetHouseResponse> call, Throwable t) {
                networkDelegate.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
