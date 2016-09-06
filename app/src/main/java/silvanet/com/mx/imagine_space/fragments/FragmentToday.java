package silvanet.com.mx.imagine_space.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import data.ApodService;
import data.Data;
import model.Apod;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import silvanet.com.mx.imagine_space.R;

/**
 * Created by LuisAlfredoSilva on 12/08/2016.
 */
public class FragmentToday extends Fragment{
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.text) TextView text;
    String img_url_share;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if(getArguments() != null){
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_today,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);

        Call<Apod> callApodService = apodService.getTodayApodWhithQuery("9PLTNIVPFbidnArlHSKsy4jud5PApcRo7RUjfYbv");

        callApodService.enqueue(new Callback<Apod>() {
            @Override
            public void onResponse(Call<Apod> call, Response<Apod> response) {
                Picasso.with(getActivity()).load(response.body().getUrl()).into(image);
                date.setText(response.body().getDate().toString());
                title.setText(response.body().getTitle().toString());
                text.setText(response.body().getExplanation().toString());
                img_url_share=response.body().getUrl();

            }

            @Override
            public void onFailure(Call<Apod> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.apod_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.apod_menu_today_item:
                //shareText("Diplomado Aplicaciones móviles " + img_url_share);
                shareText(getResources().getText(R.string.share_img)+" " + img_url_share);
                //Snackbar.make(getView(),"Share",Snackbar.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void shareText(String text){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,text);
        startActivity(Intent.createChooser(shareIntent,getResources().getText(R.string.txtShare)));

    }
}
