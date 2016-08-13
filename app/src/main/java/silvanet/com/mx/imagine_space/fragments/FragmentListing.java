package silvanet.com.mx.imagine_space.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import data.ApodService;
import data.Data;
import model.MarsRoverPhotos;
import model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import silvanet.com.mx.imagine_space.ApodViewHolder.NasaApodAdapter;
import silvanet.com.mx.imagine_space.R;
import silvanet.com.mx.imagine_space.View_List_Recycled;
import silvanet.com.mx.imagine_space.app.ActivityDetail;

/**
 * Created by LuisAlfredoSilva on 12/08/2016.
 */
public class FragmentListing extends Fragment {
    @BindView(R.id.list_recycled)
    RecyclerView RoverList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_listing,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(10,StaggeredGridLayoutManager.VERTICAL);

        RoverList.setLayoutManager(gridLayoutManager);

        final NasaApodAdapter nasaApodAdapter = new NasaApodAdapter();
        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener(){

            @Override
            public void OnItemClick(Photo photo) {
                Intent intent = new Intent(getActivity(), ActivityDetail.class);
                intent.putExtra("photopar",photo);
                startActivity(intent);


                Log.d("id",photo.getId().toString());
                Log.d("Full Name",photo.getCamera().getFullName());
                Log.d("MY PHOTO",photo.getImgSrc());
                Log.d("DATE",photo.getEarthDate());
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
