package com.example.crazyshopping.ui.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crazyshopping.R;
import com.example.crazyshopping.base.BaseActivity;
import com.example.crazyshopping.bean.UserLogBean;
import com.example.crazyshopping.interfaces.home.my.IKuangOwn;
import com.example.crazyshopping.persenter.my.KuangOwnPersenter;
import com.example.crazyshopping.utils.SpUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<IKuangOwn.Persenter> implements IKuangOwn.View {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_zhu)
    TextView tvZhu;
    @BindView(R.id.tv_deng)
    TextView tvDeng;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_yan)
    EditText etYan;
    @BindView(R.id.eye)
    ImageView eye;
    @BindView(R.id.ll)
    RelativeLayout ll;
    @BindView(R.id.tv_mi)
    TextView tvMi;
    @BindView(R.id.btn_login)
    Button btnLogin;
    String name;
    String paw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected IKuangOwn.Persenter initPersenter() {
        return new KuangOwnPersenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void getUserLog(UserLogBean userBean) {
        if (userBean.getData().getCode() == 200) {
            Toast.makeText(this, "登录成功!", Toast.LENGTH_SHORT).show();
            SpUtils.getInstance().setValue("token", userBean.getData().getToken());
            SpUtils.getInstance().setValue("username", userBean.getData().getUserInfo().getUsername());
            SpUtils.getInstance().setValue("avater", userBean.getData().getUserInfo().getAvatar());
            finish();
        } else {
            Toast.makeText(this, userBean.getData().getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.tv_zhu, R.id.eye, R.id.btn_login,R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_zhu:
                Intent intent = new Intent(LoginActivity.this, ZhuCeMainActivity.class);
                startActivity(intent);
                break;
            case R.id.eye:
                if (etYan.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {  //不可见
                    eye.setImageResource(R.mipmap.yer);
                    etYan.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                } else {  //可见
                    etYan.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    eye.setImageResource(R.mipmap.yer);
                }

                break;
            case R.id.btn_login:

                posta();

                break;
            case R.id.iv_back:

                finish();

                break;
        }
    }

    private void posta() {
        name = etPhone.getText().toString().trim();
        paw = etYan.getText().toString().trim();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(paw)) {
            if (paw.length() >= 6) {
                Map<String, String> map = new HashMap<>();
                map.put("username", name);
                map.put("password", paw);
                persenter.getUserLog(map);
            } else {
                Toast.makeText(LoginActivity.this, "大于六", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getDengLu(UserLogBean userBean) {

    }

    @Override
    public void showTips(String tips) {

    }

    @Override
    public void showLoading(int visible) {

    }
}