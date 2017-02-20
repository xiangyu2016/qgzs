package com.example.administrator.qgzs.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/2/11 0011.
 */

public class RetrofitUtils {

    //  https://item.jd.com/3243688.html
    //  https://item.jd.com/1834943.html
    private Retrofit retrofit;
    private String baseUrl ="http://p.3.cn/prices/";

    private static class SingleLoader{
        private static final RetrofitUtils INSTANCE = new RetrofitUtils();
    }
    public static RetrofitUtils getInstance(){
        return SingleLoader.INSTANCE;
    }
    public void initOkHttp(@NonNull Context context){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = null;
        try {
            client = new OkHttpClient.Builder()
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)       //设置连接超时
                    .readTimeout(10000L,TimeUnit.MILLISECONDS)          //设置读取超时
                    .writeTimeout(10000L, TimeUnit.MILLISECONDS)         //设置写入超时
                    .cache(new Cache(context.getCacheDir(),10 * 1024 * 1024))   //设置缓存目录和10M缓存
                    .addInterceptor(interceptor)    //添加日志拦截器（该方法也可以设置公共参数，头信息）
                    //添加一个不验证证书链的证书信任管理器。
                    .sslSocketFactory(getSSLSocketFactory()).hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        retrofit = new Retrofit.Builder()
                .client(client)        //设置OkHttp
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()) //  添加数据解析ConverterFactory
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   //添加RxJava
                .build();
    }
    public Retrofit getRetrofit(){
        return retrofit;
    }


    public static SSLSocketFactory getSSLSocketFactory() throws Exception {
        //创建一个不验证证书链的证书信任管理器。
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        }};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts,
                new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        return sslContext
                .getSocketFactory();
    }

}
