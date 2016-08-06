package silvanet.com.mx.imagine_space.ApodViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import model.Apod;
import model.MarsRoverPhotos;
import model.Photo;
import silvanet.com.mx.imagine_space.R;

/**
 * Created by LuisAlfredoSilva on 05/08/2016.
 */
public class NasaApodAdapter extends RecyclerView.Adapter<NasaApodViewHolder> {

    private List<Photo> apods;

    public NasaApodAdapter (List<Photo> apods) { this.apods = apods; }

    @Override
    public NasaApodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NasaApodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_view_item,parent,false));
    }

    @Override
    public void onBindViewHolder(NasaApodViewHolder holder, int position) {
        Photo photo = apods.get(position);

        //Picasso.with(holder.Image.getContext()).load(photo.getImgSrc()).into(holder.Image);

        //Ahora utilizando Fresco
        holder.Image.setImageURI(photo.getImgSrc());
        holder.Title.setText(photo.getEarthDate());


    }

    @Override
    public int getItemCount() {
        return apods.size();
    }
}
