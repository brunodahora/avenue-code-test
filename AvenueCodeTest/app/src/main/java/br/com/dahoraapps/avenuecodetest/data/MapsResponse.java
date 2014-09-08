package br.com.dahoraapps.avenuecodetest.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by brunodahora on 05/09/14.
 */
public class MapsResponse implements Serializable {

    private static final long serialVersionUID = 1L;

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
