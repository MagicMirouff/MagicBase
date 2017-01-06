package com.magicmirouf.magicbase.navigation.drawer;

/**
 * Created by sylva on 03/06/2016.
 */
public class DrawerItem {

    private String title;
    private String subtitle;
    private int image_id;
    private String data;
    private int data_color;
    private boolean chevron;

    public DrawerItem(String title, String subtitle, int image_id, String data, int data_color, boolean chevron) {
        this.title = title;
        this.subtitle = subtitle;
        this.image_id = image_id;
        this.data = data;
        this.data_color = data_color;
        this.chevron = chevron;
    }

    public String getData_number() {
        return data;
    }

    public void setData_number(String data) {
        this.data = data;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getData_color() {
        return data_color;
    }

    public void setData_color(int data_color) {
        this.data_color = data_color;
    }

    public boolean isChevron() {
        return chevron;
    }

    public void setChevron(boolean chevron) {
        this.chevron = chevron;
    }
}
