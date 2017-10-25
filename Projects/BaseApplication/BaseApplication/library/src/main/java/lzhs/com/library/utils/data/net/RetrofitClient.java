package lzhs.com.library.utils.data.net;


import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lzhs.com.library.utils.tools.Log;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/25 14:05<br/>
 * 邮箱：1050629507@qq.com
 */
public class RetrofitClient {

    private static final int DEFAULT_TIMEOUT = 5;


    private OkHttpClient okHttpClient;

    public static String baseUrl = BaseApiService.Base_URL;

    private static volatile RetrofitClient sNewInstance = null;

    public static Retrofit mRetrofit = null;


    public static RetrofitClient getInstance() {
        return getInstance("");
    }


    public static RetrofitClient getInstance(String url) {
        if (sNewInstance == null)
            synchronized (RetrofitClient.class) {
                if (sNewInstance == null)
                    sNewInstance = new RetrofitClient(url);
            }
        return sNewInstance;
    }


    private RetrofitClient(String url) {
        if (!TextUtils.isEmpty(url))
            baseUrl = url;
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(
                        new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                            @Override
                            public void log(String message) {
                                Log.sout(message);
                            }
                        }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    /**
     * 该方法创建了一个Retrofit 生产出来的APIServers实例
     */
    public <T> T create(@NonNull final Class<T> service) {
        if (baseUrl.equals(""))
            throw new RuntimeException("请求主机地址为空");
        return mRetrofit.create(service);
    }


    /**
     * 该方法提供通用Get请求
     */
    public  Observable<ResponseBody> get() {
        return get("", new HashMap(), new HashMap());
    }

    /**
     * 该方法提供通用Get请求
     *
     * @param url 请求路径
     */
    public  Observable<ResponseBody> get(String url) {
        return get(url, new HashMap(), new HashMap());
    }

    /**
     * 该方法提供通用Get请求
     *
     * @param url     请求路径
     * @param headers 请求参数
     */
    public  Observable<ResponseBody> get(String url, Map headers) {
        return get(url, headers, new HashMap());
    }


    /**
     * 该方法提供通用Get请求
     *
     * @param url        请求路径
     * @param headers    请求参数
     * @param parameters 请求头参数
     */
    public  Observable<ResponseBody> get(String url, Map headers, Map parameters) {
        Observable<ResponseBody> observable = mRetrofit.create(BaseApiService.class).executeGet(url, headers, parameters)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    /**
     * 该方法实现了通用POST请求
     *
     * @param url        请求路径
     * @param headers    请求参数
     * @param parameters 请求头参数
     * @param observer   RX被观察者对象
     */
    public void post(String url, Map headers, Map parameters, Observer<ResponseBody> observer) {
        mRetrofit.create(BaseApiService.class).executePost(url, headers, parameters)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

