package silvanet.com.mx.imagine_space.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.ModelFavorites;

/**
 * Created by LuisAlfredoSilva on 06/09/2016.
 */
public class AppDataSource {
    private final SQLiteDatabase db;

    //Obtiene la DB
    public AppDataSource(Context context) {
        MySqlLiteHelper helper = new MySqlLiteHelper(context);
        db = helper.getWritableDatabase();
    }

    //Insertar
    public void saveFav(ModelFavorites modelFavorites){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqlLiteHelper.COLUMN_FAVORITES_FULLNAME,modelFavorites.full_name);
        contentValues.put(MySqlLiteHelper.COLUMN_FAVORITES_IMAGEURI,modelFavorites.image_URI);
        contentValues.put(MySqlLiteHelper.COLUMN_FAVORITES_DATE,modelFavorites.date);
        db.insert(MySqlLiteHelper.TABLE_FAVORITES_NAME,null,contentValues);
    }
    //Borrar
    public void deleteFav(ModelFavorites modelFavorites){
        if(modelFavorites!=null){
            db.delete(MySqlLiteHelper.TABLE_FAVORITES_NAME,MySqlLiteHelper.COLUMN_FAVORITES_ID+"=?",new String[]{String.valueOf(modelFavorites.id)});
        }
    }
    //Actualizar
    public void updateFav(ModelFavorites modelFavorites){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqlLiteHelper.COLUMN_FAVORITES_ID,modelFavorites.id);
        contentValues.put(MySqlLiteHelper.COLUMN_FAVORITES_FULLNAME,modelFavorites.full_name);
        contentValues.put(MySqlLiteHelper.COLUMN_FAVORITES_IMAGEURI,modelFavorites.image_URI);
        contentValues.put(MySqlLiteHelper.COLUMN_FAVORITES_DATE,modelFavorites.date);
        db.update(MySqlLiteHelper.TABLE_FAVORITES_NAME,contentValues,MySqlLiteHelper.COLUMN_FAVORITES_ID+"=?",new String[]{String.valueOf(modelFavorites.id)});
    }
    //Obteniendo los Items presentes en la  DB
    public List<ModelFavorites> getAllFAV(){
        List<ModelFavorites> modelFAVList = new ArrayList<>();
        Cursor cursor=db.query(MySqlLiteHelper.TABLE_FAVORITES_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqlLiteHelper.COLUMN_FAVORITES_ID));
            String FAVFullName = cursor.getString(cursor.getColumnIndexOrThrow(MySqlLiteHelper.COLUMN_FAVORITES_FULLNAME));
            String FAVImageURI = cursor.getString(cursor.getColumnIndexOrThrow(MySqlLiteHelper.COLUMN_FAVORITES_IMAGEURI));
            String FAVDate = cursor.getString(cursor.getColumnIndexOrThrow(MySqlLiteHelper.COLUMN_FAVORITES_DATE));
            ModelFavorites favoritesModel = new ModelFavorites(id,FAVFullName,FAVImageURI,FAVDate);
            modelFAVList.add(favoritesModel);
        }
        return modelFAVList;
    }
}
