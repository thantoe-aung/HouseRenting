package com.mvp.houserenting.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.mvp.houserenting.views.holders.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T extends BaseViewHolder<W>,W> extends RecyclerView.Adapter<T> {
    private List<W> mData;

    BaseRecyclerAdapter(){
        mData=new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position, @NonNull List<Object> payloads) {
       holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setNewData(List<W> data){
        mData=data;
        notifyDataSetChanged();
    }
}
