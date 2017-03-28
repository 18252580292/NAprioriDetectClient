package jskj.com.naprioridetectclient;


import android.app.Application;
import android.content.Context;

public class NAprioriApplication extends Application {
    private static Context sContext;

    public static Context getContext() {
        if (sContext != null) {
            return sContext;
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this.getApplicationContext();
    }
}
