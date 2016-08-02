package silvanet.com.mx.imagine_space;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import data.ApodService;
import data.Data;
import model.Apod;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ImageView image;
    private TextView date;
    private TextView title;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.image);
        date = (TextView) findViewById(R.id.date);
        title = (TextView) findViewById(R.id.title);
        text = (TextView) findViewById(R.id.text);


        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);

        // AQUÍ LE ESTAMOS PASANDO LA api key con el método getTodayApod

        //Call<Apod> callApodService = apodService.getTodayApod();

        Call<Apod> callApodService = apodService.getTodayApodWhithQuery("9PLTNIVPFbidnArlHSKsy4jud5PApcRo7RUjfYbv");

       callApodService.enqueue(new Callback<Apod>() {
           @Override
           public void onResponse(Call<Apod> call, Response<Apod> response) {
               Picasso.with(getApplicationContext()).load(response.body().getUrl()).into(image);
               date.setText(response.body().getDate().toString());
               title.setText(response.body().getTitle().toString());
               text.setText(response.body().getExplanation().toString());


               //Log.d("APOD", response.body().getTitle());
           }

           @Override
           public void onFailure(Call<Apod> call, Throwable t) {

           }
       });
    }
}
