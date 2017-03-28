package jskj.com.naprioridetectclient.entry;

/**
 * Created by cui on 17-3-26.
 */

public class AppInfo {
    public int id;
    public String name;
    public String versionName;
    public int versionCode;
    public String packageName;
    public String permissionFamilly;
    public int normalApp;
    public String appMd5;

    public AppInfo(int id, String name, String versionName, int versionCode, String permissionFamilly, int normalApp, String appMd5) {
        this.id = id;
        this.name = name;
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.permissionFamilly = permissionFamilly;
        this.normalApp = normalApp;
        this.appMd5 = appMd5;
    }

    public AppInfo() {}
}
