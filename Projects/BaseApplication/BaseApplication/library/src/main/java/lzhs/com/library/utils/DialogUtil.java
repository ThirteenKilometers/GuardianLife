package lzhs.com.library.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;


/**
 * 加载进度条
 */

public class DialogUtil {

    static String msg="玩命加载中...";


    /**
     * 创建简单对话框
     * @param mContext
     * @return
     */
    public static AlertDialog creatProgressDialog(Context mContext) {
        return creatProgressDialog(mContext, "",msg );

    }

    /**
     * 创建标题对话框
     * @param mContext
     * @param title 自定义标题
     * @return
     */
    public static AlertDialog creatProgressDialog(Context mContext, String title) {
        return creatProgressDialog(mContext, "", title);
    }
    /**
     * 创建自定义简单对话框
     * @param mContext
     * @param msg 自定义消息
     * @return
     */
    public static AlertDialog creatProgressDialogMsg(Context mContext, String msg) {
        return creatProgressDialog(mContext, "", msg);
    }

    /**
     * 创建带标题对话框
     * @param mContext
     * @param title 自定义标题
     * @param msg 自定义消息
     * @return
     */
    public static AlertDialog creatProgressDialog(Context mContext, String title, String msg) {
        //创建ProgressDialog对象
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setCanceledOnTouchOutside(false);
        //设置进度条风格，风格为圆形，旋转的
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //设置ProgressDialog 标题
        if (!title.equals(""))
            progressDialog.setTitle(title);
        //设置ProgressDialog 提示信息
        if (!msg.equals(""))
            progressDialog.setMessage(msg);
        //设置ProgressDialog 标题图标
        //progressDialog.setIcon(com.azcx9d.publicgroupshop_agency.R.mipmap.ic_launcher);
        //设置ProgressDialog 的进度条是否不明确
        progressDialog.setIndeterminate(false);
        //设置ProgressDialog 是否可以按退回按键取消
        progressDialog.setCancelable(true);
        // 让ProgressDialog显示
        progressDialog.show();
        return progressDialog;
    }


}
