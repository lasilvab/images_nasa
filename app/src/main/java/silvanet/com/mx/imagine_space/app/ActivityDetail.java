package silvanet.com.mx.imagine_space.app;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Photo;
import silvanet.com.mx.imagine_space.R;

/**
 * Created by LuisAlfredoSilva on 12/08/2016.
 */
public class ActivityDetail extends AppCompatActivity {
    @BindView(R.id.item_id) TextView Id;
    @BindView(R.id.item_full_name) TextView Full_Name;
    @BindView(R.id.item_image) SimpleDraweeView Image;
    @BindView(R.id.item_date) TextView Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datail);
        ButterKnife.bind(this);

        Photo photo = (Photo) getIntent().getExtras().getSerializable("photopar");
        Id.setText(photo.getId());
        Full_Name.setText(photo.getCamera().getFullName());
        Image.setImageURI(photo.getImgSrc());
        Date.setText(photo.getEarthDate());

    }
}
