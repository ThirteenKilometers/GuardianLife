package yh.com.library.base.mvp;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import yh.com.library.wedgit.net.WrapperRetrofit;

/**
 * <br/>
 * Author： yh <br/>
 * DataTime： 2017/9/27  16:18<br/>
 * E-mail： 1050629507@qq.com
 */

public  abstract  class BaseMVPModel<P extends  BaseMVPPresenter> {
    private  static  final  String TOKEN="token";
    protected  P mPresenter;
    protected WrapperRetrofit retrofit=null;

    public BaseMVPModel(P mPresenter) {
        this.mPresenter = mPresenter;
        retrofit=WrapperRetrofit.getInstance();
    }
    /**
     * @param observable  发射源  被观察者
     * @param observer    接受源  观察者
     *        Subscriber  订阅者  与Observer的区别：
     *                    Subscriber实现了observer的接口，比observer多了一个
     *                    重要的方法unsubscribe()用于取消订阅，，Observer 在
     *                    subscribe() 过程中,最终也会被转换成 Subscriber 对象，
     *                    一般情况下，建议使用Subscriber作为接收源；
     */
    protected void subscribe(Observable observable, Observer observer) {
        observable
                .subscribeOn(Schedulers.io())//指定subscribe()发生在IO线程
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//指定 Subscriber即订阅者 的回调发生在主线程
                .subscribe(observer);//订阅————>将观察者和被观察者联系起来
    }
}
