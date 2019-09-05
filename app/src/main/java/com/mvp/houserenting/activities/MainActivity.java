package com.mvp.houserenting.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.mvp.houserenting.R;
import com.mvp.houserenting.adapters.RecyclerItemAdapter;
import com.mvp.houserenting.data.VOS.HouseVO;
import com.mvp.houserenting.data.models.HouseModel;
import com.mvp.houserenting.data.models.HouseModelImpl;
import com.mvp.houserenting.delegates.HouseItemDelegate;

import java.sql.Array;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    TabLayout tabLayout;
    RecyclerItemAdapter recyclerItemAdapter;
    RecyclerView recyclerView;
    private StaggeredGridLayoutManager gridLayoutManager;
    private String[] nameArray;


    @BindView(R.id.actionGridView)
    ImageView imgGridView;

    @BindView(R.id.actionListView)
    ImageView imgListView;

    @BindView(R.id.searchView)
    AppCompatAutoCompleteTextView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tabLayout);
        ButterKnife.bind(this);

        imgGridView.setOnClickListener(this);
        imgListView.setOnClickListener(this);

        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.tab_collection)),0,true);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.tab_near_me)),1,false);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.tab_price)),2,false);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.tab_collection)),3,false);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.tab_collection)),4,false);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerItemAdapter=new RecyclerItemAdapter(new HouseItemDelegate() {
            @Override
            public void onTapHouseItem(int houseId) {
                startActivity(DetailActivity.newIntent(getApplicationContext(),houseId));
            }

            @Override
            public void onTapFabIcon(double latitude, double longitude) {
                showMap(latitude,longitude);
            }
        });

        gridLayoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);

//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recyclerItemAdapter);

       HouseModel.getHouses(new HouseModel.GetHousesFromDataLayerDelegate() {
           @Override
           public void onSuccess(List<HouseVO> houseList) {
               recyclerItemAdapter.setNewData(houseList);
               recyclerItemAdapter.notifyDataSetChanged();
               nameArray=new String[houseList.size()];
               for(int i=0;i<houseList.size();i++){
                  nameArray[i]=houseList.get(i).getName();
               }

               setUpAutoComplete();
           }

           @Override
           public void onFailure(String errorMessage) {

           }
       });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.actionGridView:
                gridLayoutManager.setSpanCount(2);
                break;
            case R.id.actionListView:
                gridLayoutManager.setSpanCount(1);
                break;
        }
    }

    private void setUpAutoComplete(){
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,nameArray);
        searchView.setThreshold(1);
        searchView.setAdapter(adapter);
    }

    private void showMap(double latitude,double longitude){
        String geoString="geo:"+latitude+","+longitude;
        Uri uri=Uri.parse("google.navigation:q=16.779292,96.154465");
//        "geo:37.7749,-122.4194"
        Intent mapIntent=new Intent(Intent.ACTION_VIEW,uri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }
}
