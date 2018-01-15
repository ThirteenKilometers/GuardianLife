package lzhs.com.virtualdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Error:Execution failed for task ':app:processDebugResources'.
> Could not get unknown property 'textSymbolOutputDir' for task ':app:processDebugResources' of type com.android.build.gradle.tasks.ProcessAndroidResources.*/
    }
}
