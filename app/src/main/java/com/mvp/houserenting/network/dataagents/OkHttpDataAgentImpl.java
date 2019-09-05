package com.mvp.houserenting.network.dataagents;

import android.os.AsyncTask;
import android.view.textclassifier.TextLinks;

import com.google.gson.Gson;
import com.mvp.houserenting.network.responses.GetHouseResponse;
import com.mvp.houserenting.utils.HouseConstants;

import java.io.IOException;
import java.util.EventListener;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpDataAgentImpl implements HouseDataAgent {
    private static OkHttpDataAgentImpl objInstance;
    private OkHttpClient mHttpClient;

    private OkHttpDataAgentImpl(){
        mHttpClient=new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpDataAgentImpl getObjInstance(){
        if(objInstance == null)
            objInstance=new OkHttpDataAgentImpl();
        return objInstance;
    }

    @Override
    public void getHousesFromNetwork(GetHousesFromNetworkDelegate networkDelegate) {
        new GetHouseTask(mHttpClient,networkDelegate).execute();
    }

    public static class GetHouseTask extends AsyncTask<Void,Void, GetHouseResponse>{
        private GetHousesFromNetworkDelegate mDelegate;
        private OkHttpClient mHttpClient;

        GetHouseTask(OkHttpClient httpClient,GetHousesFromNetworkDelegate delegate){
            mHttpClient=httpClient;
            mDelegate=delegate;
        }

        @Override
        protected GetHouseResponse doInBackground(Void... voids) {
            RequestBody formBody=new FormBody.Builder()
                    .build();

            Request request=new Request.Builder()
                    .url(HouseConstants.BASE_URL+HouseConstants.GET_HOUSES)
                    .post(formBody)
                    .build();

            try {
                Response response=mHttpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    String responseString=response.body().toString();
                    GetHouseResponse houseResponse=new Gson().fromJson(responseString,GetHouseResponse.class);
                    return houseResponse;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(GetHouseResponse getHouseResponse) {
            super.onPostExecute(getHouseResponse);
            if(getHouseResponse != null){
                if(getHouseResponse.isResponseOk()){
                    mDelegate.onSuccess(getHouseResponse.getHouseList());
                }else{
                    mDelegate.onFailure(getHouseResponse.getMessage());
                }
            }else {
                mDelegate.onFailure(HouseConstants.EM_NULL_HOUSE_RESPONSE);
            }
        }
    }

}
