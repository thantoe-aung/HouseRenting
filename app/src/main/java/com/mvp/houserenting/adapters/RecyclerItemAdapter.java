package com.mvp.houserenting.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.houserenting.R;
import com.mvp.houserenting.data.VOS.HouseVO;
import com.mvp.houserenting.delegates.HouseItemDelegate;
import com.mvp.houserenting.views.holders.ItemViewHolder;

public class RecyclerItemAdapter extends BaseRecyclerAdapter<ItemViewHolder, HouseVO> {
    private HouseItemDelegate itemDelegate;

    public RecyclerItemAdapter(HouseItemDelegate itemDelegate) {
        this.itemDelegate = itemDelegate;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item_layout,viewGroup,false);
        return new ItemViewHolder(view,itemDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {

    }

}
