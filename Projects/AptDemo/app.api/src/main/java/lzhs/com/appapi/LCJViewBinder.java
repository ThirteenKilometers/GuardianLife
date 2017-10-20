package lzhs.com.appapi;

import android.app.Activity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 注解框架向外提供绑定方法，这里使用静态类来管理 <br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/20 16:22<br/>
 * 邮箱：1050629507@qq.com
 */
public class LCJViewBinder {
    private static final ActivityViewFinder activity_view_finder = new ActivityViewFinder();//默认生命一个Activity view查找器
    private static final Map<String, ViewBinder> binder_map = new LinkedHashMap<>();//管理保持管理者Map集合

    /**
     * 注解绑定ActivityViewFinder
     *
     * @param activity
     */
    public static void bind(Activity activity) {
        bind(activity, activity, activity_view_finder);
    }

    /**
     * 注解绑定
     *
     * @param host   表示注解View变量所在的类，也就是注解类
     * @param object 表示查找View的地方，Activity & View自身就可以查找，Fragment需要在自己的ItemView中查找
     * @param finder UI绑定提供者
     */
    public static void bind(Object host, Object object, ViewFinder finder) {
        String className = host.getClass().getName();

        try {
            ViewBinder binder=binder_map.get(className);
            if (binder==null) {
                Class<?> aClass=Class.forName(className+"$$ViewBinder");
                binder=(ViewBinder)aClass.newInstance();
                binder_map.put(className,binder);
            }
            if (binder!=null) {
                binder.bindView(host, object, finder);
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接触注解绑定 ActivityView Finder
     * @param host
     */
    public static void  unBind(Object host){
        String className=host.getClass().getName();
        ViewBinder binder=binder_map.get(className);
        if (binder!=null)
            binder.unBindView(host);
        binder_map.remove(className);
    }
}
