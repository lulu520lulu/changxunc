package com.c.hangxunc.pages.street;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.c.hangxunc.BaseActivity;
import com.c.hangxunc.R;
import com.c.hangxunc.bean.home.PostsBean;
import com.c.hangxunc.pages.street.adapter.StreetAllPostRecycleAdapter;
import com.c.hangxunc.utils.JumpUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StreetAllPostActivity extends BaseActivity {

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
        setContentView(R.layout.street_activity_all_post);
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
        StreetAllPostRecycleAdapter adapter = new StreetAllPostRecycleAdapter(this, list);
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
