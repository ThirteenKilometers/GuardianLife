package lzhs.com.baseapplication.mvp.login;

import lzhs.com.library.mvp.IPresenter;
import lzhs.com.library.utils.data.net.observers.BaseResponse;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/25 18:24<br/>
 * 邮箱：1050629507@qq.com
 */
public class LoginPresenter extends IPresenter<LoginView,LoginModel> {
    @Override
    public LoginModel createModle() {
        return new LoginModel();
    }

    protected  void  getData01(){

    }


    public void getData01(String s) {
        BaseResponse observer=new BaseResponse() {
            @Override
            protected void onSuccess(String json) {

            }

            @Override
            protected void onFaild(String err) {

            }
        };

    }
}
