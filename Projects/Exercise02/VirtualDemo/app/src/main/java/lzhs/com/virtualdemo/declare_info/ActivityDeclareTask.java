package lzhs.com.virtualdemo.declare_info;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import lzhs.com.virtualdemo.R;

/**
 * 申报执行页面<br/>
 * 作者：LZHS<br/>
 * 时间： 2018/1/24 14:44<br/>
 * 邮箱：1050629507@qq.com
 */
public class ActivityDeclareTask extends AppCompatActivity {

    public static void start(Activity mContext) {
        Intent mIntent = new Intent();
        mIntent.setClass(mContext, ActivityDeclareTask.class);
        mContext.startActivity(mIntent);
        mContext.overridePendingTransition(R.anim.anim_bottom_in, R.anim.anim_no);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_declare_task);
        StatusBarUtil.setTranslucent(this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_no, R.anim.anim_bottom_out);
    }

}
