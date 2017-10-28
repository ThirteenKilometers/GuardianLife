package lzhs.com.baseapplication.module.menu.tabs.five;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import lzhs.com.baseapplication.module.menu.tabs.three.ThreeFragment;
import lzhs.com.library.base.BaseFragment_V4;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/27 13:34<br/>
 * 邮箱：1050629507@qq.com
 */
public class FiveFragment extends BaseFragment_V4 {
    static final String TAG = "TAG";


    public static FiveFragment newInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, argument);
        FiveFragment fiveFragment= new FiveFragment();
        fiveFragment.setArguments(bundle);
        return fiveFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Random random = new Random();
        TextView tv = new TextView(getActivity());
        tv.setText(getArguments().getString(TAG));
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.argb(random.nextInt(100),
                random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        return tv;
    }}
