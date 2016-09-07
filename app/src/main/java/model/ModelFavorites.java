package model;

import java.io.Serializable;

/**
 * Created by LuisAlfredoSilva on 06/09/2016.
 */
public class ModelFavorites implements Serializable {
    public int id;
    public String full_name;
    public String image_URI;
    public String date;

    public ModelFavorites (int id, String full_name, String image_URI, String date){
        this.id = id;
        this.full_name = full_name;
        this.image_URI = image_URI;
        this.date = date;
    }
}
