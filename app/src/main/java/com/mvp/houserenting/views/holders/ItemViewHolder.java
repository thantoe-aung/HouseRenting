package com.mvp.houserenting.views.holders;

import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mvp.houserenting.R;
import com.mvp.houserenting.data.VOS.HouseVO;
import com.mvp.houserenting.delegates.HouseItemDelegate;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemViewHolder extends BaseViewHolder<HouseVO> {
    private HouseItemDelegate mDelegate;

    @BindView(R.id.imageView)
    ImageView ivHousePhoto;

    @BindView(R.id.btnPrice)
    MaterialButton btnPrice;

    @BindView(R.id.txtSquareFeet)
    TextView tvSquareFeet;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.fabNearMe)
    FloatingActionButton fabLocation;

    public ItemViewHolder(@NonNull final View itemView, final HouseItemDelegate delegate) {
        super(itemView);
        mDelegate=delegate;
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.onTapHouseItem(mData.getId());
            }
        });

        fabLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               delegate.onTapFabIcon(mData.getLatitude(),mData.getLongitude());
            }
        });
    }


    @Override
    public void bindData(HouseVO data) {
        mData=data;
        Glide.with(ivHousePhoto).load(mData.getHouseImageUrl()).into(ivHousePhoto);
        tvSquareFeet.setText(String.valueOf(mData.getSquareFeet()));
        tvName.setText(mData.getName());
        DecimalFormat formatter = new DecimalFormat("#,###");

        double price=mData.getPrice()/1500;
        btnPrice.setText("$"+formatter.format(price));
    }
}
