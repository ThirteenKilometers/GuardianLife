package lzhs.com.virtualdemo.declare_info.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2017/12/28 15:08<br/>
 * 邮箱：1050629507@qq.com
 */
public class Utils {

    /**
     * 读取assets下的文件，返回utf-8 String
     *
     * @param context
     * @param fileName 不包括后缀
     * @return
     */
    public static String readAssetsTxt(Context context, String fileName) {
        String text = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 得到有字体颜色的特殊字体
     *
     * @param content
     * @return
     */
    public static SpannableStringBuilder getColorSpan(String content) {
        if (TextUtils.isEmpty(content)) content = "0.00";
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        SpannableString mString = new SpannableString("￥ " + content);
        mString.setSpan(new ForegroundColorSpan(Color.parseColor("#FA641F")), 0, mString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.append(mString);
        stringBuilder.append(" 元");
        return stringBuilder;
    }

    /**
     * 费用类型
     *
     * @param costType 现金折扣discount<br/>
     *                 货补折扣delivery<br/>
     *                 物料material<br/>
     *                 现金cash<br/>
     * @return
     */
    public static String getCostType(String costType) {
        if (TextUtils.isEmpty(costType)) costType = "";
        if (TextUtils.equals(costType, "discount")) return "现金折扣";
        if (TextUtils.equals(costType, "delivery")) return "货补折扣";
        if (TextUtils.equals(costType, "material")) return "物料折扣";
        if (TextUtils.equals(costType, "material")) return "现金折扣";
        return "未知折扣";
    }

    /**
     * @param channel banquet	宴会<br/>
     *                group	团购<br/>
     *                hotel	酒店<br/>
     *                none	默认<br/>
     *                shop	电商<br/>
     *                smoke	烟酒店<br/>
     *                super	商超
     * @return
     */
    public static String getChannel(String channel) {
        if (TextUtils.equals(channel, "banquet")) return "宴会";
        if (TextUtils.equals(channel, "group")) return "团购";
        if (TextUtils.equals(channel, "hotel")) return "酒店";
        if (TextUtils.equals(channel, "none")) return "默认";
        if (TextUtils.equals(channel, "shop")) return "电商";
        if (TextUtils.equals(channel, "smoke")) return "烟酒店";
        if (TextUtils.equals(channel, "super")) return "商超";
        return "未知";
    }

    /**
     * 是否冻结   0未冻结  1冻结
     * @param frozen
     * @return
     */
    public static String getFrozen(int frozen) {
        return  frozen==0?"[未冻结]":"[冻结]";
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentData() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(currentTime);
    }
}
