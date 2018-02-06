package lzhs.com.virtualdemo.declare_info.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FastJsonTools {

    public FastJsonTools() {
        super();
    }

    /**
     * 完成对单个JavaBean的解析<br/>
     *
     * @param @param jsonStr Json字符串
     * @param @param mClass 目标JavaBean对象
     * @return T 返回JavaBean 对象
     * @Title: getObjcet
     */
    public static <T> T getObjcet(String jsonStr, Class<T> mClass) {
        T t = null;
        try {
            t = JSON.parseObject(jsonStr, mClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return t;
    }

    /**
     * 完成对 List<JavaBean>的解析<br/>
     *
     * @param @param jsonStr Json字符串
     * @param @param mClass 目标JavaBean对象
     * @return T 返回JavaBean 对象
     * @Title: getObjects
     */
    public static <T> List<T> getObjects(String jsonStr, Class<T> mClass) {
        List<T> list = new ArrayList<T>();
        try {
            list = JSON.parseArray(jsonStr, mClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    /**
     * 完成对 List<Map<String, Object>>的解析<br/>
     *
     * @param @param jsonStr Json字符串
     * @param @param mClass 目标List<Map<String,Object>>对象
     * @return T 返回List<Map<String,Object>> 对象
     * @Title: getObjects
     */
    public static <T> List<Map<String, Object>> getLsitMap(String jsonStr) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = JSON.parseObject(jsonStr,
                    new TypeReference<List<Map<String, Object>>>() {
                    });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
}
