package lzhs.com.baseapplication.module.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lzhs.com.baseapplication.R;
import lzhs.com.baseapplication.module.menu.MenuActivity;
import lzhs.com.baseapplication.mvp.login.LoginPresenter;
import lzhs.com.baseapplication.mvp.login.LoginView;
import lzhs.com.library.base.BaseActivity;
import lzhs.com.library.utils.ActivityUtil;

/**
 * 该页面用户登陆页面<br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/25 16:58<br/>
 * 邮箱：1050629507@qq.com
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.mBtn01)
    Button mBtn01;
    @BindView(R.id.mBtn02)
    Button mBtn02;
    @BindView(R.id.mBtn03)
    Button mBtn03;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //设置沉浸式转台栏
        ActivityUtil.setStatusBarBackground(this);
    }

    @Override
    public LoginPresenter onCreatePresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getData01("https://github.com/ThirteenKilometers/GuardianLife/commits/master");
    }

    @OnClick(R.id.mBtn01)
    public void onViewClicked() {
        startActivity(new Intent(this, MenuActivity.class));
    }
}
