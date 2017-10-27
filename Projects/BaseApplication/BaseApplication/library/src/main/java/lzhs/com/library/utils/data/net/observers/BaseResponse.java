package lzhs.com.library.utils.data.net.observers;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import lzhs.com.library.utils.tools.Log;
import okhttp3.ResponseBody;

/**
 * 该类将会将使用Retrofit 中的 ResponseBody 这时我们可以得到完整的Json  数据，<br/>
 * 使用 实例  {@code Observable<ResponseBody> getXXX()}
 * 作者：LZHS<br/>
 * 时间： 2017/5/8 0008 16:53<br/>
 * 邮箱：1050629507@qq.com
 */

public abstract class BaseResponse implements Observer<ResponseBody> {

    public Disposable mDisposable;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        this.mDisposable = d;
    }

    @Override
    public void onNext(@NonNull ResponseBody body) {
        if (body != null)
            try {
                onSuccess(body.string());
            } catch (Exception e) {
                onError(e);
            }
        else onError(new NullPointerException("请求体返回为空"));
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Log.sout(e.getMessage());
        if (mDisposable != null)
            mDisposable.dispose();
    }

    @Override
    public void onComplete() {
        if (mDisposable != null)
            mDisposable.dispose();
    }

    public void dispose(){
        if (mDisposable != null&&mDisposable.isDisposed()) mDisposable.dispose();
    }
    /**
     * 该方法成功的时候调用
     *
     * @param json
     */
    protected abstract void onSuccess(String json);

    /**
     * 该方法将在请求失败的调用
     *
     * @param err
     */
    protected abstract void onFaild(String err);

}
