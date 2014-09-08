package br.com.dahoraapps.avenuecodetest.data;

import java.util.List;

/**
 * Created by brunodahora on 05/09/14.
 */
public class MapsResponse {

    private List<Place> results;
    private String status;

    public List<Place> getResults() {
        return results;
    }

    public void setResults(List<Place> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
