package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.core.effect.Effect;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * @author Gonzalo Calle
 */
public class BuildingRequirementsTest {

    private static final int PLAYERS_NEEDED = 4;
    private static final int FIRST_AGE = 1;
    private List<Effect> effects;

    private List<Requirement> gatesRequirements;
    private List<Requirement> templeRequirements;
    private List<Requirement> bathsRequirements;


    private List<Building> templeBuildingRequirementList;

    private Requirement templeBuildingRequirements;

    private Building gatesBuilding;
    private Building templeBuilding;
    private Building bathsBuilding;

    private City city;

    @Before
    public void setUp() {

        effects = new ArrayList<>();

        templeBuildingRequirementList = new ArrayList<>();
        templeBuildingRequirements = new BuildingRequirement(templeBuildingRequirementList);

        templeRequirements = new ArrayList<>();
        templeRequirements.add(mock(ResourceRequirement.class));
        templeRequirements.add(templeBuildingRequirements);

        gatesRequirements = new ArrayList<>();
        gatesRequirements.add(mock(ResourceRequirement.class));
        gatesRequirements.add(mock(BuildingRequirement.class));

        bathsRequirements = new ArrayList();
        bathsRequirements.add(mock(ResourceRequirement.class));
        bathsRequirements.add(mock(BuildingRequirement.class));

        templeBuilding = new Building(templeRequirements, effects, PLAYERS_NEEDED, FIRST_AGE, "Temple", BuildingType.CIVIC);
        gatesBuilding = new Building(gatesRequirements, effects, PLAYERS_NEEDED, FIRST_AGE, "Gates of the city", BuildingType.CIVIC);
        bathsBuilding = new Building(bathsRequirements, effects, PLAYERS_NEEDED, FIRST_AGE, "Baths", BuildingType.CIVIC);

        city = new City(mock(Wonder.class), mock(StoragePoint.class), mock(Storage.class));
    }

    @Test
    public void testValidateBuildingsInCityMeetBuildingRequirements() {

        templeBuildingRequirementList.add(gatesBuilding);
        templeBuildingRequirementList.add(bathsBuilding);


        BuildingRequirement buildingRequirement = new BuildingRequirement(templeBuildingRequirementList);

        city.addBuilding(gatesBuilding);
        city.addBuilding(bathsBuilding);

        assertTrue(templeBuilding.getRequirements().get(1).validate(city));
        assertTrue(buildingRequirement.validate(city));
    }

    @Test
    public void testValidateSingleBuildingInCityMeetBuildingRequirements() {

        templeBuildingRequirementList.add(gatesBuilding);

        BuildingRequirement buildingRequirement = new BuildingRequirement(templeBuildingRequirementList);

        city.addBuilding(gatesBuilding);


        assertTrue(buildingRequirement.validate(city));
    }

    @Test
    public void testValidateSingleBuildingInCityDoesNotMeetBuildingRequirements() {

        templeBuildingRequirementList.add(gatesBuilding);

        BuildingRequirement buildingRequirement = new BuildingRequirement(templeBuildingRequirementList);


        assertFalse(buildingRequirement.validate(city));
    }

    @Test
    public void testValidateBuildingInCityDoesNotMeetBuildingRequirements() {

        templeBuildingRequirementList.add(gatesBuilding);
        templeBuildingRequirementList.add(bathsBuilding);

        BuildingRequirement buildingRequirement = new BuildingRequirement(templeBuildingRequirementList);

        city.getBuildings().add(gatesBuilding);

        assertFalse(buildingRequirement.validate(city));
    }

    @Test
    public void testValidateBuildingInCityMeetEmptyBuildingRequirements() {

        BuildingRequirement buildingRequirement = new BuildingRequirement(templeBuildingRequirementList);


        assertTrue(buildingRequirement.validate(city));
    }

    @Test
    public void testValidateEmptyBuildingsInCityDoesNotMeetBuildingRequirements() {

        templeBuildingRequirementList.add(gatesBuilding);
        templeBuildingRequirementList.add(bathsBuilding);

        BuildingRequirement buildingRequirement = new BuildingRequirement(templeBuildingRequirementList);


        assertFalse(buildingRequirement.validate(city));
    }

    @Test
    public void testValidateBuildingsInCityMeetSingleBuildingRequirements() {

        templeBuildingRequirementList.add(gatesBuilding);

        BuildingRequirement buildingRequirement = new BuildingRequirement(templeBuildingRequirementList);

        city.getBuildings().add(gatesBuilding);
        city.getBuildings().add(bathsBuilding);

        assertTrue(buildingRequirement.validate(city));
    }

    @Test(expected = NullPointerException.class)
    public void testValidateCityIsNull() {
        templeBuildingRequirementList.add(gatesBuilding);

        BuildingRequirement buildingRequirement = new BuildingRequirement(templeBuildingRequirementList);
        City nullCity = null;

        buildingRequirement.validate(nullCity);
    }

    @Test(expected = NullPointerException.class)
    public void testValidateBuildingsListIsNull() {
        templeBuildingRequirementList.add(gatesBuilding);

        BuildingRequirement buildingRequirement = new BuildingRequirement(templeBuildingRequirementList);
        City nullCity = null;

        buildingRequirement.validate(nullCity);
    }
}



