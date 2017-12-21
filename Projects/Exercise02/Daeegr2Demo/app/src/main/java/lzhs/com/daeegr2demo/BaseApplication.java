package lzhs.com.daeegr2demo;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2017/11/22 20:57<br/>
 * 邮箱：1050629507@qq.com
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
