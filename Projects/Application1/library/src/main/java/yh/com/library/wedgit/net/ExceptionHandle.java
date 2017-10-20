package yh.com.library.wedgit.net;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.HttpException;

/**
 * <br/>
 * 该类为用户异常处理类型
 * Author： yh <br/>
 * DataTime： 2017/10/3  11:22<br/>
 * E-mail： 1050629507@qq.com
 */

public class ExceptionHandle {
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    //java.lang.RuntimeException: Failed to invoke public okhttp3.RequestBody() with no args
    public static ResponeThrowable handleException(Throwable e) {
        ResponeThrowable ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponeThrowable(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.message = "网络繁忙，请稍后重试...";
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new ResponeThrowable(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ResponeThrowable(e, ERROR.PARSE_ERROR);
            ex.message = "JSON 数据解析错误";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponeThrowable(e, ERROR.NETWORD_ERROR);
            ex.message = "网络连接超时，请稍后重试...";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponeThrowable(e, ERROR.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        } else if (e instanceof ConnectTimeoutException) {
            ex = new ResponeThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "网络连接超时，请稍后重试";
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new ResponeThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "连接超时";
            return ex;
        } else if (e instanceof NullPointerException) {
            ex = new ResponeThrowable(e, ERROR.TIMEOUT_NULL);
            if (e.getMessage().equals("") || e.getMessage() == null) {
                ex.message = "空指针异常";
            } else ex.message = e.getMessage();
            return ex;
        } else {
            ex = new ResponeThrowable(e, ERROR.UNKNOWN);
            ex.message = "网络连接超时，请检查网络";
            return ex;
        }
    }
    /**
     * 约定异常
     */
    public static class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 0x1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 0x1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 0x1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 0x1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 0x1005;

        /**
         * 连接超时
         */
        public static final int TIMEOUT_ERROR = 0x1006;
        /**
         * 空指针异常
         */
        public static final int TIMEOUT_NULL = 0x1007;
    }
    /**
     * 所有请求错误类型 <br/>
     * * 请求返回状态码<br/>
     * -1 ————>  token 过期<br/>
     * 0 ————>  一切正常<br/>
     * 2 ————> 通用异常，不需要做处理 <br/>
     * 4 ————>  请求成功，但数据为空
     */
    public  static  class ResponeThrowable extends  Exception{
        /**
         * token 过期
         */
        public static final int ERROR_TOKEN_PAST_DUE = -1;

        /**
         * 通用异常
         */
        public static final int ERROR_COMMON = 2;

        /**
         * 请求成功，但数据为空
         */
        public static final int ERROR_DATA_NULL = 4;
        public  int code;
        public  String message;
        public  ResponeThrowable(Throwable throwable,int code){
            super(throwable);
            this.code=code;
        }
    }
    /**
     * 与服务器---约定异常
     */
    public  static  class  ServerException extends RuntimeException{
        public  int code;
        public  String message;

        public ServerException(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
