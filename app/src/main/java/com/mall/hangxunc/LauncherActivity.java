package com.mall.hangxunc;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mall.hangxunc.utils.CommSharedUtil;
import com.mall.hangxunc.utils.JumpUtils;
import com.mall.hangxunc.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class LauncherActivity extends AppCompatActivity {

    private CountDownTimer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        setContentView(R.layout.activity_launcher);

        initPermission();
    }

    private static final String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private List<String> mPermissionList = new ArrayList<>();
    private final int mRequestCode = 100;


    private void initPermission() {
        mPermissionList.clear();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        if (mPermissionList.size() > 0) {
            ActivityCompat.requestPermissions(this, permissions, mRequestCode);
        }else{
            timeStart();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case mRequestCode:
                //权限请求失败
                if (grantResults.length == permissions.length) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            //弹出对话框引导用户去设置
                            ToastUtils.showToast(LauncherActivity.this, "请求权限被拒绝");
                            break;
                        }
                    }
                } else {
                    ToastUtils.showToast(LauncherActivity.this, "已授权");

                }
                break;
        }
        timeStart();
    }

    //弹出提示框
//    private void showDialog() {
//        AlertDialog dialog = new AlertDialog.Builder(this)
//                .setMessage("录像需要相机、录音和读写权限，是否去设置？")
//                .setPositiveButton("是", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        goToAppSetting();
//                    }
//                })
//                .setNegativeButton("否", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
//                .setCancelable(false)
//                .show();
//    }

    // 跳转到当前应用的设置界面
    private void goToAppSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    private void timeStart() {
        mTimer = new CountDownTimer(1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                boolean isFirst = CommSharedUtil.getInstance(LauncherActivity.this).getBoolean(CommSharedUtil.FIRST_ENTER_KEY, true);
                if (isFirst) {
                    JumpUtils.goGuideActivity(LauncherActivity.this);
                    CommSharedUtil.getInstance(LauncherActivity.this).putBoolean(CommSharedUtil.FIRST_ENTER_KEY, false);
                } else {
                    JumpUtils.goMain(LauncherActivity.this);
                }
                finish();
            }
        };
        mTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}
