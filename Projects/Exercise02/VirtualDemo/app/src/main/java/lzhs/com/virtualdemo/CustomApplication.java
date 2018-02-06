package lzhs.com.virtualdemo;

import android.app.Application;
import android.content.Context;

import com.didi.virtualapk.PluginManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2018/1/16 14:10<br/>
 * 邮箱：1050629507@qq.com
 */
public class CustomApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PluginManager.getInstance(base).init();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
