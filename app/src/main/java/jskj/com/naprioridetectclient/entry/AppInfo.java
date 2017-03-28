package jskj.com.naprioridetectclient.entry;

public class AppInfo {
    public int id;
    //应用的名字，一般是从待检测应用的apk文件中获取的
    public String name;
    //版本名称
    public String versionName;
    public int versionCode;
    //包名
    public String packageName;
    //待检测应用申请的所有权限
    public String permissionFamilly;
    //是否是正常应用：0 代表代表恶意应用;1代表正常应用;-1代表未知，它只会出现在本地检测中
    public int normalApp;
    //md5值
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
