package lzhs.com.moduleapplication.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

import lzhs.com.TestClass;
import lzhs.com.moduleapplication.R;
import lzhs.com.moduleapplication.activity.base.BaseActivity;
import lzhs.com.moduleapplication.databinding.ActivityHomeLayoutBinding;

/**
 * 1.创建首页所有的Fragment，以及Fragment的切换<br/>
 * 作者：LZHS<br/>
 * 时间： 2017/12/21 11:09<br/>
 * 邮箱：1050629507@qq.com
 */
public class HomeActivity extends BaseActivity {
    ActivityHomeLayoutBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_home_layout);
        TestClass.log();
        TestClass.log();
        TestClass.log();
        TestClass.log();
        TestClass.log();
        TestClass.log();


        Logger.t(TAG).d("尼玛，这个是我本地程序运行的结果哦");
    }
}
