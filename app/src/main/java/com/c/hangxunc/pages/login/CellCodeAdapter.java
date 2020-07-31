//package com.c.hangxunc.pages.login;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.c.hangxunc.R;
//import com.c.hangxunc.bean.home.CountryBean;
//
//import java.util.List;
//
//public class CellCodeAdapter extends RecyclerView.Adapter {
//
//    private Context mContext;
//    private List<CountryBean> mData;
//    private CellCodeDialog.ItemClickListener mListener;
//
//    public CellCodeAdapter(Context mContext, List<CountryBean> posts, CellCodeDialog.ItemClickListener listener) {
//        this.mContext = mContext;
//        this.mData = posts;
//        this.mListener = listener;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.list_cell_code_item, parent, false);
//        MyViewHolder holder = new MyViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        CountryBean bean = mData.get(position);
//
//        MyViewHolder viewHolder = (MyViewHolder) holder;
//        viewHolder.text.setText(bean.getName());
//
//
//        if (viewHolder.itemView != null) {
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListener != null) {
//                        mListener.onItemClick(bean);
//                    }
//                }
//            });
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        if (mData == null) {
//            return 0;
//        }
//        return mData.size();
//    }
//
//    private class MyViewHolder extends RecyclerView.ViewHolder {
//        private TextView text;
//        private View line;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            text = itemView.findViewById(R.id.text);
//            line = itemView.findViewById(R.id.line);
//        }
//    }
//}
