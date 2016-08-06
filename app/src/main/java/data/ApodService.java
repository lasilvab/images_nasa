package data;

/**
 * Created by LuisAlfredoSilva on 30/07/2016.
 */

import model.Apod;
import model.MarsRoverPhotos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApodService {

    @GET("planetary/apod?api_key=9PLTNIVPFbidnArlHSKsy4jud5PApcRo7RUjfYbv")
    Call<Apod> getTodayApod();

    @GET("planetary/apod")
    Call<Apod> getTodayApodWhithQuery(@Query("api_key") String apiKey);

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    Call<MarsRoverPhotos> getMarsRoverPhotos(@Query("sol") int sol, @Query("api_key") String apiKey );

}
