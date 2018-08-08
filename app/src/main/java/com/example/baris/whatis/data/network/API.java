package com.example.baris.whatis.data.network;

import com.example.baris.whatis.data.models.Responses;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public class API {

    private static final String BASE_URL = "https://od-api.oxforddictionaries.com/";
    private static final int HTTP_READ_TIMEOUT = 10;
    private static final int HTTP_CONNECT_TIMEOUT = 6;

    public static oxford_API createService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build();

        return retrofit.create(oxford_API.class);
    }

    public interface oxford_API {

        @GET("api/v1/entries/{source_lang}/{word_id}")
        Call<Responses> search(@Path("source_lang") String sourceLanguage,
                               @Path("word_id") String word,
                               @Header("app_id") String appId,
                               @Header("app_key") String appKey);
    }

    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(createLoggingInterceptor());
        return httpClientBuilder.build();
    }

    private static HttpLoggingInterceptor createLoggingInterceptor(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return logging;
    }
}
