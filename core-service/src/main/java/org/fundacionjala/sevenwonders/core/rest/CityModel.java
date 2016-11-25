package org.fundacionjala.sevenwonders.core.rest;

/**
 * Created by ubuntu on 25-11-16.
 */
public class CityModel {
    private String name;
    private WonderModel wonder;
    private StoragePointModel storagePoint;

    public CityModel() {}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StoragePointModel getStoragePoint() {
        return storagePoint;
    }

    public void setStoragePoint(StoragePointModel storagePoint) {
        this.storagePoint = storagePoint;
    }

    public WonderModel getWonder() {
        return wonder;
    }

    public void setWonder(WonderModel wonder) {
        this.wonder = wonder;
    }
}
