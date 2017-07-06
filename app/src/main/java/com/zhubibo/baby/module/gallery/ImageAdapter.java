package com.zhubibo.baby.module.gallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhubibo.baby.R;
import com.zhubibo.baby.util.DensityUtil;

import java.util.Collections;
import java.util.List;

/**
 * Created by kevin on 2017/7/4.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>
        implements MyItemTouchHelperCallback.OnDragListener {

    private List<ImageEntity> data;
    private boolean isGrid;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public void setGrid(boolean grid) {
        isGrid = grid;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ImageAdapter(List<ImageEntity> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_image, parent, false);
        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ImageEntity imageEntity = data.get(position);
        holder.iv.setImageResource(imageEntity.imageId);
        holder.tv.setText(imageEntity.title);
        holder.tv.setTextColor(imageEntity.textColor);
        holder.tv.setBackgroundColor(imageEntity.bgColor);
        RelativeLayout.LayoutParams params;
        if (isGrid) {
            params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dp2px(mContext, 200));
        } else {
            params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        holder.iv.setLayoutParams(params);

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(imageEntity);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(data, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.adapter_image);
            tv = (TextView) itemView.findViewById(R.id.adapter_text);
        }
    }

    interface OnItemClickListener {

        void onItemClick(ImageEntity imageEntity);
    }
}
