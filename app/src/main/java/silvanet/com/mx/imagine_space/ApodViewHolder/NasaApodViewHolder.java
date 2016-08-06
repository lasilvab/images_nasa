package silvanet.com.mx.imagine_space.ApodViewHolder;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import model.Photo;
import silvanet.com.mx.imagine_space.R;

/**
 * Created by LuisAlfredoSilva on 05/08/2016.
 */
public class NasaApodViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_apod_image)
    SimpleDraweeView Image;
    //ImageView Image;
    @BindView(R.id.item_apod_title)
    TextView Title;

    private NasaApodAdapter.OnItemClickListener onItemListener;
    private Photo photo;

    public NasaApodViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setItemClick(Photo photo, NasaApodAdapter.OnItemClickListener onItemListener){
        this.photo = photo;
        this.onItemListener = onItemListener;
    }

    @OnClick(R.id.item_apod_image)
    public void onViewClick(View view){
        if(onItemListener!=null){
            onItemListener.OnItemClick(photo);
        }
    }


}
