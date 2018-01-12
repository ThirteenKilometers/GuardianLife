package lzhs.com.moduleapplication.views.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * 主要为我们所有的Fragment提供公共的行为或事件<br/>
 * 作者：LZHS<br/>
 * 时间： 2017/12/21 11:12<br/>
 * 邮箱：1050629507@qq.com
 */
public class BaseFragment extends Fragment {
    protected String TAG = this.getClass().getSimpleName();
    protected Activity mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

