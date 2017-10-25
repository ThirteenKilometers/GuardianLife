package lzhs.com.baseapplication;

import android.app.Application;

import lzhs.com.library.utils.tools.Log;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/25 13:07<br/>
 * 邮箱：1050629507@qq.com
 */
public class APPApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        Log.init(this);
    }
}
