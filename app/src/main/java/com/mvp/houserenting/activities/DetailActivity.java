package com.mvp.houserenting.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mvp.houserenting.R;
import com.mvp.houserenting.data.VOS.HouseVO;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity {
    ImageButton imgBack;
    public static final String HOUSE_ID_EXTRA="houseId";

    @BindView(R.id.ivBackground)
    ImageView ivBackGround;

    @BindView(R.id.tvDetail)
    TextView tvDetail;

    @BindView(R.id.txtPrice)
    TextView tvPrice;

    @BindView(R.id.txtName)
    TextView tvName;

    @BindView(R.id.txtDetailSquareFeet)
    TextView tvSquareFeet;

    public static Intent newIntent(Context context,int houseId){
        Intent intent=new Intent(context,DetailActivity.class);
        intent.putExtra(HOUSE_ID_EXTRA,houseId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        imgBack=findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailActivity.super.onBackPressed();
            }
        });

        int houseId=getIntent().getIntExtra(HOUSE_ID_EXTRA,0);
        HouseVO house=HouseModel.getHouseById(houseId);
        bindData(house);
    }

    private void bindData(HouseVO house){
        Glide.with(ivBackGround).load(house.getHouseImageUrl()).into(ivBackGround);
        tvName.setText(house.getName());

        DecimalFormat formatter = new DecimalFormat("#,###");
        double price=house.getPrice()/1500;
        tvPrice.setText("$"+formatter.format(price));
        tvDetail.setText(house.getDescription());
        tvSquareFeet.setText(String.valueOf(house.getSquareFeet()));
    }
}
