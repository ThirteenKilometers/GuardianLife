package yh.com.library.wedgit.net.interceptor;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * 添加请求头差值器
 * <br/>
 * Author： yh <br/>
 * DataTime： 2017/9/28  10:32<br/>
 * E-mail： 1050629507@qq.com
 */

public class HeadInterceptor implements Interceptor {
    private Map<String ,String> heads=new HashMap<>();

    public HeadInterceptor(Map<String, String> heads) {
        this.heads = heads;
    }
    public HeadInterceptor(String key,String val) {
        heads.put(key,val);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original=chain.request();
        Request.Builder builder=original.newBuilder()
        .method(original.method(),original.body());
        if (heads.size()>0){
            Iterator it=heads.entrySet().iterator();
            while (it.hasNext()){
               Map.Entry<String,String> entry= (Map.Entry<String, String>) it.next();
                builder.removeHeader(entry.getKey())
                        .addHeader(entry.getKey(),entry.getValue());

                yh.com.library.utils.tools.Log.sout("请求头参数 key --->"+entry.getKey()+"val  --->"+entry.getValue());

            }
        }
        return chain.proceed(builder.build());
    }
}
