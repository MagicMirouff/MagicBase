package com.magicmirouf.magicbase.webservices.google.models;

/**
 * Created by sylva on 29/09/2016.
 */

public class NearbySearch {

    public Result[] results;

    public class Geometry {
        public PlaceDetails.Location location;
    }

    public class Opening_hours {
        public boolean open_now;
        public String[] weekday_text;
    }

    public class Result {
        public String place_id;
        public Geometry geometry;
        public String name;
        public String vicinity;
        public Opening_hours opening_hours;
        public Photo[] photos;
    }

    public class Photo {
        public String photo_reference;
    }
}
