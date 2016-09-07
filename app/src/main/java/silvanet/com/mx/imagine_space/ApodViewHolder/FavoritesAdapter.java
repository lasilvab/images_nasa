package silvanet.com.mx.imagine_space.ApodViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import model.ModelFavorites;
import silvanet.com.mx.imagine_space.R;

/**
 * Created by LuisAlfredoSilva on 06/09/2016.
 */
public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {
    private List<ModelFavorites> marsFav;
    private OnItemClickListener onItemClickListener;

    public FavoritesAdapter(){}
    public FavoritesAdapter(List<ModelFavorites> modelFavoritesList){
        this.marsFav = modelFavoritesList;
    }

    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavoritesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_view_item,parent,false));
    }

    @Override
    public void onBindViewHolder(FavoritesViewHolder holder, int position) {
        ModelFavorites modelFavorites = marsFav.get(position);
        holder.Image.setImageURI(modelFavorites.image_URI);
        holder.Title.setText(modelFavorites.date);
        holder.setItemClick(modelFavorites,onItemClickListener);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void setMarsFav(List<ModelFavorites> marsFav){
        this.marsFav = marsFav;
    }

    @Override
    public int getItemCount() {
        return marsFav!=null?marsFav.size():0;
    }
    public interface OnItemClickListener{
        void onItemClick(ModelFavorites modelFavorites);
    }

}
