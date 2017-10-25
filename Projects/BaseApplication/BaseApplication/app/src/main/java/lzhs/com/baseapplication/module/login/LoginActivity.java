package lzhs.com.baseapplication.module.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import lzhs.com.baseapplication.R;
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
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //设置沉浸式转台栏
        ActivityUtil.setStatusBarBackground(this);
    }

    @Override
    public LoginPresenter onCreatePresenter() {
        return new LoginPresenter();
    }
}
