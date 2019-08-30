package com.mvp.houserenting.views.holders;

import android.support.annotation.NonNull;
import android.view.View;

import com.mvp.houserenting.delegates.HouseItemDelegate;

public class ItemViewHolder extends BaseViewHolder {
    private HouseItemDelegate mDelegate;

    public ItemViewHolder(@NonNull View itemView, final HouseItemDelegate delegate) {
        super(itemView);
        mDelegate=delegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.onTapHouseItem();
            }
        });
    }

    @Override
    public void bindData(Object mData) {

    }
}
