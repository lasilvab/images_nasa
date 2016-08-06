package silvanet.com.mx.imagine_space;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import data.ApodService;
import data.Data;
import model.Apod;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Utilizando ahora la librería Butter Knife
    @BindView(R.id.image) ImageView image;
    @BindView(R.id.date) TextView date;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.text) TextView text;

    @OnClick(R.id.envia_recycler)
    public void envia_recycler(){
        Intent intent = new Intent(getApplicationContext(),View_List_Recycled.class);
        startActivity(intent);
    }

    /* private ImageView image;
    private TextView date;
    private TextView title;
    private TextView text;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Librería Butter Knife
        ButterKnife.bind(this);

        /*
        image = (ImageView) findViewById(R.id.image);
        date = (TextView) findViewById(R.id.date);
        title = (TextView) findViewById(R.id.title);
        text = (TextView) findViewById(R.id.text);
        */


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
