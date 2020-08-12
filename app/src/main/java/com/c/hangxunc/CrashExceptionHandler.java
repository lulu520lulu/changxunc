package com.c.hangxunc;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CrashExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Context mContext;
    private static CrashExceptionHandler instance;
    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    public CrashExceptionHandler(Context mContext) {
        this.mContext = mContext;
        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        try {
            handlerException(t, e);
        } catch (Throwable throwable) {
            if (mUncaughtExceptionHandler != null) {
                mUncaughtExceptionHandler.uncaughtException(t, e);
            }
        }
    }

    private void handlerException(Thread thread, Throwable throwable) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("fatal exception:")
                .append(Thread.currentThread().getName())
                .append("\r\n");
        if (mContext != null) {
            stringBuffer.append("process:")
                    .append(mContext.getApplicationContext().getPackageName())
                    .append("\r\n");

        }
        stringBuffer.append(throwable.getClass().getName()).append("\r\n");
        stringBuffer.append(throwable.getMessage()).append("\r\n");
        stringBuffer.append(Log.getStackTraceString(throwable)).append("\r\n");
        log(stringBuffer.toString());
        exit(thread, throwable);
    }

    private void log(String bodyMsg) {
        if (bodyMsg.length() > 4000) {
            for (int i = 0; i < bodyMsg.length(); i += 4000) {
                //当前截取的长度<总长度则继续截取最大的长度来打印
                if (i + 4000 < bodyMsg.length()) {
                    Log.e("fatal:" + i, bodyMsg.substring(i, i + 4000));
                } else {
                    //当前截取的长度已经超过了总长度，则打印出剩下的全部信息
                    Log.e("fatal:" + i, bodyMsg.substring(i, bodyMsg.length()));
                }
            }
        } else {
            //直接打印
            Log.e("fatal:", bodyMsg);
        }


        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "hangxun");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyy-MM-dd-HH-mm-ss", Locale.ENGLISH);
        String fileName = format.format(new Date());
        String file = dir.getAbsoluteFile() + "/fatal-2c-" + fileName + ".log";

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(bodyMsg.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void init(Context context) {
        if (instance == null) {
            instance = new CrashExceptionHandler(context);
        }
    }

    private void exit(Thread thread, Throwable throwable) {
        if (mUncaughtExceptionHandler == null) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        } else {
            mUncaughtExceptionHandler.uncaughtException(thread, throwable);
        }
    }

}
