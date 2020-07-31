package com.c.hangxunc.pages.login;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.c.hangxunc.R;
import com.c.hangxunc.bean.home.CountryBean;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.loading.LoadingView;
import com.c.hangxunc.utils.HangLog;

import java.util.List;


public class CellCodeDialog extends Dialog {

    private static final String TAG = CellCodeDialog.class.getSimpleName();
    private TextView title;
    private RecyclerView recycleView;
    private LoadingView loading;
    private LinearLayout container;
    private ItemClickListener mItemClickListener;

    public CellCodeDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CellCodeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected CellCodeDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_code_dialog_layout, null, false);
        setContentView(view);
        title = findViewById(R.id.title);
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        recycleView = findViewById(R.id.recycleView);
        loading = findViewById(R.id.loading);
        container = findViewById(R.id.container);
        getCellCode();
    }

    private void showLoading() {
        HangLog.d(TAG, "showLoading ");
        loading.setVisibility(View.VISIBLE);
        container.setVisibility(View.GONE);
    }

    private void hideLoading() {
        HangLog.d(TAG, "hideLoading ");
        loading.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);
    }

    private void getCellCode() {
        showLoading();
        HangXunBiz.getInstance().getCountry(new ResponseListener<List<CountryBean>>() {
            @Override
            public void onFail(int code, String message) {
                hideLoading();
                cancel();
            }

            @Override
            public void onSuccess(List<CountryBean> list) {
                hideLoading();
                if (list == null || list.size() == 0) {
                    cancel();
                    return;
                }
                updateCellCode(list);
            }
        });
    }

    @Override
    public void setContentView(@NonNull View view) {
        super.setContentView(view);
        setCanceledOnTouchOutside(true);
    }


    public void updateCellCode(List<CountryBean> list) {
        if (list == null || list.size() == 0) {
            cancel();
        }
        CellCodeAdapter adapter = new CellCodeAdapter(getContext(), list);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recycleView.setAdapter(adapter);
    }

    public interface ItemClickListener {
        void onItemClick(CountryBean cellCode);
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    class CellCodeAdapter extends RecyclerView.Adapter {

        private Context mContext;
        private List<CountryBean> mData;

        public CellCodeAdapter(Context mContext, List<CountryBean> posts) {
            this.mContext = mContext;
            this.mData = posts;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.list_cell_code_item, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            CountryBean bean = mData.get(position);

            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.text.setText(bean.getName());


            if (viewHolder.itemView != null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CellCodeDialog.this.cancel();
                        if (mItemClickListener != null) {
                            mItemClickListener.onItemClick(bean);
                        }
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (mData == null) {
                return 0;
            }
            return mData.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView text;
            private View line;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                text = itemView.findViewById(R.id.text);
                line = itemView.findViewById(R.id.line);
            }
        }
    }

}