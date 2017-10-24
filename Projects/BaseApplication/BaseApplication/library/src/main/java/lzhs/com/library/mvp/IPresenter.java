package lzhs.com.library.mvp;


import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import lzhs.com.library.base.BaseActivity;


public abstract class IPresenter<V extends IView, M extends IModel> implements BaseActivity.ActivityFlow {

    final String TAG = this.getClass().getSimpleName();

    /**
     * View接口类型弱引用
     */
    protected Reference<V> mViewRef;

    protected Reference<M> mModel;

    protected IPresenter() {
        mModel = new WeakReference<M>(createModle());
        mModel.get().onCreate();
    }

    public abstract M createModle();

    /**
     * 建立关联
     * a
     *
     * @param view
     */
    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }


    /**
     * 获取View
     *
     * @return
     */
    protected V getView() {
        return isViewAttached() ? mViewRef.get() : null;
    }

    /**
     * 判断是否与View建立了关联
     *
     * @return
     */
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 解除关联
     */
    public void detachView() {
        if (isViewAttached()) {
            mViewRef.clear();
            mViewRef = null;
            mModel.get().onDestroy();
            mModel.clear();
            mModel = null;
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onRestart() {

    }


}
