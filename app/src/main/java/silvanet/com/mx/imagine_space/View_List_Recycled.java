package silvanet.com.mx.imagine_space;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import data.ApodService;
import data.Data;
import model.Apod;
import model.MarsRoverPhotos;
import model.Photo;
import model.Rover;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import silvanet.com.mx.imagine_space.ApodViewHolder.NasaApodAdapter;

/**
 * Created by LuisAlfredoSilva on 05/08/2016.
 */
public class View_List_Recycled extends AppCompatActivity{
    @BindView(R.id.list_recycled) RecyclerView RoverList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycled_view_list);
        //Librería Butter Knife
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(10,StaggeredGridLayoutManager.VERTICAL);

        RoverList.setLayoutManager(gridLayoutManager);

        final NasaApodAdapter nasaApodAdapter = new NasaApodAdapter();
        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener(){

            @Override
            public void OnItemClick(Photo photo) {
                Log.d("MY PHOTO",photo.getImgSrc());
            }
        });

        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);

        Call<MarsRoverPhotos> callMarsRoverPhotos = apodService.getMarsRoverPhotos(1000,"9PLTNIVPFbidnArlHSKsy4jud5PApcRo7RUjfYbv");

        callMarsRoverPhotos.enqueue(new Callback<MarsRoverPhotos>() {
            @Override
            public void onResponse(Call<MarsRoverPhotos> call, Response<MarsRoverPhotos> response) {

                //RoverList.setAdapter(new NasaApodAdapter(response.body().getPhotos()));
                nasaApodAdapter.setMarsPhotos(response.body().getPhotos());
                RoverList.setAdapter(nasaApodAdapter);


                //Log.d("IMAGEN",response.body().getPhotos().get(0).getImgSrc().toString());
            }

            @Override
            public void onFailure(Call<MarsRoverPhotos> call, Throwable t) {

            }
        });
    }
}
