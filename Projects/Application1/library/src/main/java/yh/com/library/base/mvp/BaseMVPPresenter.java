package yh.com.library.base.mvp;

import android.app.Activity;

import java.lang.ref.Reference;

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
    protected Reference<V> mViewRef;
    Activity activity;

    public  void  attachView(V view){
        if(view instanceof  base){

        }

    }
}
