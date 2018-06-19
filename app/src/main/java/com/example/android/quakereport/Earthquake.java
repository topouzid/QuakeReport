package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * {@link Earthquake} represents an earthquake with its data.
 * It contains the earthquake location, time and magnitude.
 */
public class Earthquake {

    /** Location of the earthquake */
    private String mLocation;

    /** Timestamp of the earthquake */
    private long mTime;

    /** Magnitude of the earthquake */
    private double mMagnitude;

    /** URL of the earthquake */
    private String mUrl;

    /** Constructor that has exactly the same name as the class
     * @param location Is the name of the location nearby the earthquake
     * @param time is the date and time of the earthquake
     * @param magnitude is the magnitude of the erthquake in Richter scale (decimal)
     */
    public Earthquake(String location, long time, double magnitude, String url) {
        mLocation = location;
        mTime = time;
        mMagnitude = magnitude;
        mUrl = url;
    }

    /** Method: get the location of the earthquake
     * @return location nearby the epicenter
     */
    public String getLocation() {
        return mLocation;
    }

    /** Method: get the time and date of the earthquake
     * @return date and time in UNIX timecode format
     */
    public long getTime() {
        return mTime;
    }

    /** Method: get the date of the earthquake
     * @return date in format: Feb 16, 2009
     */
    public String getSimpleDate() {
        Date dateObject = new Date(mTime);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormatter.format(dateObject);
    }

    /** Method: get the time of the earthquake
     * @return time in format: 9:18 AM
     */
    public String getSimpleTime() {
        Date dateObject = new Date(mTime);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a");
        return dateFormatter.format(dateObject);
    }

    /** Method: get the magnitude of the earthquake
     * @return floating point magnitude, richter scale
     */
    public double getMagnitude() {
        return mMagnitude;
    }

    /** Method: get the URL of the earthquake
     * @return web link of the earthquake event
     */
    public String getUrl() {
        return mUrl;
    }
}
