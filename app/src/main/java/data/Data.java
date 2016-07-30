package data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import silvanet.com.mx.imagine_space.BuildConfig;

/**
 * Created by LuisAlfredoSilva on 30/07/2016.
 */
public class Data {
    public static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(BuildConfig.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
