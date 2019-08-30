package com.mvp.houserenting.network.dataagents;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvp.houserenting.network.responses.GetHouseResponse;
import com.mvp.houserenting.utils.HouseConstants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class HttpUrlConnectionDataAgentImpl implements HouseDataAgent{

    public static HttpUrlConnectionDataAgentImpl objInstance;

    private HttpUrlConnectionDataAgentImpl(){}


    public static HttpUrlConnectionDataAgentImpl getObjInstance(){
        if(objInstance == null)
            objInstance=new HttpUrlConnectionDataAgentImpl();
        return objInstance;
    }


    @Override
    public void getHousesFromNetwork(GetHousesFromNetworkDelegate networkDelegate) {
        new GetHouseTask(networkDelegate).execute();
    }

    public class GetHouseTask extends AsyncTask<Void,Void, GetHouseResponse>{
        private GetHousesFromNetworkDelegate networkDelegate;

        public GetHouseTask(GetHousesFromNetworkDelegate networkDelegate) {
            this.networkDelegate = networkDelegate;
        }

        @Override
        protected GetHouseResponse doInBackground(Void... voids) {
            URL url;
            BufferedReader bufferedReader=null;
            StringBuilder stringBuilder;

            try {
                url=new URL(HouseConstants.BASE_URL + HouseConstants.GET_HOUSES);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setReadTimeout(15 * 10000);
                connection.setDoInput(true);
                connection.setDoOutput(true);

//                OutputStream outputStream=connection.getOutputStream();
//                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
//                writer.flush();
//                writer.close();
//                outputStream.close();

                connection.connect();

                bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                stringBuilder=new StringBuilder();
                String line=null;
                while (((line=bufferedReader.readLine()) != null)){
                    stringBuilder.append(line + " \n");
                }

                String responseString=stringBuilder.toString();
                Gson gson = new GsonBuilder().create();
                GetHouseResponse response=new Gson().fromJson(responseString,GetHouseResponse.class);

                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(bufferedReader !=null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

//        public String getQuery(List<NameValuePair> params){
//            StringBuilder result=new StringBuilder();
//            boolean first=true;
//            for(NameValuePair pair : params ){
//                if(first){
//                    first=false;
//                }else
//                    result.append("&");
//                try {
//                    result.append(URLEncoder.encode(pair.getName(),"UTF-8"));
//                    result.append("=");
//                    result.append(URLEncoder.encode(pair.getValue(),"UTF-8"));
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//            }
//            return result.toString();
//        }

        @Override
        protected void onPostExecute(GetHouseResponse getHouseResponse) {
            super.onPostExecute(getHouseResponse);
            if(getHouseResponse !=null){
                networkDelegate.onSuccess(getHouseResponse.getHouseList());
            }else{
                networkDelegate.onFailure("Can't connect to server");
            }
        }
    }
}
