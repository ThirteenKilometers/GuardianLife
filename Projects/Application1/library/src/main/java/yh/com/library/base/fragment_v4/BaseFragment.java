package yh.com.library.base.fragment_v4;

import android.app.AlertDialog;
import android.app.Fragment;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import yh.com.library.base.mvp.BaseMVPPresenter;
import yh.com.library.base.mvp.BaseMVPView;

/**
 * <br/>
 * Author： yh <br/>
 * DataTime： 2017/9/29  16:17<br/>
 * E-mail： 1050629507@qq.com
 */

public abstract class BaseFragment <V extends BaseMVPView,P extends BaseMVPPresenter> extends Fragment {
    protected View viewPara=null;
    protected  P mPresenter;
    protected AlertDialog mDialog=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=onCreatePresenter();
        if (mPresenter!=null)
           mPresenter.attachView((V)this);
    }

    /**创建一个Presenter实例对象
     *
     * @return
     */
    protected  P onCreatePresenter(){
        return  null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
            mPresenter.detachView();
    }
}
