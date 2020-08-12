//package com.c.hangxunc;
//
//import android.os.Bundle;
//import android.view.View;
//
//import com.c.hangxunc.change.ChangeLanguageActivity;
//
//public class MainActivity extends BaseActivity {
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//    public void onClick(View view) {
//        String language = null;
//
//        switch (view.getId()) {
//            case R.id.btn_change_language:
//                ChangeLanguageActivity.launch(this, ChangeLanguageActivity.LANGUAGE_FLAG);
//                break;
//        }
//    }
//
//    /**
//     * 如果是7.0以下，我们需要调用changeAppLanguage方法，
//     * 如果是7.0及以上系统，直接把我们想要切换的语言类型保存在SharedPreferences中即可
//     * 然后重新启动MainActivity
//     *
//     * @param language
//     */
////    private void changeLanguage(String language) {
////        if (TextUtils.isEmpty(language)) {
////            return;
////        }
////        LanguageSp.getInstance().saveLanguageList(language);
////
////        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
////            LanguageUtil.changeLanguageAndKill(HandXunApplication.getInstance(), language);
////        }
////
////    }
//}
