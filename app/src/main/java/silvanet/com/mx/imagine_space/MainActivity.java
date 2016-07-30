package silvanet.com.mx.imagine_space;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import data.ApodService;
import data.Data;
import model.Apod;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);

        // AQUÍ LE ESTAMOS PASANDO LA api key con el método getTodayApod

        //Call<Apod> callApodService = apodService.getTodayApod();

        Call<Apod> callApodService = apodService.getTodayApodWhithQuery("9PLTNIVPFbidnArlHSKsy4jud5PApcRo7RUjfYbv");

       callApodService.enqueue(new Callback<Apod>() {
           @Override
           public void onResponse(Call<Apod> call, Response<Apod> response) {
               //Log.d("APOD", response.body().getTitle());
           }

           @Override
           public void onFailure(Call<Apod> call, Throwable t) {

           }
       });
    }
}
