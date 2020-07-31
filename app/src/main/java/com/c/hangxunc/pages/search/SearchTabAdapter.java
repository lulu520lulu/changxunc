package com.c.hangxunc.pages.search;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.c.hangxunc.R;
import com.c.hangxunc.utils.DimenUtils;
import com.c.hangxunc.utils.WindowUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchTabAdapter extends RecyclerView.Adapter {

    private static final String TAG = SearchTabAdapter.class.getSimpleName();
    private SortOnClickListener mSortOnClickListener;
    private Drawable drawable = null;

    private Context mContext;
    private List<SortData> mDatas = new ArrayList<>();

    public SearchTabAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<SortData> data) {
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_sort_view, parent, false);
        SearchTabAdapter.SearchViewHolder viewHolder = new SearchTabAdapter.SearchViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SearchViewHolder viewHolder = (SearchViewHolder) holder;
        SortData item = mDatas.get(position);

        int windowWidth = WindowUtils.getWindowWidth((Activity) mContext);
        int width = windowWidth / 5;
        ViewGroup.LayoutParams params = viewHolder.mContainer.getLayoutParams();
        params.width = width;
        viewHolder.mContainer.setLayoutParams(params);

        viewHolder.mSortView.setText(item.name);
        if (item.checkFlag == SortData.DESC_FLAG) {
            drawable = mContext.getResources().getDrawable(R.mipmap.top_arrow);
            drawable.setBounds(0, 0, DimenUtils.dip2px(8), DimenUtils.dip2px(14));
        } else if (item.checkFlag == SortData.ASC_FLAG) {
            drawable = mContext.getResources().getDrawable(R.mipmap.bottom_arrow);
            drawable.setBounds(0, 0, DimenUtils.dip2px(8), DimenUtils.dip2px(14));
        } else {
            drawable = mContext.getResources().getDrawable(R.mipmap.search_two_arrow);
            drawable.setBounds(0, 0, DimenUtils.dip2px(8), DimenUtils.dip2px(14));
        }
        viewHolder.mSortView.setCompoundDrawables(null, null, drawable, null);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String order = SortData.ASC;
                if (item.checkFlag == SortData.DESC_FLAG) {
                    drawable = mContext.getResources().getDrawable(R.mipmap.bottom_arrow);
                    drawable.setBounds(0, 0, DimenUtils.dip2px(8), DimenUtils.dip2px(14));
                } else if (item.checkFlag == SortData.ASC_FLAG) {
                    drawable = mContext.getResources().getDrawable(R.mipmap.top_arrow);
                    drawable.setBounds(0, 0, DimenUtils.dip2px(8), DimenUtils.dip2px(14));
                    order = SortData.DESC;
                } else {
                    drawable = mContext.getResources().getDrawable(R.mipmap.top_arrow);
                    drawable.setBounds(0, 0, DimenUtils.dip2px(8), DimenUtils.dip2px(14));
                }
                viewHolder.mSortView.setCompoundDrawables(null, null, drawable, null);


                if (mSortOnClickListener != null) {
                    mSortOnClickListener.onSortSelectListener(item.key, order);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }


    public void setSortOnClickListener(SortOnClickListener sortOnClickListener) {
        this.mSortOnClickListener = sortOnClickListener;
    }

    private class SearchViewHolder extends RecyclerView.ViewHolder {

        private TextView mSortView;
        private FrameLayout mContainer;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            mSortView = itemView.findViewById(R.id.sort_view);
            mContainer = itemView.findViewById(R.id.container);
            mContainer.setClickable(true);
            mSortView.setClickable(false);
        }

    }

}
