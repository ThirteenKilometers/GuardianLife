package lzhs.com.coordinatordemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import lzhs.com.coordinatordemo.utils.StatusBarUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.setTransparentForImageView(this, null);
    }
}
