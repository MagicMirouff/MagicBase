package com.magicmirouf.magicbase.utils.intents;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.widget.Toast;

/**
 * Created by MagicMirouf on 12/12/2016.
 */

public class IntentBasics {

    public static void share(Context context,String message){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,message);
        sendIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sendIntent, "Share"));
    }

    public static void addEvent(Context context, String title, String location, long begin) {
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin);
                //.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static void dialPhoneNumber(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static void composeEmail(Context context,String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static void showActionView(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (!url.contains("http://") && !url.contains("https://"))
            url = "http://" + url;
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }


    public static String getCityMapperURL(String latitude, String longitude) {
        return  "http://citymapper.com/directions?endcoord="+latitude+"%2C"+longitude;
    }

    public static String getUberURL() {
        return "https://m.uber.com/ul/?action=setPickup";
    }

    public static void showGoogleMaps(Context context, String latitude, String longitude,String name) {
        Uri gmmIntentUri;
        if (name!=null){
            gmmIntentUri = Uri.parse("geo:"+latitude+","+longitude+"?q=" + Uri.encode(name));//Uri.parse("geo:" + latitude +","+longitude);
        }else {
            gmmIntentUri = Uri.parse("geo:" + latitude +","+longitude);
        }
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        } else {
            Toast.makeText(context,"Google Maps n'est pas installé",Toast.LENGTH_SHORT).show();
        }
    }

    public static void showGoogleMapsNavigation(Context context, String latitude, String longitude) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitude +","+longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        } else {
            Toast.makeText(context,"Google Maps n'est pas installé",Toast.LENGTH_SHORT).show();
        }
    }
}
