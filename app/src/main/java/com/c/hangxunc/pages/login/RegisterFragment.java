package com.c.hangxunc.pages.login;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.c.hangxunc.R;
import com.c.hangxunc.bean.home.CountryBean;
import com.c.hangxunc.bean.login.SmsCodeBean;
import com.c.hangxunc.bean.login.RegistInfo;
import com.c.hangxunc.bean.login.SmsCodeData;
import com.c.hangxunc.http.ApiConstants;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.loading.LoadingView;
import com.c.hangxunc.mvp.BaseFragment;
import com.c.hangxunc.pages.widget.BottomView;
import com.c.hangxunc.utils.HangLog;
import com.c.hangxunc.utils.ToastUtils;
import com.c.hangxunc.web.HybridActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterFragment extends BaseFragment<RegisterPresenter> {

    private static final String TAG = RegisterFragment.class.getSimpleName();
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.email_edit)
    EditText emailEdit;
    @BindView(R.id.email_password_edit)
    EditText emailPasswordEdit;
    @BindView(R.id.email_password_re_edit)
    EditText emailPasswordReEdit;
    @BindView(R.id.email_container)
    LinearLayout emailContainer;
    @BindView(R.id.phone_country_edit)
    TextView phoneCountryEdit;
    @BindView(R.id.phone_edit)
    EditText phoneEdit;
    @BindView(R.id.sms_edit)
    EditText smsEdit;
    @BindView(R.id.sms_get)
    TextView smsGet;
    @BindView(R.id.phone_password_edit)
    EditText phonePasswordEdit;
    @BindView(R.id.phone_password_re_edit)
    EditText phonePasswordReEdit;
    @BindView(R.id.phone_container)
    LinearLayout phoneContainer;
    @BindView(R.id.book_email)
    TextView bookEmail;
    @BindView(R.id.radio_group_book)
    RadioGroup radioGroupBook;
    @BindView(R.id.checkbox)
    AppCompatCheckBox checkbox;
    @BindView(R.id.rule)
    TextView rule;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.bottom_view)
    BottomView bottomView;
    @BindView(R.id.loading)
    LoadingView loading;
    @BindView(R.id.radio_book_yes)
    RadioButton radioBookYes;
    @BindView(R.id.radio_book_no)
    RadioButton radioBookNo;
    @BindView(R.id.back)
    ImageView back;

    private RegisterPresenter mRegisterPresenter;
    private RegisterChangeListener mRegisterChangeListener;
    private CountDownTimer mTimer;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public interface RegisterChangeListener {
        void showLogin();
    }

    public void setRegisterChangeListener(RegisterChangeListener registerChangeListener) {
        this.mRegisterChangeListener = registerChangeListener;
    }

    @Override
    protected RegisterPresenter onCreateTopPresenter() {
        mRegisterPresenter = new RegisterPresenter(getActivity());
        return mRegisterPresenter;
    }

    @Override
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, null);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        phoneCountryEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCellCodeDialog();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.show_email:
                        emailContainer.setVisibility(View.VISIBLE);
                        phoneContainer.setVisibility(View.GONE);
                        break;
                    case R.id.show_phone:
                        emailContainer.setVisibility(View.GONE);
                        phoneContainer.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(getActivity());
                goNext();
            }
        });
        phoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (TextUtils.isEmpty(text)) {
                    smsGet.setEnabled(false);
                    smsGet.setBackgroundResource(R.color.mall_main_blue_text_un_enable);
                    return;
                }
                if (isMobileNO(text)) {
                    smsGet.setEnabled(true);
                    smsGet.setBackgroundResource(R.color.mall_main_blue_text);
                } else {
                    smsGet.setEnabled(false);
                    smsGet.setBackgroundResource(R.color.mall_main_blue_text_un_enable);
                }

            }
        });
        smsGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(getActivity());
                getSmsCode();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRegisterChangeListener != null) {
                    mRegisterChangeListener.showLogin();
                }
            }
        });
        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HybridActivity.openWeb(ApiConstants.RULE_PATH);
            }
        });

        mCellCodeDialog = new CellCodeDialog(getActivity());
        mCellCodeDialog.setItemClickListener(new CellCodeDialog.ItemClickListener() {
            @Override
            public void onItemClick(CountryBean cellCode) {
                mCountryBean = cellCode;
                CountrySp.getInstance().saveCountry(mCountryBean);
                if (cellCode != null) {
                    phoneCountryEdit.setText(cellCode.getName());
                }
            }
        });
    }

    private CountryBean mCountryBean;
    private CellCodeDialog mCellCodeDialog;

    private void showCellCodeDialog() {
        if (mCellCodeDialog != null) {
            mCellCodeDialog.show();
        }
    }

    private String getCellCode() {
        if (mCountryBean == null) {
            mCountryBean = CountrySp.getInstance().getCountry();
        }
        String code = "86";
        if (mCountryBean != null && !TextUtils.isEmpty(mCountryBean.getCode())) {
            code = mCountryBean.getCode();
        }
        return code;
    }

    private SmsCodeBean mSmsCodeBean;

    private void getSmsCode() {
        showLoading();
        HangXunBiz.getInstance().smsCode(getCellCode(), phoneEdit.getText().toString(),
                new ResponseListener<SmsCodeData>() {
                    @Override
                    public void onFail(int code, String message) {
                        mSmsCodeBean = null;
                        hideLoading();
                        HangLog.d(TAG, "getSmsCode onFail code:" + code + ",message:" + message);
                        ToastUtils.showToast(getActivity(), getActivity().getString(R.string.get_sms_code_fail));
                    }

                    @Override
                    public void onSuccess(SmsCodeData data) {
                        hideLoading();
                        if (data != null && data.getCode() == 0 && data.getData() != null) {
                            SmsCodeBean bean = data.getData();
                            mSmsCodeBean = bean;
                            HangLog.d(TAG, "getSmsCode onSuccess bean:" + bean.toString());
                            ToastUtils.showToast(getActivity(), getActivity().getString(R.string.get_sms_code_success));
                            startTimer();
                        } else {
                            mSmsCodeBean = null;
                            if (data == null) {
                                ToastUtils.showToast(getActivity(), getActivity().getString(R.string.get_sms_code_fail));
                            } else {
                                ToastUtils.showToast(getActivity(), data.getMsg());
                            }
                            HangLog.d(TAG, "getSmsCode onSuccess bean==null");
                        }

                    }
                });
    }


    private void startTimer() {
        smsGet.setEnabled(false);
        smsGet.setBackgroundResource(R.color.mall_main_blue_text_un_enable);
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        mTimer = new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                smsGet.setText(millisUntilFinished / 1000 + getActivity().getResources().getString(R.string.miao));
            }

            @Override
            public void onFinish() {
                smsGet.setText(getActivity().getString(R.string.sns_code_get));
                if (isMobileNO(phoneEdit.getText().toString())) {
                    smsGet.setEnabled(true);
                    smsGet.setBackgroundResource(R.color.mall_main_blue_text);
                } else {
                    smsGet.setEnabled(false);
                    smsGet.setBackgroundResource(R.color.mall_main_blue_text_un_enable);
                }
            }
        };
        mTimer.start();
    }

    private void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    public boolean isMobileNO(String mobiles) {
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，
        //"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "[1][34578]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    private void goNext() {
        boolean checked = checkbox.isChecked();
        if (!checked) {
            ToastUtils.showToast(getActivity(), getActivity().getString(R.string.check_agree_rule));
            return;
        }
        boolean isEmail = radioGroup.getCheckedRadioButtonId() == R.id.show_email;

        String type = ApiConstants.LOGIN_MOBILE_TYPE;
        String telephone = "";
        String email = "";
        String password = "";
        String rePassword = "";
        String agree = radioGroupBook.getCheckedRadioButtonId() == R.id.radio_book_yes ? "1" : "0";
        String smsCode = "";
        String newsletter = checked ? "1" : "0";
        if (isEmail) {
            type = ApiConstants.LOGIN_EMAIL_TYPE;
            email = emailEdit.getText().toString();
            password = emailPasswordEdit.getText().toString();
            if (TextUtils.isEmpty(email)) {
                ToastUtils.showToast(getActivity(), getActivity().getString(R.string.registe_emil_is_empty));
                return;
            }
            rePassword = emailPasswordReEdit.getText().toString();
        } else {
            smsCode = smsEdit.getText().toString();
            telephone = phoneEdit.getText().toString();
            password = phonePasswordEdit.getText().toString();
            if (TextUtils.isEmpty(telephone)) {
                ToastUtils.showToast(getActivity(), getActivity().getString(R.string.registe_phone_is_empty));
                return;
            }
            if (TextUtils.isEmpty(smsCode)) {
                ToastUtils.showToast(getActivity(), getActivity().getString(R.string.registe_sms_is_empty));
                return;
            }
            rePassword = phonePasswordReEdit.getText().toString();


            if (mSmsCodeBean == null) {
                ToastUtils.showToast(getActivity(), getActivity().getString(R.string.please_get_registe_sms));
                return;
            }

            String code = mSmsCodeBean.getSmsCode();
            if (!TextUtils.equals(code, smsCode)) {
                ToastUtils.showToast(getActivity(), getActivity().getString(R.string.registe_sms_error));
                return;
            }

            long currentTimeMillis = System.currentTimeMillis();
            int time = mSmsCodeBean.getTime();
            long dTime = currentTimeMillis / 1000 - time;
            if (dTime > 60 * 1000) {
                ToastUtils.showToast(getActivity(), getActivity().getString(R.string.registe_sms_time_out));
                return;
            }
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showToast(getActivity(), getActivity().getString(R.string.register_password_is_empty));
            return;
        }
        if (TextUtils.isEmpty(rePassword)) {
            ToastUtils.showToast(getActivity(), getActivity().getString(R.string.register_re_password_is_empty));
            return;
        }

        if (!TextUtils.equals(password, rePassword)) {
            ToastUtils.showToast(getActivity(), getActivity().getString(R.string.register_password_no_equal));
            return;
        }


        showLoading();


        HangXunBiz.getInstance().regist(type, email, getCellCode(),
                telephone, smsCode, password, rePassword, newsletter, agree,
                new ResponseListener<RegistInfo>() {
                    @Override
                    public void onFail(int code, String message) {
                        hideLoading();
                        handleFail("");
                    }

                    @Override
                    public void onSuccess(RegistInfo registInfo) {
                        hideLoading();
                        if (registInfo != null && registInfo.getCode() == 0) {
                            HangLog.d("onSuccess:" + registInfo.toString());
                            handleSuccess();
                        } else {
                            if (registInfo == null) {
                                handleFail("");
                            } else {
                                handleFail(registInfo.getMsg());
                            }
                        }
                    }
                });
    }

    private void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void handleSuccess() {
        clearData();

        ToastUtils.showToast(getActivity(), getActivity().getString(R.string.register_success));

        if (mRegisterChangeListener != null) {
            mRegisterChangeListener.showLogin();
        }
    }


    private void clearData() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                phoneEdit.setText("");
                phonePasswordReEdit.setText("");
                phonePasswordEdit.setText("");
                smsEdit.setText("");

                emailPasswordReEdit.setText("");
                emailPasswordEdit.setText("");
                emailEdit.setText("");
            }
        });

    }

    private void handleFail(String message) {
        if (TextUtils.isEmpty(message)) {
            ToastUtils.showToast(getActivity(), getActivity().getString(R.string.register_fail));
        } else {
            ToastUtils.showToast(getActivity(), message);
        }
    }

    private void showLoading() {
        HangLog.d(TAG, "showLoading ");
        loading.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        HangLog.d(TAG, "hideLoading ");
        loading.setVisibility(View.GONE);
    }

    @Override
    protected void onResumeImpl() {
        super.onResumeImpl();
    }

    @Override
    protected void onPauseImpl() {
        super.onPauseImpl();
        stopTimer();
    }


    @Override
    protected void onDestroyViewImpl() {
        super.onDestroyViewImpl();
        stopTimer();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!getUserVisibleHint()) {
            stopTimer();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            stopTimer();
        }
    }
}