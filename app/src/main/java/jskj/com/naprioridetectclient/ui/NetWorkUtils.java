package jskj.com.naprioridetectclient.ui;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by xuecheng.cui on 2017/3/28.
 */

public class NetWorkUtils {

    /**
     * 判断网络是否可用
     * @param context
     * @return true 网络可用 flase 网络不可用
     */
    public static boolean isNetWorkAvailable(Context context) {
        ConnectivityManager ctMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return ctMgr.getActiveNetworkInfo().isAvailable();
    }
}
