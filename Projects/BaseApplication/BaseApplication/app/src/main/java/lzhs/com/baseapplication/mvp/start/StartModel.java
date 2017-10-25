package lzhs.com.baseapplication.mvp.start;

import io.reactivex.Observer;
import lzhs.com.library.mvp.IModel;
import lzhs.com.library.utils.data.net.RetrofitClient;
import okhttp3.ResponseBody;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/24 23:18<br/>
 * 邮箱：1050629507@qq.com
 */
class StartModel extends IModel {


    @Override
    protected void onCreate() {

    }

    @Override
    protected void onDestroy() {


    }

    public void  getDatas(String val, Observer<ResponseBody> observer) {
        RetrofitClient.getInstance().get().subscribe(observer);
    }
}
