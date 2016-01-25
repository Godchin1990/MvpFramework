package com.ziyou.tourGuide.helper;

/**
 * Created by Edward on 16/1/25.
 */
/*  * 文 件 名:  DataCleanManager.java  * 描    述:  主要功能有清除内/外缓存，清除数据库，清除sharedPreference，清除files和清除自定义目录  */

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.ziyou.tourGuide.R;

import java.io.File;

/** * 本应用数据清除管理器 */
public class DataCleanHelper {
    private static volatile DataCleanHelper helper;
    private DataCleanHelper(){

    }
    public static DataCleanHelper getInstance(){
        if(helper==null){
            synchronized (DataCleanHelper.class){
                if (helper==null){
                    helper = new DataCleanHelper();
                }
            }
        }
        return helper;
    }
    /** * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * * @param context */
    public void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }

    /** * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * * @param context */
    public void cleanDatabases(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/databases"));
    }

    /**
     * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) * * @param
     * context
     */
    public void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/shared_prefs"));
    }

    /** * 按名字清除本应用数据库 * * @param context * @param dbName */
    public void cleanDatabaseByName(Context context, String dbName) {
        context.deleteDatabase(dbName);
    }

    /** * 清除/data/data/com.xxx.xxx/files下的内容 * * @param context */
    public void cleanFiles(Context context) {
        deleteFilesByDirectory(context.getFilesDir());
    }

    /**
     * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache) * * @param
     * context
     */
    public void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    /** * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * * @param filePath */
    public void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    /** * 清除本应用所有的数据 * * @param context * @param filepath */
    public void cleanApplicationData(Context context, String... filepath) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanSharedPreference(context);
        cleanFiles(context);
        for (String filePath : filepath) {
            cleanCustomCache(filePath);
        }
    }

    /** * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory */
    private void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                if(item.isDirectory()){
                    deleteFilesByDirectory(item);
                    if(directory.listFiles().length==0){
                        item.delete();
                    }
                }else {
                    item.delete();
                }
            }
        }
    }

    /**
     * 删除当前应用正在用到的缓存
     * @param context
     */
    public void cleanAppCache(Context context){
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/app_webview"));
        cleanInternalCache(context);
        Toast.makeText(context, context.getResources().getString(R.string.clear_cache_success), Toast.LENGTH_SHORT).show();
    }
}
