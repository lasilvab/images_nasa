package silvanet.com.mx.imagine_space.ApodViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import model.ModelFavorites;
import silvanet.com.mx.imagine_space.R;

/**
 * Created by LuisAlfredoSilva on 06/09/2016.
 */
public class FavoritesViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_apod_image)
    SimpleDraweeView Image;
    @BindView(R.id.item_apod_title)
    TextView Title;
    //Implementación del click
    private FavoritesAdapter.OnItemClickListener onItemListener;
    private ModelFavorites modelFavorites;
    public FavoritesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void setItemClick(ModelFavorites modelFavorites, FavoritesAdapter.OnItemClickListener onItemListener){
        this.modelFavorites = modelFavorites;
        this.onItemListener = onItemListener;
    }
    @OnClick(R.id.item_apod_image)
    public void onViewClick(View view){
        if(onItemListener!=null){
            onItemListener.onItemClick(modelFavorites);
        }
    }
}
