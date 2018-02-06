package lzhs.com.virtualdemo.declare_info;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2018/1/17 11:51<br/>
 * 邮箱：1050629507@qq.com
 */
public class BindAdapter {
    /**
     * 加载网络图片
     */
    @BindingAdapter({"imageUrl"})
    public static void setmageUrl(ImageView mImage, String imgUrl){
     }
    /**
     * 给将第一个字符变成红色
     */
    @BindingAdapter({"richText","unitText"})
    public static void setSpannelText(TextView textView, String richText,String unitText){

    }
    @BindingAdapter("trimText")
    public static void setToTrim(TextView textView, String text){
        if (TextUtils.isEmpty(text)) return;
        if(text.length()==1)
            text="        "+text;
        else if(text.length()==2)
            text="      "+text;
       else if(text.length()==3)
            text="  "+text;
       textView.setText(text);
    }


}
