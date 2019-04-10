package com.unicornholdings.skillmanagement;


import android.content.Context;

import com.parse.ParseException;
import com.parse.ParseFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class InternalStorage{

    private InternalStorage() {}

    public static void writeObject(Context context, String key, Object object) throws IOException {
        FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }
    public static void writeFile(Context applicationContext, String path, File parseFile, byte[] data) throws IOException, ParseException {
        File directory = new File(path);
        FileOutputStream stream = null;
        File file = parseFile;
        try {
            if(!file.exists()) {
                directory.createNewFile();
            }
            File dataFile = new File(directory, file.getName());
            stream = new FileOutputStream(dataFile, true); // true if append is required.
            stream.write(data);
            stream.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (null != stream) {
                stream.close();
            }
        }
    }
    public static Object readObject(Context context, String key) throws IOException,
            ClassNotFoundException {
        FileInputStream fis =  context.getApplicationContext().openFileInput(key);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }
    public static Object deleteCache(Context context, String key) {
        String path = context.getFilesDir().getAbsolutePath()+"/"+key;
        File file = new File ( path);
        return file.delete();

    }


}
