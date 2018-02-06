package lzhs.com.virtualdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.didi.virtualapk.PluginManager;
import com.orhanobut.logger.Logger;

import java.io.File;

import lzhs.com.virtualdemo.declare_info.ActivityDeclareInfo;

public class MainActivity extends AppCompatActivity {


    private static final String PLUGIN_PKG_NAME = "lzhs.com.virtual_plugin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityDeclareInfo.start(this,"");
//        CoordinatorActivity.start(this);


        //testMethod();


    }

    private void testMethod() {
        String read = "android.permission.READ_EXTERNAL_STORAGE";
        if (ContextCompat.checkSelfPermission(this, read) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{read}, 1);
        }
        findViewById(R.id.mBtnStartApk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPlugin();
                if (PluginManager.getInstance(MainActivity.this).getLoadedPlugin(PLUGIN_PKG_NAME) == null) {
                    Toast.makeText(getApplicationContext(),
                            "插件未加载,请尝试重启APP", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                ComponentName name = new ComponentName(PLUGIN_PKG_NAME, PLUGIN_PKG_NAME + ".MainActivity");
                intent.setComponent(name);
                startActivity(intent);


            }
        });
    }

    public void loadPlugin() {
        try {
            PluginManager pluginManager = PluginManager.getInstance(this);
            File apk = new File(Environment.getExternalStorageDirectory(), "plugin.apk");
            Logger.t("loadPlugin")
                    .d(apk.getAbsolutePath());
            if (apk.exists()) {

                pluginManager.loadPlugin(apk);

            } else {
                boolean is = apk.createNewFile();
                Logger.d("文件是否创建成功       " + is);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.d(e.getMessage());
        }
    }
}
