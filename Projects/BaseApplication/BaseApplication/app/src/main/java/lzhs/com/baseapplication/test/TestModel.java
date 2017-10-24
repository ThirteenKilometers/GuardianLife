package lzhs.com.baseapplication.test;

import io.reactivex.Observable;
import lzhs.com.library.mvp.IModel;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/23 23:32<br/>
 * 邮箱：1050629507@qq.com
 */
public class TestModel extends IModel {

Observable observable=null;
    @Override
    protected void onCreate() {
        observable.subscribe();

    }


    public Observable getObservable() {
        return observable;
    }

    @Override
    protected void onDestroy() {
        ///
    }
}
