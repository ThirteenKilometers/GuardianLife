package lzhs.com.virtualdemo.declare_info;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lzhs.com.virtualdemo.R;
import lzhs.com.virtualdemo.databinding.ActivityDeclareInfoBinding;
import lzhs.com.virtualdemo.databinding.LayoutDeclareInfoBeneficiaryBinding;
import lzhs.com.virtualdemo.declare_info.utils.FastJsonTools;
import lzhs.com.virtualdemo.declare_info.utils.Utils;

/**
 * 费用系统申报详情页面<br/>
 * 作者：LZHS<br/>
 * 时间： 2018/1/16 16:57<br/>
 * 邮箱：1050629507@qq.com
 */
public class ActivityDeclareInfo extends AppCompatActivity {
    String draweeValueUuid;

    ActivityDeclareInfoBinding mBinding = null;
    /**
     * @param mContext
     * @param draweeValueUuid 申报表单值唯一识别码
     */
    public static void start(Context mContext, String draweeValueUuid) {
        Intent mIntent = new Intent();
        mIntent.setClass(mContext, ActivityDeclareInfo.class);
        mIntent.putExtra("draweeValueUuid", draweeValueUuid);
        mContext.startActivity(mIntent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_declare_info);

        mBinding.setMClick(new ClickLisenter());
        initData();

    }

    private void initData() {
        Observable.just(eventData())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanDeclarInfo>() {
                    @Override
                    public void accept(BeanDeclarInfo data) throws Exception {

                        mBinding.setMDeclarInfo01(data);

                        for (BeanDeclarInfoBeneficiary beneficiary : data.getBeneficiary()) {
                            LayoutDeclareInfoBeneficiaryBinding mBinBene = DataBindingUtil.inflate(LayoutInflater.from(ActivityDeclareInfo.this)
                                    , R.layout.layout_declare_info_beneficiary
                                    , mBinding.mViewBeneficParam
                                    , false);
                            mBinBene.setMBeneficiary(beneficiary);
                            mBinding.mViewBeneficParam.addView(mBinBene.getRoot());
                        }

                    }
                });


    }

    private BeanDeclarInfo eventData() {
        String jsonVal = Utils.readAssetsTxt(this, "test_json_cost_app_drawee_details");
        JSONObject json = JSON.parseObject(jsonVal);
        Logger.d(json);
        BeanDeclarInfo beanDeclarInfo = FastJsonTools.getObjcet(json.getString("content"), BeanDeclarInfo.class);
        return beanDeclarInfo;

    }

    public class ClickLisenter implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ActivityDeclareTask.start(ActivityDeclareInfo.this);
        }
    }
}
