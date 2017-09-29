package yh.com.library.utils.tools;

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
 * 自定义日志打印类
 * <br/>
 * Author： yh <br/>
 * DataTime： 2017/9/28  11:24<br/>
 * E-mail： 1050629507@qq.com
 */

public class Log {
    public  static  final  String TAG=Log.class.getSimpleName();
    /**
     * debug模式 将会输出日志
     */
    public  static  boolean isDebug=false;
    public  static  boolean init(Context context){
        return isDebug=isApkDebugable(context);
    }
    public  static  boolean isApkDebugable(Context context){
        ApplicationInfo info=context.getApplicationInfo();
        return (info.flags&ApplicationInfo.FLAG_DEBUGGABLE)!=0;
    }
    public  static  void sout(String val){
        sout("",val);
    }
    public  static  void  sout(String tag,String val){
        if (isDebug)
            System.out.println(tag.equals("")?(TAG + "   " + val) : (tag + "   " + val));
    }
    public static void soutArrs(Object msg) {
        //region 方法体
        try {
            if (msg instanceof ArrayList)
                printArrayList((ArrayList) msg);
            else if (msg instanceof List)
                printList((List) msg);
            else if (msg instanceof HashMap)
                printHashMap((HashMap) msg);
            else if (msg instanceof SparseArray)
                printSparseArray((SparseArray) msg);
            else if (msg.getClass().isArray())
                printArray((Object[]) msg);
            else if (msg instanceof ConcurrentHashMap)
                printConcurrentHashMap((ConcurrentHashMap<String, Object>) msg);
        } catch (Exception e) {
            e.printStackTrace();
            sout(e.getMessage());
        }
        //endregion
    }

    public static void printArray(Object[] msg) throws Exception {
        Object[] msgArrs = msg;
        sout("数组大小 : " + msgArrs.length);
        for (Object msgArr : msgArrs) {
            sout(" " + msgArr.toString());
        }
    }

    public static void printConcurrentHashMap(ConcurrentHashMap<String, Object> msg) throws Exception {
        ConcurrentHashMap<String, Object> map = msg;
        sout("数组大小 : " + map.size());
        for (Map.Entry<String, Object> e : map.entrySet()) {
            sout("key = " + e.getKey() + "; value = " + e.getValue());
        }
    }

    public static void printSparseArray(SparseArray msg) throws Exception {
        SparseArray array = msg;
        sout("数组大小 : " + array.size());
        for (int i = 0; i < array.size(); i++) {
            sout("key = " + array.keyAt(i) + " ; value = " + array.valueAt(i));
        }
    }

    public static void printHashMap(HashMap msg) throws Exception {
        HashMap arr = msg;
        sout("数组大小 : " + arr.size());
        Set set = arr.entrySet();
        for (Iterator iter = set.iterator(); iter.hasNext(); ) {
            Map.Entry entry = (Map.Entry) iter.next();
            sout("Key = " + entry.getKey().toString() + "; value = " + entry.getValue()
                    .toString());
        }
    }

    public static void printList(List msg) throws Exception {
        sout("数组大小 : " + msg.size());
        for (int i = 0; i < msg.size(); i++) {
            sout("Key = " + i + " ; value = " + msg.get(i).toString());
        }
    }

    public static void printArrayList(ArrayList msg) throws Exception {
        sout("数组大小 : " + msg.size());
        for (int i = 0; i < msg.size(); i++) {
            sout("Key = " + i + " ; value = " + msg.get(i).toString());
        }
    }
}
