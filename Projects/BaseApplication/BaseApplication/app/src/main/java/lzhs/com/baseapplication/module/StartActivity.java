package lzhs.com.baseapplication.module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import lzhs.com.baseapplication.R;
import lzhs.com.baseapplication.mvp.start.StartPresenter;
import lzhs.com.baseapplication.mvp.start.StartView;
import lzhs.com.library.base.BaseActivity;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/24 23:07<br/>
 * 邮箱：1050629507@qq.com
 */
public class StartActivity extends BaseActivity<StartPresenter>  implements StartView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        mPresenter.getDatas("请求数据");

    }


    @Override
    public StartPresenter onCreatePresenter() {
        return new StartPresenter();
    }

    @Override
    public void setDatas(String val) {
        ((TextView)findViewById(R.id.mTextShow)).setText(val);
    }
}
