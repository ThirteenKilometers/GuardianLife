package yh.com.library.base.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

/**
 *1. 该类实现了张鸿洋的Android屏幕适配方案
 * 2.封装了Android 6.0动态权限
 * 3.实现了动态更改通知栏的颜色或者
 *背景，注意：该页面默认是去除标题栏的
 *  <br/>
 * Author： yh <br/>
 * DataTime： 2017/9/27  16:14<br/>
 * E-mail： 1050629507@qq.com
 */
/**
 * APP类型：
 * <br/>1：Android商户端
 * <br/>2 :Android代理端
 * <br/>3：Android用户端
 * <br/>4：IOS商户端
 * <br/>5：iOS代理端
 * <br/>6：IOS用户端
 */

public abstract class BaseAutoActivity extends AutoLayoutActivity {
    public  static  final  int APPTYPE=3;
    protected AlertDialog mDialog=null;
    public  final  String TAG=this.getClass().getSimpleName();
    /**
     * 权限监听接口
     */

    private  PermissionListener mListener;

    /**
     * 权限监听请求码
     */
    private  final  static  int  PERMISSION_REQUEST_CODE=0x1b4a;
    /**
     * 当前屏幕的宽度
     */
    protected  int Screen_Width;

    /**
     * 当前屏幕的高度
     */
    protected  int Screen_Height;
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScreen_WidtnAndHetght();
        context=this;

    }

    private void setScreen_WidtnAndHetght() {
        DisplayMetrics metrics=new DisplayMetrics();//获取像素宽高包含虚拟键所占的空间
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        this.Screen_Width= metrics.widthPixels;
        this.Screen_Height=metrics.heightPixels;
        context=this;

    }

    /**
     * 设置全透明状态栏效果
     */
    protected void setStateBar() {
        Window window = getWindow();
        //android 5.0 以上手机状态栏黑条
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public int getStatusBarHeight(Context context) {
        int statusHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
    // TODO: 2017/10/2 Android 6.0动态权限 尺寸
    public  void  requestPermission(PermissionListener mListener,String...permssions){
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            mListener.onGranted();
            this.mListener=mListener;
            //获取未授权的权限列表
            List<String> unauthorizeds=new ArrayList<>();
            for (String permission :permssions){
                if (ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
                    unauthorizeds.add(permission);
                }
            }
            if (!unauthorizeds.isEmpty()){
                ActivityCompat.requestPermissions(this,unauthorizeds.toArray(new  String[]{}),PERMISSION_REQUEST_CODE);

            }else {
                mListener.onGranted();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==PERMISSION_REQUEST_CODE&&grantResults.length>0){
            //存放没有授权的权限
            List<String>deniedPermsson=new ArrayList<>();
            for (int i=0;i<permissions.length;i++){
                String permission=permissions[i];
                int grantResult=grantResults[i];
                if (grantResult!=PackageManager.PERMISSION_GRANTED)
                    deniedPermsson.add(permission);
            }
            //说明都进行了授权
            if(deniedPermsson.isEmpty()||deniedPermsson.size()<=0)
                mListener.onGranted();
            else  mListener.onDenied(deniedPermsson.toArray(new String[]{}));
        }
    }

    public  interface  PermissionListener{
        /**
         * 已授权
         */
        void onGranted();

        /**
         * 返回被拒权限列表
         * @param deniedPermissions
         */
        void  onDenied(String ...deniedPermissions);
    }

}
