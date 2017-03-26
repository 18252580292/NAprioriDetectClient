package jskj.com.naprioridetectclient.entry;

/**
 * Created by cui on 17-3-26.
 */

public class AppInfo {
    public int id;
    public String name;
    public String permissionFamilly;
    public int normalApp;
    public String appMd5;

    public AppInfo(int id, String name, String permissionFamilly, int normalApp, String appMd5) {
        this.id = id;
        this.name = name;
        this.permissionFamilly = permissionFamilly;
        this.normalApp = normalApp;
        this.appMd5 = appMd5;
    }
    public AppInfo() {}
}
