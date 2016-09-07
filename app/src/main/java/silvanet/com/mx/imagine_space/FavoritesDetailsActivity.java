package silvanet.com.mx.imagine_space;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.ModelFavorites;
import model.Photo;

/**
 * Created by LuisAlfredoSilva on 06/09/2016.
 */
public class FavoritesDetailsActivity extends AppCompatActivity {
    @BindView(R.id.item_id)
    TextView Id;
    @BindView(R.id.item_full_name) TextView Full_Name;
    @BindView(R.id.item_image)
    SimpleDraweeView Image;
    @BindView(R.id.item_date) TextView Date;
    @BindView(R.id.toolbar_detail)
    Toolbar Bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datail);
        ButterKnife.bind(this);


        setSupportActionBar(Bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if(getIntent().getExtras()!=null) {
            ModelFavorites modelFavorites = (ModelFavorites) getIntent().getSerializableExtra("favPhoto");

            Id.setText("Id: " + modelFavorites.id);
            Full_Name.setText("Full Name: " + modelFavorites.full_name.toString());
            Image.setImageURI(modelFavorites.image_URI.toString());
            Date.setText("Date: " + modelFavorites.date.toString());
        }else{
            Toast.makeText(getApplicationContext(),getResources().getText(R.string.error_data),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
