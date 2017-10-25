package lzhs.com.library.base;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhy.autolayout.AutoLayoutActivity;

import lzhs.com.library.mvp.IPresenter;
import lzhs.com.library.mvp.IView;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/23 22:26<br/>
 * 邮箱：1050629507@qq.com
 */
public abstract class BaseActivity<P extends IPresenter> extends AutoLayoutActivity {

    final String TAG = this.getClass().getSimpleName();

    protected P mPresenter = null;

    protected AlertDialog mDialog=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this instanceof IView)
            mPresenter = onCreatePresenter();
        if (mPresenter != null) {
            mPresenter.attachView((IView) this);
            mPresenter.onCreate();
        }
    }

    public P onCreatePresenter() {
        return mPresenter;
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null)
            mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null)
            mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null)
            mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null)
            mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.onDestroy();
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (mPresenter != null)
            mPresenter.onRestart();
    }

    /**
     * <br/>
     * 作者：LZHS<br/>
     * 时间： 2017/10/23 22:32<br/>
     * 邮箱：1050629507@qq.com
     */
    public static interface ActivityFlow {

        void onCreate();

        void onStart();

        void onResume();


        void onPause();

        void onStop();

        void onDestroy();

        void onRestart();
    }
}
