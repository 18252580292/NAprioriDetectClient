package jskj.com.naprioridetectclient.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import jskj.com.naprioridetectclient.contant.DBConstant;

public class NAprioriOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "NAprioriOpenHelper";
    private static String sql;

    static {
        sql = "create table " + DBConstant.TABLE_NAME +
                " (" +
                DBConstant.ID + " integer primary key autoincrement," +
                DBConstant.NAME + " text," +
                DBConstant.PERM_FAM + " text," +
                DBConstant.VERSION_NAME + " text," +
                DBConstant.VERSION_CODE + " integer," +
                DBConstant.NORMAL_APP + " int," +
                DBConstant.APP_MD5 +
                ")";
    }

    public NAprioriOpenHelper(Context context) {
        super(context, DBConstant.DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table xxx (id primary key autoincrement,name text, PERM_FAM text,NORMAL_APP bool,
        // md5 text)
        Log.d(TAG, "create sql :" + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.delete(DBConstant.TABLE_NAME, null, null);
            db.execSQL(sql);
        } else {
            Log.d(TAG, "db version is not update");
        }
    }
}
