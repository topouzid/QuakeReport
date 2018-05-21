package com.example.android.quakereport;

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

    /** Constructor that has exactly the same name as the class
     * @param location Is the name of the location nearby the earthquake
     * @param time is the date and time of the earthquake
     * @param magnitude is the magnitude of the erthquake in Richter scale (decimal)
     */
    public Earthquake(String location, long time, double magnitude) {
        mLocation = location;
        mTime = time;
        mMagnitude = magnitude;
    }

    /** Method: get the location of the earthquake
     * @return location nearby the epicenter
     */
    public String getLocation() {
        return mLocation;
    }

    /** Method: get the time and date of the earthquake
     * @return date and time in format: Feb 16, 2009
     */
    public long getTime() {
        return mTime;
    }

    /** Method: get the magnitude of the earthquake
     * @return floating point magnitude, richter scale
     */
    public double getMagnitude() {
        return mMagnitude;
    }
}
