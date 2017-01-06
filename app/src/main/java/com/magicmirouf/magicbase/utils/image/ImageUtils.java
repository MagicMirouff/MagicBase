package com.magicmirouf.magicbase.utils.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by MagicMirouf on 02/01/2017.
 */

public class ImageUtils {
    public static File BitmapToFile(Activity activity,String filePath,Uri uri){
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File fileImage = new File(activity.getFilesDir(), filePath);

        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(fileImage);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileImage;
    }

}
