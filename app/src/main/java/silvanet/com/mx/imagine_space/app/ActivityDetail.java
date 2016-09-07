package silvanet.com.mx.imagine_space.app;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.ModelFavorites;
import model.Photo;
import silvanet.com.mx.imagine_space.R;
import silvanet.com.mx.imagine_space.sql.AppDataSource;

/**
 * Created by LuisAlfredoSilva on 12/08/2016.
 */
public class ActivityDetail extends AppCompatActivity {
    @BindView(R.id.item_id) TextView Id;
    @BindView(R.id.item_full_name) TextView Full_Name;
    @BindView(R.id.item_image) SimpleDraweeView Image;
    @BindView(R.id.item_date) TextView Date;
    @BindView(R.id.toolbar_detail)
    Toolbar Bar;

    private AppDataSource favoritesDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datail);
        ButterKnife.bind(this);

        favoritesDataSource = new AppDataSource(getApplicationContext());
        setSupportActionBar(Bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if(getIntent().getExtras()!=null) {
            Photo photo = (Photo) getIntent().getExtras().getSerializable("photopar");
            Id.setText("Id: " + photo.getId().toString());
            Full_Name.setText("Full Name: " + photo.getCamera().getFullName());
            Image.setImageURI(photo.getImgSrc());
            Date.setText("Date: " + photo.getEarthDate());
        }else{
            Toast.makeText(getApplicationContext(),getResources().getText(R.string.error_data),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorites_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_favorites:
                 // Agrega datos a favoritos
                addFavorites();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void addFavorites() {
        String cFull_Name = Full_Name.getText().toString();
        //Image.setImageURI(photo.getImgSrc());
        //String cImage = Image.toString();
        String cImage = Date.getText().toString();
        String cDate = Date.getText().toString();
        ModelFavorites modelFavorites = new ModelFavorites(0,cFull_Name,cImage,cDate);
        favoritesDataSource.saveFavorites(modelFavorites);
        Snackbar.make(findViewById(android.R.id.content),getResources().getText(R.string.AddFavorites),Snackbar.LENGTH_SHORT).show();
    }


}
