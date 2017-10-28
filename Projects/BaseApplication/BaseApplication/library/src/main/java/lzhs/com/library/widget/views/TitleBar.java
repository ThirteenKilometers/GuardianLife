package lzhs.com.library.widget.views;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import lzhs.com.library.R;


/**
 * <br/>
 * Author： lzhs <br/>
 * DataTime： 2017/5/2  20:47<br/>
 * E-mail： 1050629507@qq.com
 */

public class TitleBar extends FrameLayout {
    /**
     * titleBar 的高度
     */
    private int mTitleBarHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());

    /**
     * 状态栏的高度
     */
    private int mStartHeight = getStatusBarHeight();
    /**
     * 状态栏的背景色
     */
    private Drawable mTitleBarBg = new ColorDrawable(ContextCompat.getColor(getContext(),R.color.colorPrimary_));

    private String mTitleText = "";

    private int mTitleTextSize = 18;

    private int mTitleTextColor = ContextCompat.getColor(getContext(), R.color.textColor01);

    private String mLeftText = "";

    private int mLeftTextColor = ContextCompat.getColor(getContext(), R.color.textColor01);

    private int mLeftTextSize = 13;

    private Drawable mLeftTextImg = getResources().getDrawable(R.mipmap.icon_back);
    /**
     * 默认右边不显示
     */
    private boolean mRightIsShwo = false;

    private String mRightText = "";

    private int mRightTextColor = ContextCompat.getColor(getContext(), R.color.textColor01);

    private int mRightTextSize = 13;

    private Drawable mRightTextImg = null;


    private View viewPara = null;

    private OnClickListener defaultFinsh = new OnClickListener() {
        @Override
        public void onClick(View v) {
            ((Activity) getContext()).finish();
        }
    };

    public TextView mTextShowTitle, mTextShowLeft, mTextShowRight;

    public View mTitleBarContent;


    public TitleBar(@NonNull Context context) {
        this(context, null);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        viewPara = LayoutInflater.from(context).inflate(R.layout.layout_title_bar, null);
        viewPara.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                mTitleBarHeight));
        addView(viewPara);

        initAttrs(getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar, defStyleAttr, 0));


    }


    private void initAttrs(TypedArray array) {
        try {
            Drawable tempDraw = array.getDrawable(R.styleable.TitleBar_titleBg);
            if (tempDraw != null) mTitleBarBg = tempDraw;
            mTitleText = array.getString(R.styleable.TitleBar_titleText);
            mTitleTextColor = array.getColor(R.styleable.TitleBar_titleTextColor, mTitleTextColor);
            mTitleTextSize = array.getDimensionPixelSize(R.styleable.TitleBar_titleTextSize, mTitleTextSize);

            mLeftText = array.getString(R.styleable.TitleBar_leftText);
            Drawable tempDrawLeft = array.getDrawable(R.styleable.TitleBar_leftImg);
            if (tempDrawLeft != null) mLeftTextImg = tempDrawLeft;
            mLeftTextColor = array.getColor(R.styleable.TitleBar_leftColor, mLeftTextColor);
            mLeftTextSize = array.getDimensionPixelSize(R.styleable.TitleBar_lefTextSize, mLeftTextSize);

            mRightIsShwo = array.getBoolean(R.styleable.TitleBar_rightIsShow, mRightIsShwo);
            mRightText = array.getString(R.styleable.TitleBar_rightText);
            mRightTextImg = array.getDrawable(R.styleable.TitleBar_rightImg);
            mRightTextColor = array.getColor(R.styleable.TitleBar_rightColor, mRightTextColor);
            mRightTextSize = array.getDimensionPixelSize(R.styleable.TitleBar_rightTextSize, mRightTextSize);
        } finally {
            array.recycle();
        }

        initViews();

    }

    public void setTitlText(String mTitleText) {
        mTextShowTitle.setText(mTitleText);
    }


    public void setRightText(String mRightText) {
        mTextShowRight.setText(mRightText);
    }

    public void setRightTextColor(int mRightTextColor) {
        mTextShowRight.setTextColor(mRightTextColor);
    }


    private void initViews() {
        mTextShowTitle = (TextView) viewPara.findViewById(R.id.mTextShowTitle);
        mTextShowLeft = (TextView) viewPara.findViewById(R.id.mTextShowLeft);
        mTextShowRight = (TextView) viewPara.findViewById(R.id.mTextShowRight);
        mTitleBarContent = viewPara.findViewById(R.id.mTitleBarContent);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTitleBarContent.getLayoutParams();
        params.setMargins(0, mStartHeight, 0, 0);
        mTitleBarContent.setLayoutParams(params);
        viewPara.setBackgroundDrawable(mTitleBarBg);

        mTextShowTitle.setText(mTitleText);
        mTextShowTitle.setTextColor(mTitleTextColor);
        mTextShowTitle.setTextSize(mTitleTextSize);

        mTextShowLeft.setCompoundDrawablesWithIntrinsicBounds(mLeftTextImg, null, null, null);
        mTextShowLeft.setText(mLeftText);
        mTextShowLeft.setTextColor(mLeftTextColor);
        mTextShowLeft.setTextSize(mLeftTextSize);
        mTextShowLeft.setOnClickListener(defaultFinsh);

        if (mRightIsShwo) {
            mTextShowRight.setVisibility(View.VISIBLE);
            mTextShowRight.setTextColor(mRightTextColor);
            mTextShowRight.setTextSize(mRightTextSize);
            if (mRightTextImg != null)
                mTextShowRight.setCompoundDrawablesWithIntrinsicBounds(null, null, mRightTextImg, null);
            mTextShowRight.setText(mRightText);
        } else {
            mTextShowRight.setVisibility(View.GONE);
        }

    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public TitleBar setOnClickToLift(OnClickListener onClickToLift) {
        mTextShowLeft.setOnClickListener(onClickToLift);
        return this;
    }

    public TitleBar setOnClickToRight(OnClickListener onClickToRight) {
        if (mTextShowRight.getVisibility() == View.VISIBLE)
            mTextShowRight.setOnClickListener(onClickToRight);
        return this;
    }
}
