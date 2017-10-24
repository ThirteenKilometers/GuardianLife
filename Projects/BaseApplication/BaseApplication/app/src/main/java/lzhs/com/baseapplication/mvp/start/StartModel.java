package lzhs.com.baseapplication.mvp.start;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import lzhs.com.library.mvp.IModel;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/24 23:18<br/>
 * 邮箱：1050629507@qq.com
 */
class StartModel extends IModel {
    Observable obsevable=null;
    @Override
    protected void onCreate() {

    }

    @Override
    protected void onDestroy() {


    }

    public Observable  getDatas(String val) {
       return Observable.interval(3, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
               .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(@NonNull Long aLong) throws Exception {
                        return "欢欢，这个是延时操作哦";
                    }
                });

    }
}
