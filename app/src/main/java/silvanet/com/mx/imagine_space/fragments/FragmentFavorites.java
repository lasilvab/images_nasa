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
import silvanet.com.mx.imagine_space.FavoritesDetailsActivity;
import silvanet.com.mx.imagine_space.R;
import silvanet.com.mx.imagine_space.sql.AppDataSource;

/**
 * Created by LuisAlfredoSilva on 06/09/2016.
 */
public class FragmentFavorites extends Fragment {
    @BindView(R.id.favoriteListing)
    RecyclerView marsRoverListRecycler_Favorites;
    String img;
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

        marsRoverListRecycler_Favorites.setLayoutManager(gridLayoutManager);
        final FavAdapter favAdapter = new FavAdapter();
        favAdapter.setOnItemClickListener(new FavAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FavoritesModel favoritesModel) {
                Intent intent = new Intent(getActivity(),FavDetailsActivity.class);
                intent.putExtra("favPhoto",favoritesModel);
                startActivity(intent);
            }
        });
        appDataSource = new AppDataSource(getActivity());
        List<ModelFavorites> favoritesModelList = appDataSource.getAllFAV();
        if(!favoritesModelList.isEmpty()) {
            favAdapter.setMarsFav(favoritesModelList);
            marsRoverListRecycler_Favorites.setAdapter(favAdapter);
        }else{
            Snackbar.make(getView(),getResources().getText(R.string.NoAddFavirites),Snackbar.LENGTH_SHORT).show();
            //Snackbar.make(getView(),"No data added to favorites",Snackbar.LENGTH_SHORT).show();
        }
    }
}
