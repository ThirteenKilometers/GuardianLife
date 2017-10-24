package lzhs.com.baseapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;

import lzhs.com.baseapplication.test.TestPresenter;
import lzhs.com.baseapplication.test.TestView;
import lzhs.com.library.base.BaseActivity;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/23 22:57<br/>
 * 邮箱：1050629507@qq.com
 */
public class TestActivity extends BaseActivity <TestPresenter>  implements TestView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ///.......

    }

    @Override
    public TestPresenter onCreatePresenter() {
        return new TestPresenter();
    }

    @Override
    public void setText(String content) {
        /////
    }
}
