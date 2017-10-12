package ac57.com.application1.mvp;

import yh.com.library.base.mvp.BaseMVPPresenter;
import yh.com.library.base.mvp.BaseMVPView;

/**
 * <br/>
 * Author： lzhs <br/>
 * DataTime： 2017/10/3  18:44<br/>
 * E-mail： 1050629507@qq.com
 */

public class LoginPresenter extends BaseMVPPresenter<BaseMVPView> {
    LoginMoudel loginMoudel=new LoginMoudel(this);

}
