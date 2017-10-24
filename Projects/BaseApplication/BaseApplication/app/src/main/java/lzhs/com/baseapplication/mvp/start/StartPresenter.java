package lzhs.com.baseapplication.mvp.start;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import lzhs.com.library.mvp.IPresenter;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/24 23:17<br/>
 * 邮箱：1050629507@qq.com
 */
public class StartPresenter extends IPresenter <StartView,StartModel>{

    @Override
    public StartModel createModle() {
        return new StartModel();
    }

    public void getDatas(String val) {
        Observable observable=mModel.get().getDatas(val);
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                mViewRef.get().setDatas(s);
            }
        });


    }



}
