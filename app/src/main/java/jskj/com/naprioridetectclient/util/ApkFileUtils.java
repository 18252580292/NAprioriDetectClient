package jskj.com.naprioridetectclient.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import jskj.com.naprioridetectclient.entry.AppInfo;

public class ApkFileUtils {

    public static AppInfo getApkInfo(Context context, String apkFilePath) {
        if (StringUtils.isEmpty(apkFilePath)) {
            return null;
        }
        AppInfo info = new AppInfo();
        PackageInfo packageInfo = context.getPackageManager().getPackageArchiveInfo(apkFilePath, PackageManager.GET_ACTIVITIES);
        ApplicationInfo appInfo = packageInfo.applicationInfo;
        appInfo.sourceDir = apkFilePath;
        appInfo.publicSourceDir = apkFilePath;
        info.packageName = packageInfo.packageName;
        info.name = appInfo.loadLabel(context.getPackageManager()).toString();
        info.versionCode = packageInfo.versionCode;
        info.versionName = packageInfo.versionName;
        return info;
    }
}
