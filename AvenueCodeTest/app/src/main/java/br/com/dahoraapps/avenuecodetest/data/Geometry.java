package br.com.dahoraapps.avenuecodetest.data;

/**
 * Created by brunodahora on 05/09/14.
 */
public class Geometry {

    private Cardinal bounds;
    private String locationType;
    private Cardinal viewport;

    public Cardinal getBounds() {
        return bounds;
    }

    public void setBounds(Cardinal bounds) {
        this.bounds = bounds;
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
