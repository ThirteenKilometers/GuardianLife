package lzhs.com.virtualdemo;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2018/1/19 16:40<br/>
 * 邮箱：1050629507@qq.com
 */
public class CoordinatorActivity extends AppCompatActivity {

    public static void start(Context mContext) {
        Intent mIntent = new Intent();
        mIntent.setClass(mContext, CoordinatorActivity.class);
        mContext.startActivity(mIntent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_coordiantor);
    }
}
