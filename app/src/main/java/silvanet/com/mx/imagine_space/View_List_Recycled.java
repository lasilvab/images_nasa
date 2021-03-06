package silvanet.com.mx.imagine_space;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
import silvanet.com.mx.imagine_space.app.ActivityDetail;
import silvanet.com.mx.imagine_space.fragments.FragmentFavorites;
import silvanet.com.mx.imagine_space.fragments.FragmentListing;
import silvanet.com.mx.imagine_space.fragments.FragmentToday;

/**
 * Created by LuisAlfredoSilva on 05/08/2016.
 */
public class View_List_Recycled extends AppCompatActivity{
    //@BindView(R.id.list_recycled) RecyclerView RoverList;
    @BindView(R.id.listing_toolbar) Toolbar toolbar;
    @BindView(R.id.listing_navigation_view)
    NavigationView navigationView;
    @BindView(R.id.listing_navigation_drawer)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing_navigation_activity);
        //setContentView(R.layout.recycled_view_list);
        //Librería Butter Knife
        ButterKnife.bind(this);

        /* Código para generar el Hasskey de facebook

        try {
            PackageInfo info = getPackageManager().getPackageInfo("silvanet.com.mx.imagine_space",PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
            } catch (PackageManager.NameNotFoundException e) {

            } catch (NoSuchAlgorithmException e) {

            }
        */

        setSupportActionBar(toolbar);
        getFBUserInfo();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.today_item:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentToday()).commit();
                        break;
                    case R.id.masr_rover_item:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentListing()).commit();
                        break;
                    case R.id.favorite_item:
                        //Snackbar.make(findViewById(android.R.id.content),"Favorites",Snackbar.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentFavorites()).commit();
                        break;
                }
                return false;
            }

            });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name){
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
            }
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        /* LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(10,StaggeredGridLayoutManager.VERTICAL);

        RoverList.setLayoutManager(gridLayoutManager);

        final NasaApodAdapter nasaApodAdapter = new NasaApodAdapter();
        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener(){

            @Override
            public void OnItemClick(Photo photo) {
                Intent intent = new Intent(View_List_Recycled.this, ActivityDetail.class);
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
        }); */
    }

    private void getFBUserInfo(){
        GraphRequest request=GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    SimpleDraweeView userImage =(SimpleDraweeView)findViewById(R.id.FB_image_usr);
                    userImage.setImageURI("http://graph.facebook.com/"+object.getString("id")+"/picture?type=large");
                    TextView userName = (TextView) findViewById(R.id.FB_name_usr);
                    userName.setText(object.getString("name"));


                    //Log.d("name",object.getString("name"));
                    //Log.d("id",object.getString("id"));
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
        request.executeAsync();
    }
}
