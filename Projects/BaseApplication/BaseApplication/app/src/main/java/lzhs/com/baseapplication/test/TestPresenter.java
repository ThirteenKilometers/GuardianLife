package lzhs.com.baseapplication.test;

import lzhs.com.library.mvp.IPresenter;

/**
 * <br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/23 23:25<br/>
 * 邮箱：1050629507@qq.com
 */
public class TestPresenter extends IPresenter<TestView,TestModel> {
    @Override
    public TestModel createModle() {
        return new TestModel();
    }

    public void getText(){
        mModel.get().getObservable();
    }

    public  void setText(){
        mViewRef.get().setText("");
    }


}
