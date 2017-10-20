package yh.com.library.base.mvp;

/**
 * <br/>
 *
 * 将MVP中View接口都将继承该接口
 * Author： yh <br/>
 * DataTime： 2017/9/27  16:20<br/>
 * E-mail： 1050629507@qq.com
 */

public interface BaseMVPView<T> {
    void onSuccess(T data);

    /**
     * 该方法将在请求失败时调用
     * @param err
     */
    void onFaild(String err);
}
