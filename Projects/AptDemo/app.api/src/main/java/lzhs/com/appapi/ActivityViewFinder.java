package lzhs.com.appapi;

import android.app.Activity;
import android.view.View;

/**
 * Activity UI 查找提供者<br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/20 16:20<br/>
 * 邮箱：1050629507@qq.com
 */
public class ActivityViewFinder implements ViewFinder {
    @Override
    public View findView(Object object, int id) {
        return ((Activity)object).findViewById(id);
    }
}
