package lzhs.com.baseapplication.module;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lzhs.com.baseapplication.R;
import lzhs.com.baseapplication.module.login.LoginActivity;
import lzhs.com.baseapplication.mvp.start.StartPresenter;
import lzhs.com.baseapplication.mvp.start.StartView;
import lzhs.com.library.base.BaseActivity;
import lzhs.com.library.utils.DialogUtil;

/**
 * 该页面作为整个应用入口函数页面<br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/24 23:07<br/>
 * 邮箱：1050629507@qq.com
 */
public class StartActivity extends BaseActivity<StartPresenter> implements StartView {

    @BindView(R.id.mTextShow)
    TextView mTextShow;
    @BindView(R.id.mTextShowTitle)
    TextView mTextShowTitle;

    Handler mHadler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0) {
                //开始请求数据
                mPresenter.getDatas("开始请求数据");
                mDialog = DialogUtil.creatProgressDialog(StartActivity.this);
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mHadler.sendEmptyMessageDelayed(0, 4000);
    }

    @Override
    public StartPresenter onCreatePresenter() {
        return new StartPresenter();
    }


    @Override
    public void getDataSuccess(String json) {
        mDialog.dismiss();
        mTextShow.setText(json);
        Observable.interval(1, TimeUnit.SECONDS)
                .take(6)
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    Disposable mDisposable = null;

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        int time = 6 - aLong.intValue();
                        SpannableStringBuilder stringBuilder = new SpannableStringBuilder("马上开启下个页面");
                        stringBuilder.append(new String("(" + time + ")"))
                                .setSpan(new ForegroundColorSpan(Color.RED), 8, stringBuilder.length(), Spanned.SPAN_COMPOSING);
                        mTextShowTitle.setText(stringBuilder);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        startActivity(new Intent(StartActivity.this, LoginActivity.class));
                        StartActivity.this.finish();
                        mDisposable.dispose();
                    }
                });


    }

    @Override
    public void getDataFaild(String err) {
        mDialog.dismiss();

        mTextShow.setText(err);
    }
}
