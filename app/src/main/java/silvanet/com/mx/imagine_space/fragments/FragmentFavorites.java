package silvanet.com.mx.imagine_space.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.ModelFavorites;
import silvanet.com.mx.imagine_space.ApodViewHolder.FavoritesAdapter;
import silvanet.com.mx.imagine_space.FavoritesDetailsActivity;
import silvanet.com.mx.imagine_space.R;
import silvanet.com.mx.imagine_space.sql.AppDataSource;

/**
 * Created by LuisAlfredoSilva on 06/09/2016.
 */
public class FragmentFavorites extends Fragment {
    @BindView(R.id.favoritesListing)
    RecyclerView RoverList_Favorites;
    private AppDataSource appDataSource;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if(getArguments()!=null){
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_favorites,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(10,StaggeredGridLayoutManager.VERTICAL);

        RoverList_Favorites.setLayoutManager(gridLayoutManager);

        final FavoritesAdapter favoritesAdapter = new FavoritesAdapter();
        favoritesAdapter.setOnItemClickListener(new FavoritesAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(ModelFavorites modelFavorites) {
                Intent intent = new Intent(getActivity(),FavoritesDetailsActivity.class);
                intent.putExtra("favPhoto",modelFavorites);
                startActivity(intent);
            }
        });

        appDataSource = new AppDataSource(getActivity());

        List<ModelFavorites> modelFavoritesList = appDataSource.getAllFAV();
        if(!modelFavoritesList.isEmpty()) {
            favoritesAdapter.setMarsFav(modelFavoritesList);
            RoverList_Favorites.setAdapter(favoritesAdapter);
        }else{
            Snackbar.make(getView(),getResources().getText(R.string.NoAddFavirites),Snackbar.LENGTH_SHORT).show();
        }
    }
}
