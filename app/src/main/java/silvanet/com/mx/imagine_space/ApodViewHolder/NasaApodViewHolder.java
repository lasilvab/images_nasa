package silvanet.com.mx.imagine_space.ApodViewHolder;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
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


    public NasaApodViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
