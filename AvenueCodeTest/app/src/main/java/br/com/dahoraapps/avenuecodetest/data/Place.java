package br.com.dahoraapps.avenuecodetest.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("address_components")
    private List<AddressComponent> addressComponents;
    @SerializedName("formatted_address")
    private String formattedAddress;
    private Geometry geometry;
    private List<String> types;

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

}
