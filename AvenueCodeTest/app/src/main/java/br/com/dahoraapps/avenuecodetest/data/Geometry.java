package br.com.dahoraapps.avenuecodetest.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by brunodahora on 05/09/14.
 */
public class Geometry implements Serializable {

    private static final long serialVersionUID = 1L;

    private Cardinal bounds;
    private Position location;
    @SerializedName("location_type")
    private String locationType;
    private Cardinal viewport;

    public Cardinal getBounds() {
        return bounds;
    }

    public void setBounds(Cardinal bounds) {
        this.bounds = bounds;
    }

    public Position getLocation() {
        return location;
    }

    public void setLocation(Position location) {
        this.location = location;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Cardinal getViewport() {
        return viewport;
    }

    public void setViewport(Cardinal viewport) {
        this.viewport = viewport;
    }

}
