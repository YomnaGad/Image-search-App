package com.example.android.searchimg.data.remote;

import com.example.android.searchimg.data.model.Request;
import com.example.android.searchimg.data.model.Response;
import com.example.android.searchimg.data.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MultipartBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Yomna on 11/27/2016.
 */
public interface Service {

    //define the services
    //@POST("user/login/") // this line defines the path to service
    @POST("api-token-auth/")
//    Observable<ResponseBody> login(@QueryMap Map<String, String> map);
   // Observable<Response> login(@Body Request userRequest);
    Observable<Response> login(@Body User user);
    //Observable<Response> login(@Body Request userRequest, @Header("Authorization") String authorization);

    @POST("user/signup/")
    Observable<Response> createUser(@Body Request userRequest);

   /* @POST("User/signup/")
    Call registerUser(@Body User User);*/
    @Multipart
   @POST("image/upload")
   Observable<Response> upload(@Header("Authorization") String token, @Part MultipartBody.Part image);
    //Observable<Response> upload(@Part("image") RequestBody photo);

    @GET("image/search")
    Observable<Response> fetchImages(@Header("Authorization") String token, @Query("q") String querySearch);


    class Creator{
        public static Service getService(){

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            // configure retrofit in android app
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("http://ec2-54-200-112-229.us-west-2.compute.amazonaws.com")
                    .build();


            return retrofit.create(Service.class);
        }

    }
}

