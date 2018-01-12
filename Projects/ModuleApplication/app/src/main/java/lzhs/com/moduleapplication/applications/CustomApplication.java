package lzhs.com.moduleapplication.applications;

import lzhs.com.BaseApplication;

/**
 * 1.整个程序的入口<br/>
 * 2.初始化工作<br/>
 * 3.为整个应用的其他模块提供上下文<br/>
 * 作者：LZHS<br/>
 * 时间： 2017/12/21 11:11<br/>
 * 邮箱：1050629507@qq.com
 */
public class CustomApplication extends BaseApplication {
    private static CustomApplication mInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static CustomApplication getInstance() {
        return mInstance != null ? mInstance : null;
    }
}
