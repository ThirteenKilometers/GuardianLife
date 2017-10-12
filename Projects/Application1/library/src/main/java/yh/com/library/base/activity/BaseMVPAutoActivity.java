package yh.com.library.base.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import yh.com.library.base.mvp.BaseMVPPresenter;
import yh.com.library.base.mvp.BaseMVPView;

/**
 * <br/>
 * Author： lzhs <br/>
 * DataTime： 2017/9/27  16:15<br/>
 * E-mail： 1050629507@qq.com
 */

public abstract class BaseMVPAutoActivity<V extends BaseMVPView,P extends BaseMVPPresenter> extends BaseAutoActivity{
    protected  P mPresenter;
    protected AlertDialog  mDialog=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=onCreatePresenter();
        if (mPresenter!=null)
            mPresenter.attachView((V)this);
    }

    /**
     * 创建一个Persenter实例对象
     *
     * @return
     */
    protected  abstract  P onCreatePresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
          mPresenter.detachView();
        }
    }
}
