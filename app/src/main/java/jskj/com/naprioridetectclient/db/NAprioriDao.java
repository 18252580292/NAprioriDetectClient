package jskj.com.naprioridetectclient.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import jskj.com.naprioridetectclient.contant.DBConstant;
import jskj.com.naprioridetectclient.entry.AppInfo;

public class NAprioriDao {
    private static final String TAG = "NAprioriDao";
    private static NAprioriDao mInstance;
    private static SQLiteDatabase mDb = null;
    private NAprioriDao() {
    }

    public static NAprioriDao getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NAprioriDao();
            init(context);
        }
        return mInstance;
    }

    private static void init(Context context) {
        NAprioriOpenHelper helper = new NAprioriOpenHelper(context);
        mDb = helper.getWritableDatabase();
    }

    /**
     * 将检测完成的app保存到本地数据库中
     *
     * @param info
     * @return
     */
    public long save(AppInfo info) {
        if (mDb != null) {
            ContentValues values = new ContentValues();
            values.put(DBConstant.NAME, info.name);
            values.put(DBConstant.PERM_FAM, info.permissionFamilly);
            values.put(DBConstant.NORMAL_APP, info.normalApp);
            values.put(DBConstant.APP_MD5, info.appMd5);
            return mDb.insert(DBConstant.TABLE_NAME, null, values);
        }
        Log.e(TAG, "sqlitedatabase is null,so not save data!!!");
        return -1;
    }

    public long delete(AppInfo info) {
        if (mDb != null) {
            return mDb.delete(DBConstant.TABLE_NAME, "name = ?", new String[]{info.name});
        }
        Log.e(TAG, "sqlitedatabase is null,so not delete data!!!");
        return -1;
    }

    public AppInfo queryOne(String name) {
        if (mDb != null) {
            Cursor cursor = mDb.query(DBConstant.TABLE_NAME, null, DBConstant.NAME + "=?", new String[]{name}, null, null, null);
            if(!cursor.moveToNext()) return null;
            AppInfo info = new AppInfo();
            info.id = cursor.getInt(cursor.getColumnIndex(DBConstant.ID));
            info.name = cursor.getString(cursor.getColumnIndex(DBConstant.NAME));
            info.permissionFamilly = cursor.getString(cursor.getColumnIndex(DBConstant.PERM_FAM));
            info.normalApp = cursor.getInt(cursor.getColumnIndex(DBConstant.NORMAL_APP));
            info.appMd5 = cursor.getString(cursor.getColumnIndex(DBConstant.APP_MD5));
            return info;
        }
        return null;
    }

    public List<AppInfo> queryAll() {
        if (mDb == null) {
            return null;
        }
        Cursor cursor = mDb.query(DBConstant.TABLE_NAME, null, null, null, null, null, null);
        List<AppInfo> infos = new ArrayList<>();
        while (cursor.moveToNext()) {
            AppInfo info = new AppInfo();
            info.id = cursor.getInt(cursor.getColumnIndex(DBConstant.ID));
            info.name = cursor.getString(cursor.getColumnIndex(DBConstant.NAME));
            info.permissionFamilly = cursor.getString(cursor.getColumnIndex(DBConstant.PERM_FAM));
            info.normalApp = cursor.getInt(cursor.getColumnIndex(DBConstant.NORMAL_APP));
            info.appMd5 = cursor.getString(cursor.getColumnIndex(DBConstant.APP_MD5));
            infos.add(info);
        }
        return infos;
    }
}
