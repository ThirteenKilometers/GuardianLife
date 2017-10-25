package lzhs.com.library.utils.data.net;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Ｔａｍｉｃ on 2016-07-08.
 */
 public interface BaseApiService {

  public static final String Base_URL = "http://www.baidu.com/";



 @GET("{url}")
 Observable<ResponseBody> executeGet(
        @Path("url") String url,
        @HeaderMap Map<String,String> headers,
        @QueryMap Map<String, String> querys);


@POST("{url}")
Observable<ResponseBody> executePost(
        @Path("url") String url,
        @HeaderMap Map<String,String> headers,
        @QueryMap Map<String, String> querys);



 @POST("{url}")
Observable<ResponseBody> uploadFiles(
        @Path("url") String url,
        @Path("headers") Map<String, String> headers,
        @Part("filename") String description,
        @PartMap()  Map<String, RequestBody> maps);

 @Streaming
 @GET
 Observable<ResponseBody> downloadFile(@Url String fileUrl);

}