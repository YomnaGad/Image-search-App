package com.example.android.searchimg.data.remote;

import com.example.android.searchimg.data.model.Response;
import com.example.android.searchimg.data.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MultipartBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by Yomna on 11/27/2016.
 */
public interface Service {

    //define the services
    @POST("user/login/") // this line defines the path to service
//    Observable<ResponseBody> login(@QueryMap Map<String, String> map);
    Observable<Response> login(@Body User user);

    @POST("user/signup/")
    Observable<Response> createUser(@Body User user);

   /* @POST("User/signup/")
    Call registerUser(@Body User User);*/
    @Multipart
   @POST("image/trail")
   Observable<Response> upload(@Part MultipartBody.Part image);
    //Observable<Response> upload(@Part("image") RequestBody photo);
    class Creator{
        public static Service getService(){

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            // configure retrofit in android app
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("http://ec2-54-214-92-209.us-west-2.compute.amazonaws.com/")
                    .build();


            return retrofit.create(Service.class);
        }

    }
}

