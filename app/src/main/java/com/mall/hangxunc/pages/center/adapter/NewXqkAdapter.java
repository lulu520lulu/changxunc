package com.mall.hangxunc.pages.center.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mall.hangxunc.R;
import com.mall.hangxunc.pages.center.center.XqkChildBean;
import com.mall.hangxunc.utils.DimenUtils;

import java.util.List;

public class NewXqkAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<XqkChildBean.ListBean> mData;

    public NewXqkAdapter(Activity mContext, List<XqkChildBean.ListBean> list) {
        this.mData = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.center_home_xqk_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        XqkChildBean.ListBean item = mData.get(position);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.title.setText(item.getTitle());
        viewHolder.see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        expandTouchArea(viewHolder.see_more, DimenUtils.dip2px(20));
        viewHolder.type_text.setText(item.getTypeName());
        viewHolder.ying_text.setText(item.getIndustryName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        if ((position + 1) == mData.size()) {
            viewHolder.line.setVisibility(View.GONE);
        } else {
            viewHolder.line.setVisibility(View.VISIBLE);
        }
    }

    private void expandTouchArea(View view, int size) {
        View parentView = (View) view.getParent();
        parentView.post(new Runnable() {
            @Override
            public void run() {
                Rect rect = new Rect();
                view.getHitRect(rect);

                rect.top -= size;
                rect.bottom += size;
                rect.left -= size;
                rect.right += size;

                parentView.setTouchDelegate(new TouchDelegate(rect, view));
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView ying_text;
        private TextView type_text;
        private TextView see_more;
        private View line;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            ying_text = itemView.findViewById(R.id.ying_text);
            type_text = itemView.findViewById(R.id.type_text);
            see_more = itemView.findViewById(R.id.see_more);
            line = itemView.findViewById(R.id.line);

        }
    }
}
