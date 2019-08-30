package com.mvp.houserenting.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mvp.houserenting.R;
import com.mvp.houserenting.adapters.RecyclerItemAdapter;
import com.mvp.houserenting.data.VOS.HouseVO;
import com.mvp.houserenting.data.models.HouseModel;
import com.mvp.houserenting.data.models.HouseModelImpl;
import com.mvp.houserenting.delegates.HouseItemDelegate;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    RecyclerItemAdapter recyclerItemAdapter;
    RecyclerView recyclerView;

    private HouseModel houseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tabLayout);

//        tabLayout.addTab(new TabLayout.Tab().setText(getResources().getString(R.string.tab_collection)),0,true);
//        tabLayout.addTab(new TabLayout.Tab(),1,true);
//        tabLayout.addTab(new TabLayout.Tab(),2,false);
//        tabLayout.addTab(new TabLayout.Tab(),3,false);
//        tabLayout.addTab(new TabLayout.Tab(),4,false);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.tab_collection)),0,true);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.tab_near_me)),1,false);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.tab_price)),2,false);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.tab_collection)),3,false);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.tab_collection)),4,false);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerItemAdapter=new RecyclerItemAdapter(new HouseItemDelegate() {
            @Override
            public void onTapHouseItem() {
                startActivity(new Intent(MainActivity.this,DetailActivity.class));
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerItemAdapter);

        houseModel= HouseModelImpl.getObjInstance();
        houseModel.getHouses(new HouseModel.GetHousesFromDataLayerDelegate() {
            @Override
            public void onSuccess(List<HouseVO> houseList) {
                int count=0;
                if(houseList != null)
                    count=houseList.size();
                Toast.makeText(getApplicationContext(),"Request Success " + count,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
            }
        });

    }
}
