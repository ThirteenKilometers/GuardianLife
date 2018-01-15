package lzhs.com.virtualdemo;

import android.app.Application;
import android.content.Context;

import com.didi.virtualapk.PluginManager;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2018/1/15 16:53<br/>
 * 邮箱：1050629507@qq.com
 */
public class BaseApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PluginManager .getInstance(base).init();
    }
}
