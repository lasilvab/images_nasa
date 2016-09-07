package silvanet.com.mx.imagine_space.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by LuisAlfredoSilva on 06/09/2016.
 */
public class MySqlLiteHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "favorites_db";
    private static final int DATABASE_VERSION = 1;

    //Tabla para almacenar  favoritos
    public static final String TABLE_FAVORITES_NAME="favorites_table";
    public static final String COLUMN_FAVORITES_ID= BaseColumns._ID;
    public static final String COLUMN_FAVORITES_FULLNAME= "full_name";
    public static final String COLUMN_FAVORITES_IMAGEURI= "image_URI";
    public static final String COLUMN_FAVORITES_DATE= "date";

    public static final String CREATE_FAVORITES_TABLE = "create table "+TABLE_FAVORITES_NAME+
            "("+COLUMN_FAVORITES_ID+" integer primary key autoincrement,"+
            COLUMN_FAVORITES_FULLNAME+" text null,"+
            COLUMN_FAVORITES_IMAGEURI+ " text null,"+
            COLUMN_FAVORITES_DATE+ " text null)";

    public MySqlLiteHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

