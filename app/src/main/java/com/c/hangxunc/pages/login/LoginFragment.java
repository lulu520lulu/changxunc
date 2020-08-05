package com.c.hangxunc.pages.login;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.c.hangxunc.R;
import com.c.hangxunc.bean.home.CountryBean;
import com.c.hangxunc.bean.login.LoginInfo;
import com.c.hangxunc.http.HangXunBiz;
import com.c.hangxunc.http.ResponseListener;
import com.c.hangxunc.loading.LoadingView;
import com.c.hangxunc.mvp.BaseFragment;
import com.c.hangxunc.pages.home.ui.MessageHome;
import com.c.hangxunc.pages.widget.BottomView;
import com.c.hangxunc.utils.HangLog;
import com.c.hangxunc.utils.LoginUtils;
import com.c.hangxunc.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends BaseFragment<LoginPresenter> {

    private static final String TAG = LoginFragment.class.getSimpleName();
    //    @BindView(R.id.title_text)
//    TextView titleText;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.email_edit)
    EditText emailEdit;
    @BindView(R.id.email_password_edit)
    EditText emailPasswordEdit;
    @BindView(R.id.email_container)
    LinearLayout emailContainer;
    @BindView(R.id.phone_country_edit)
    TextView phoneCountryEdit;
    @BindView(R.id.phone_edit)
    EditText phoneEdit;
    @BindView(R.id.phone_password_edit)
    EditText phonePasswordEdit;
    @BindView(R.id.phone_container)
    LinearLayout phoneContainer;
    @BindView(R.id.start_login)
    TextView startLogin;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.forget_password)
    TextView forgetPassword;
    @BindView(R.id.bottom_view)
    BottomView bottomView;
    @BindView(R.id.loading)
    LoadingView loading;
    @BindView(R.id.show_email)
    RadioButton showEmail;
    @BindView(R.id.show_phone)
    RadioButton showPhone;
    private LoginPresenter mLoginPresenter;


    @Override
    protected LoginPresenter onCreateTopPresenter() {
        mLoginPresenter = new LoginPresenter(getActivity());
        return mLoginPresenter;
    }

    public interface LoginChangeListener {
        void showRegister();

        void showForget();

        void showPersion(String customId);
    }

    private LoginChangeListener mLoginChangeListener;

    public void setLoginChangeListener(LoginChangeListener loginChangeListener) {
        this.mLoginChangeListener = loginChangeListener;
    }

    private void goRegister() {
        hideSoftKeyboard(getActivity());
        if (mLoginChangeListener != null) {
            mLoginChangeListener.showRegister();
        }
    }

    private void goForget() {
        hideSoftKeyboard(getActivity());
        if (mLoginChangeListener != null) {
            mLoginChangeListener.showForget();
        }
    }

    private void goLogin(boolean isEmail) {
        String type = "mobile";
        String telephone = "";
        String email = "";
        String password = "";

        if (isEmail) {
            type = "email";
            email = emailEdit.getText().toString();
            password = emailPasswordEdit.getText().toString();
            if (TextUtils.isEmpty(email)) {
                ToastUtils.showToast(getActivity(), getActivity().getString(R.string.emil_edit_is_empty));
                return;
            }
        } else {
            telephone = phoneEdit.getText().toString();
            password = phonePasswordEdit.getText().toString();
            if (TextUtils.isEmpty(telephone)) {
                ToastUtils.showToast(getActivity(), getActivity().getString(R.string.emil_phone_is_empty));
                return;
            }
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showToast(getActivity(), getActivity().getString(R.string.emil_password_is_empty));
            return;
        }
        showLoading();
        if (mCountryBean == null) {
            mCountryBean = CountrySp.getInstance().getCountry();
        }
        HangXunBiz.getInstance().login(type, mCountryBean == null ? "86" : mCountryBean.getCode(), telephone, email, password,
                new ResponseListener<LoginInfo>() {
                    @Override
                    public void onFail(int code, String message) {
                        hideLoading();
                        handleFail();
                    }

                    @Override
                    public void onSuccess(LoginInfo info) {
                        hideLoading();
                        if (info == null) {
                            handleFail();
                            return;
                        }
                        ToastUtils.showToast(getActivity(), getActivity().getString(R.string.login_success));
                        LoginUtils.getInstance().setLoginInfo(info.getSession_id(), info.getCustomer_id());
                        EventBus.getDefault().post(MessageLogin.getInstance(MessageLogin.LOGIN_IN));
                        if (mLoginChangeListener != null) {
                            mLoginChangeListener.showPersion(info.getCustomer_id());
                        }
                    }
                });
    }

    private void handleFail() {
        ToastUtils.showToast(getActivity(), getActivity().getString(R.string.login_fail));
    }

    private void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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
    protected View onCreateViewImpl(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, null);
        ButterKnife.bind(this, view);
        initView(view);
        initData();
        return view;
    }

    private void initData() {

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
                        showEmail.setBackgroundResource(R.drawable.login_radio_checked_bg);
                        showPhone.setBackgroundResource(android.R.color.transparent);
                        break;
                    case R.id.show_phone:
                        showEmail.setBackgroundResource(android.R.color.transparent);
                        showPhone.setBackgroundResource(R.drawable.login_radio_checked_bg);
                        emailContainer.setVisibility(View.GONE);
                        phoneContainer.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        startLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(getActivity());
                goLogin(radioGroup.getCheckedRadioButtonId() == R.id.show_email);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRegister();
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goForget();
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

    @Override
    protected void onResumeImpl() {
        super.onResumeImpl();
    }

    @Override
    protected void onPauseImpl() {
        super.onPauseImpl();
    }


    @Override
    protected void onDestroyViewImpl() {
        super.onDestroyViewImpl();
        if (mCellCodeDialog != null) {
            mCellCodeDialog.cancel();
        }
    }


}