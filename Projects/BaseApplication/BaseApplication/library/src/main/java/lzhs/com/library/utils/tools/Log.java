package lzhs.com.library.utils.tools;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 该类作为自定义日志打印类<br/>
 * 作者：lzhs <br/>
 * 时间： 2016/12/13 0013 12:02<br/>
 * 邮箱：1050629507@qq.com
 */
public class Log {

    public static final String TAG = Log.class.getSimpleName();

    /**
     * debug模式  将会输出日志信息
     */
    public static boolean isDebug = false;


    public static boolean init(Context context) {
        return isDebug = isApkDebugable(context);

    }

    /**
     * 但是当我们没在AndroidManifest.xml中设置其debug属性时:
     * 使用Eclipse运行这种方式打包时其debug属性为true,使用Eclipse导出这种方式打包时其debug属性为法false.
     * 在使用ant打包时，其值就取决于ant的打包参数是release还是debug.
     * 因此在AndroidMainifest.xml中最好不设置android:debuggable属性置，而是由打包方式来决定其值.
     *
     * @param context
     * @retur
     */
    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {

        }
        return false;
    }


    public static void sout(String val) {
        sout(TAG, val);
    }

    public static void sout(String tag, String val) {
        if (isDebug)
            System.out.println(tag.equals("") ? (TAG + " : " + val) : (tag + " : " + val));


    }

    public static void soutArrs(Object msg) {
        if (!isDebug)
            return;
        if (msg instanceof ArrayList) {
            sout("数组大小 : " + ((ArrayList) msg).size());
            for (int i = 0; i < ((ArrayList) msg).size(); i++) {
                sout("Key = " + i + " ; value = " + ((ArrayList) msg).get(i).toString());
            }
        } else if (msg instanceof List) {
            sout("数组大小 : " + ((List) msg).size());
            for (int i = 0; i < ((List) msg).size(); i++) {
                sout("Key = " + i + " ; value = " + ((List) msg).get(i).toString());
            }
        } else if (msg instanceof HashMap) {
            HashMap arr = ((HashMap) msg);
            sout("数组大小 : " + arr.size());
            Set set = arr.entrySet();
            for (Iterator iter = set.iterator(); iter.hasNext(); ) {
                Map.Entry entry = (Map.Entry) iter.next();
                sout("Key = " + entry.getKey().toString() + "; value = " + entry.getValue()
                        .toString());
            }
        } else if (msg instanceof SparseArray) {
            SparseArray array = (SparseArray) msg;
            sout("数组大小 : " + array.size());
            for (int i = 0; i < array.size(); i++) {
                sout("key = " + array.keyAt(i) + " ; value = " + array.valueAt(i));
            }
        } else if (msg.getClass().isArray()) {
            Object[] msgArrs = (Object[]) msg;
            sout("数组大小 : " + msgArrs.length);
            for (Object msgArr : msgArrs) {
                sout(" " + msgArr.toString());
            }
        } else if (msg instanceof ConcurrentHashMap) {
            ConcurrentHashMap<String, Object> map = (ConcurrentHashMap<String, Object>) msg;
            sout("数组大小 : " + map.size());
            for (Map.Entry<String, Object> e : map.entrySet()) {
                sout("key = " + e.getKey() + "; value = " + e.getValue());
            }
        }
    }


}
