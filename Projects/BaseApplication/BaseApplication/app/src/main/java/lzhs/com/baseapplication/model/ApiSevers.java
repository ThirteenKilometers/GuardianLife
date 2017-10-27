package lzhs.com.baseapplication.model;

import io.reactivex.Observable;
import lzhs.com.library.utils.data.net.BaseApiService;
import okhttp3.ResponseBody;

/**
 * 该接口见存放所有的网络请求接口<br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/25 18:41<br/>
 * 邮箱：1050629507@qq.com
 */
public interface ApiSevers extends BaseApiService {

    interface  TestInterface{

        Observable<ResponseBody> test();
    }

}
