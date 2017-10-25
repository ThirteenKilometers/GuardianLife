package lzhs.com.baseapplication.mvp.start;

import lzhs.com.library.mvp.IPresenter;
import lzhs.com.library.utils.data.net.observers.BaseResponse;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/24 23:17<br/>
 * 邮箱：1050629507@qq.com
 */
public class StartPresenter extends IPresenter <StartView,StartModel>{
BaseResponse observer=null;
    @Override
    public StartModel createModle() {
        return new StartModel();
    }

    public void getDatas(String val) {
       observer=new BaseResponse() {
           @Override
           protected void onSuccess(String json) {
               mViewRef.get().getDataSuccess(json);
           }

           @Override
           protected void onFaild(String err) {

               mViewRef.get().getDataFaild(err);
           }
       };
       mModel.get().getDatas(val,observer);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (observer != null&&observer.mDisposable.isDisposed())
            observer.mDisposable.dispose();
    }
}
