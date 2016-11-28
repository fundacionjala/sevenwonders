package org.fundacionjala.sevenwonders.core.rest;

import org.fundacionjala.sevenwonders.core.Building;
import org.fundacionjala.sevenwonders.core.BuildingType;

/**
 * Created by Gonzalo Calle on 28/11/2016.
 */
public class BuildingModel {
    private String name;
    private BuildingType buildingType;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public BuildingType getBuildingType(){
        return buildingType;
    }
    public void setName(BuildingType buildingType){
        this.buildingType = buildingType;
    }

}
