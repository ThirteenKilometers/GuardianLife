package yh.com.library.wedgit.net;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import yh.com.library.wedgit.net.interceptor.HeadInterceptor;

/**
 * <br/>
 * Author： yh <br/>
 * DataTime： 2017/9/27  16:31<br/>
 * E-mail： 1050629507@qq.com
 */

public class WrapperRetrofit {
    /**
     * 请求超时时间
     */
    private static final int DEFAULT_TIMEOUT = 30;
    /**
     * 请求地址的主机地址
     */
    private static String baseHost = "";
    private static WrapperRetrofit mInstance = null;
    OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
    Retrofit.Builder builder = new Retrofit.Builder();

    private WrapperRetrofit() {
        okBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        yh.com.library.utils.tools.Log.sout("net",message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY));
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }

    public static WrapperRetrofit getInstance() {
        if (mInstance == null)
            synchronized (WrapperRetrofit.class) {
                if (mInstance == null)
                    mInstance = new WrapperRetrofit();
            }
        return mInstance;
    }

    public WrapperRetrofit setHost(String baseHost) {
        this.baseHost = baseHost;
        return mInstance;
    }

    /**
     * 添加请求头差值器
     *
     * @param headInterceptor
     * @return
     */
    public WrapperRetrofit addHeadInterceptor(Interceptor headInterceptor) {
        if (mInstance == null)
            throw new NullPointerException("WrapperRetrofit 为空");
        okBuilder.addInterceptor(headInterceptor);
        return mInstance;
    }
    /**
     * 添加 请求头 Token  差值器

     * @param token
     * @return
     */
    public  WrapperRetrofit addHeadInterceptor(String token){
    if (mInstance==null)
        throw  new NullPointerException("WrapperRetrofit 为空");
       okBuilder.addInterceptor(new HeadInterceptor("token",token));
        return  mInstance;
    }
    /**
     * 该方法创建了一个 Retrofit 生产出来的APIServers实例
     */
    public<T> T create(final Class<T>service){
        if (service==null)
            throw new  NullPointerException("实例化的Service不能为空");
        if (baseHost.equals(""))
            throw  new  RuntimeException("请求主机地址为空");
        return  builder
                .baseUrl(baseHost)
                .client(okBuilder.build())//设置OKHttpClient,如果不设置会一个默认的提供client
                .build()
                .create(service);
    }




}
