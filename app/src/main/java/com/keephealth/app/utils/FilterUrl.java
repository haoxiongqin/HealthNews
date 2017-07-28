package com.keephealth.app.utils;

/**
 * 描述：
 */
public class FilterUrl {
    private volatile static FilterUrl filterUrl;

    public FilterUrl() {
    }
    public static FilterUrl instance() {
        if (filterUrl == null) {
            synchronized (FilterUrl.class) {
                if (filterUrl == null) {
                    filterUrl = new FilterUrl();
                }
            }
        }
        return filterUrl;
    }
    public String singleListPosition;
    public String doubleListLeft;
    public String doubleListRight;

    public int position;
    public String positionTitle;
    public String titleUrl;

    public String getSingleListPosition() {
        return singleListPosition;
    }

    public void setSingleListPosition(String singleListPosition) {
        this.singleListPosition = singleListPosition;
    }

    public String getDoubleListLeft() {
        return doubleListLeft;
    }

    public void setDoubleListLeft(String doubleListLeft) {
        this.doubleListLeft = doubleListLeft;
    }

    public String getDoubleListRight() {
        return doubleListRight;
    }

    public void setDoubleListRight(String doubleListRight) {
        this.doubleListRight = doubleListRight;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }

    public void clear() {
        filterUrl = null;
    }
}