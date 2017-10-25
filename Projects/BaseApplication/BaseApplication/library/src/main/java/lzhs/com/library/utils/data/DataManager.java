package lzhs.com.library.utils.data;

import android.content.Context;
import android.support.annotation.NonNull;

import lzhs.com.library.utils.data.net.RetrofitClient;

/**
 *  该类为用户提供Data管理类，所有数据获取将进过该类<br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/25 13:42<br/>
 * 邮箱：1050629507@qq.com
 */
public class DataManager {

    public static RetrofitClient getRetrofitClient(@NonNull Context mContext){
        return RetrofitClient.getInstance("");
    }
    public static RetrofitClient getRetrofitClient(@NonNull Context mContext, String hostUrl){
        return RetrofitClient.getInstance(hostUrl);
    }



}
