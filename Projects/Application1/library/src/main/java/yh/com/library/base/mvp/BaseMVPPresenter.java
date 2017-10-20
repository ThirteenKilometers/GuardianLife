package yh.com.library.base.mvp;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import yh.com.library.Config;
import yh.com.library.base.activity.BaseAutoActivity;
import yh.com.library.base.fragment_v4.BaseFragment;
import yh.com.library.wedgit.net.ExceptionHandle;

/**
 * <br/>
 * Author： yh <br/>
 * DataTime： 2017/9/27  16:19<br/>
 * E-mail： 1050629507@qq.com
 * MVP有很多优点，例如易于维护，易于测试，松耦合，复用性高，健壮稳定
 * ，易于扩展，那么当我们在未完成耗时操作时关闭了Activity,会导致Presenter一直持有Activity
 * 对象，造成内存泄露，那么如何去解决这个问题呢，我们只需要在Activity
 * 或者Fragment关闭的时候将Presenter的引用释放掉就可以，但是如果所有
 * 界面都去操作那样就变得很复杂，所以我们在BaseActivity，或者BaseFragment
 * 中去操作
 */

public abstract class BaseMVPPresenter <V extends BaseMVPView>{
    /**
     * View接口类型弱引用
     */
    protected Reference<V> mViewRef;
    Activity activity;

    /**
     * 建立关联
     * @param view
     */
    public  void  attachView(V view){
        if (view instanceof  BaseFragment)
            this.activity=((BaseFragment) view).getActivity();
        else  if (view instanceof BaseAutoActivity)
            this.activity=(BaseAutoActivity)view;
        mViewRef=new WeakReference<V>(view);
    }

    /**
     * 获取View
     * @return
     */
    protected  V getView() {
        return isViewAttached() ? mViewRef.get() : null;
    }
    /**
     * 判断是否与View建立了关联
     * @return
     */
    public  boolean isViewAttached(){
        return  mViewRef!=null&&mViewRef.get()!=null;
    }
    /**
     * 解除关联
     */
    public  void  detachView(){
        if (mViewRef!=null){
            mViewRef.clear();
            mViewRef=null;
        }
    }
    /**
     * 成功
     */
    public<T>void onSuccess(T data){}

    /**
     * 失败
     * @param err
     */
    public void onFailed(ExceptionHandle.ResponeThrowable err) {
        switch (err.code) {
            case ExceptionHandle.ResponeThrowable.ERROR_COMMON:
                //通用异常
                break;
            case ExceptionHandle.ResponeThrowable.ERROR_DATA_NULL:
                // 数据为空的异常
                err.message = "";
                break;
            case ExceptionHandle.ResponeThrowable.ERROR_TOKEN_PAST_DUE:
                //token 过期
                err.message = "登录超时";
                activity.startActivity(new Intent(Config.IntentFilter.LOGINACTIVITY));
                if (activity instanceof BaseAutoActivity) {
                    if (TextUtils.equals(((BaseAutoActivity) activity).TAG, "MenuActivity")) {
                        break;
                    }
                }
                activity.finish();
                break;

        }

    }
}
