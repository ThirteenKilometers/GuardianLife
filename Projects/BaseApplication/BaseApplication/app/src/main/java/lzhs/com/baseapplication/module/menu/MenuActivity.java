package lzhs.com.baseapplication.module.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import lzhs.com.baseapplication.R;
import lzhs.com.baseapplication.module.menu.tabs.five.FiveFragment;
import lzhs.com.baseapplication.module.menu.tabs.four.FourFragment;
import lzhs.com.baseapplication.module.menu.tabs.one.OneFragment;
import lzhs.com.baseapplication.module.menu.tabs.three.ThreeFragment;
import lzhs.com.baseapplication.module.menu.tabs.two.TwoFragment;
import lzhs.com.library.base.BaseActivity;
import lzhs.com.library.base.BaseFragment_V4;
import lzhs.com.library.utils.ActivityUtil;

/**
 * 该页面作为APP首页菜单页面<br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/27 12:02<br/>
 * 邮箱：1050629507@qq.com
 */
public class MenuActivity extends BaseActivity {

    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;

    TabLayout.Tab one, two, three, four, five;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ActivityUtil.setStatusBarBackground(this);
        ButterKnife.bind(this);
        initViews();
        initEvents();

        /*
        媳妇，辛苦你了。我爱你！
         */
    }


    private void initEvents() {

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == mTabLayout.getTabAt(0)) {
                    one.setIcon(ContextCompat.getDrawable(MenuActivity.this, R.mipmap.ic_launcher_round));
                    mViewPager.setCurrentItem(0);
                } else if (tab == mTabLayout.getTabAt(1)) {
                    two.setIcon(ContextCompat.getDrawable(MenuActivity.this, R.mipmap.ic_launcher_round));
                    mViewPager.setCurrentItem(1);
                } else if (tab == mTabLayout.getTabAt(2)) {
                    three.setIcon(ContextCompat.getDrawable(MenuActivity.this, R.mipmap.ic_launcher_round));
                    mViewPager.setCurrentItem(2);
                } else if (tab == mTabLayout.getTabAt(3)) {
                    four.setIcon(ContextCompat.getDrawable(MenuActivity.this, R.mipmap.ic_launcher_round));
                    mViewPager.setCurrentItem(3);
                } else if (tab == mTabLayout.getTabAt(4)) {
                    five.setIcon(ContextCompat.getDrawable(MenuActivity.this, R.mipmap.ic_launcher_round));
                    mViewPager.setCurrentItem(4);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab == mTabLayout.getTabAt(0))
                    one.setIcon(ContextCompat.getDrawable(MenuActivity.this, R.mipmap.ic_launcher));
                else if (tab == mTabLayout.getTabAt(1))
                    two.setIcon(ContextCompat.getDrawable(MenuActivity.this, R.mipmap.ic_launcher));
                else if (tab == mTabLayout.getTabAt(2))
                    three.setIcon(ContextCompat.getDrawable(MenuActivity.this, R.mipmap.ic_launcher));
                else if (tab == mTabLayout.getTabAt(3))
                    four.setIcon(ContextCompat.getDrawable(MenuActivity.this, R.mipmap.ic_launcher));
                else if (tab == mTabLayout.getTabAt(4))
                    five.setIcon(ContextCompat.getDrawable(MenuActivity.this, R.mipmap.ic_launcher));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initViews() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] mTitles = new String[]{"首页", "我的广告", "我的屏幕", "报表", "我"};

            @Override
            public BaseFragment_V4 getItem(int position) {

                switch (position) {
                    case 0:
                        return OneFragment.newInstance(mTitles[position]);
                    case 1:
                        return TwoFragment.newInstance(mTitles[position]);
                    case 2:
                        return ThreeFragment.newInstance(mTitles[position]);
                    case 3:
                        return FourFragment.newInstance(mTitles[position]);
                    case 4:
                        return new FiveFragment();
                }

                return null;
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }

        });
        mViewPager.setOffscreenPageLimit(5);

        mTabLayout.setupWithViewPager(mViewPager);

        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
        four = mTabLayout.getTabAt(3);
        five = mTabLayout.getTabAt(4);

        one.setIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher));
        two.setIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher));
        three.setIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher));
        four.setIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher));
        five.setIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher));
    }


}
