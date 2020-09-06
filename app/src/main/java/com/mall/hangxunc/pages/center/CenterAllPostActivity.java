package com.mall.hangxunc.pages.center;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mall.hangxunc.BaseActivity;
import com.mall.hangxunc.R;
import com.mall.hangxunc.bean.home.PostsBean;
import com.mall.hangxunc.pages.center.adapter.CenterAllPostRecycleAdapter;
import com.mall.hangxunc.utils.JumpUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CenterAllPostActivity extends BaseActivity {

    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.content_title_text)
    TextView contentTitleText;
    @BindView(R.id.search_result)
    RecyclerView recyclerView;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.center_activity_all_post);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        ArrayList<PostsBean> list = intent.getParcelableArrayListExtra(JumpUtils.SEE_MORE_POST_DATA);
        if (list == null || list.size() == 0) {
            finish();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        CenterAllPostRecycleAdapter adapter = new CenterAllPostRecycleAdapter(this, list);
        recyclerView.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
