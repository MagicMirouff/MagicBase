package com.magicmirouf.magicbase.webservices.google.models;

/**
 * Created by sylva on 22/08/2016.
 */
public class PlaceDetails {
    public Result result;

    public final static String Ville = "locality";
    public final static String Dpt = "administrative_area_level_2";
    public final static String Region = "administrative_area_level_1";
    public final static String Pays = "country";

    public class Result {
        public AdressComponent[] address_components;
        public Geometry geometry;
        public String name;
        public String place_id;
        public String formatted_address;
        public String formatted_phone_number;
        public String website;
        public String[] types;
        public Photo[] photos;

        public String getArea(String area){
            for (AdressComponent address_component : address_components) {
                if (address_component.types[0].equals(area)){
                    return address_component.long_name;
                }
            }
            switch (area){
                case Ville:
                    return "Ville";
                case Dpt:
                    return "Dpt";
                case Region:
                    return "Région";
                case Pays:
                    return "Pays";
            }
            return null;
        }

        public String getSite() {
            if (website !=null) {
                if (website.contains("http://"))
                    return website.replace("http://", "");
                else
                    return website;
            } else {
                return null;
            }
        }
    }

    public class AdressComponent {
        public String long_name;
        public String short_name;
        public String[] types;
    }

    public class Geometry {
        public Location location;
    }

    public class Location {
        public Double lat;
        public Double lng;

        public boolean isValid(){
            if (lat != null && lng != null && !lat.equals("") && !lng.equals(""))
                return true;
            else
                return false;
        }

        public Double getDoubleLongitude() {
            return Double.valueOf(lng);
        }

        public Double getDoubleLatitude() {
            return Double.valueOf(lat);
        }

        public android.location.Location buildLocation(){
            android.location.Location location = new android.location.Location("");
            location.setLatitude(lat);
            location.setLongitude(lng);
            return location;
        }

        @Override
        public String toString() {
            return lat + "," + lng;
        }
    }

    public class Photo {
        public String photo_reference;
    }

    public boolean isEstablishment() {
        for (String type : result.types) {
            if (type.equals("establishment")){
                return true;
            }
        }
        return false;
    }

}
