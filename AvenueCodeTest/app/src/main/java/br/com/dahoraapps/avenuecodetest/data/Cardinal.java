package br.com.dahoraapps.avenuecodetest.data;

/**
 * Created by brunodahora on 05/09/14.
 */
public class Cardinal {

    private Position northeast;
    private Position southwest;

    public Position getNortheast() {
        return northeast;
    }

    public void setNortheast(Position northeast) {
        this.northeast = northeast;
    }

    public Position getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Position southwest) {
        this.southwest = southwest;
    }

}
