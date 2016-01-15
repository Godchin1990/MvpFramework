package com.ziyou.tourGuide.helper;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

/**
 * Created by Edward on 15/12/12.
 */
public class UUIDHelpter {
    private static volatile UUIDHelpter helpter;
    private UUIDHelpter(){

    }
    public static UUIDHelpter getInstance(){
        if(helpter==null){
            synchronized (UUIDHelpter.class){
                if(helpter==null){
                    helpter = new UUIDHelpter();
                }
            }
        }
        return helpter;
    }

    private static final String INSTALLATION = "INSTALLATION";
    private String sUUID = null;
    public synchronized String getUUID(Context context){

        File installation = new File(context.getFilesDir(), INSTALLATION);
        if (sUUID == null) {
            try {
                if (!installation.exists())
                    writeInstallationFile(installation);
                sUUID = readInstallationFile(installation);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sUUID;
    }

    private static void writeInstallationFile(File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = UUID.randomUUID().toString();
        out.write(id.getBytes());
        out.close();
    }

    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

}
