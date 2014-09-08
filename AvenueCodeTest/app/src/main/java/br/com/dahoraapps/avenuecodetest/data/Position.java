package br.com.dahoraapps.avenuecodetest.data;

import java.io.Serializable;

/**
 * Created by brunodahora on 05/09/14.
 */
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double lat;
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

}
