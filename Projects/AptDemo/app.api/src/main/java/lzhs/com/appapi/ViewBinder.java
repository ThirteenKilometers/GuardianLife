package lzhs.com.appapi;

/**
 * UI绑定解绑接口<br/>
 * 作者：LZHS<br/>
 * 时间： 2017/10/20 16:06<br/>
 * 邮箱：1050629507@qq.com
 */
public interface  ViewBinder<T> {
    void bindView(T host,Object object,ViewFinder finder);
    void unBindView(T host);
}
