package br.com.dahoraapps.avenuecodetest.data;

import java.util.List;

/**
 * Created by brunodahora on 05/09/14.
 */
public class MapsResponse {

    private List<Place> result;
    private String status;

    public List<Place> getResult() {
        return result;
    }

    public void setResult(List<Place> result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
