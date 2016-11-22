package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.core.reader.GameReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by diego on 11/7/2016.
 */
public class GameReaderTest {

    private String path = new File("").getAbsolutePath();
    private String wonderTestPath = "/src/test/resources/wonders/Alexandria.json";
    private String cardTestPath = "/src/test/resources/cards/Altar.json";

    @Test
    public void testWonderIsNotNull() throws IOException {
        File file = new File(path + wonderTestPath);
        GameReader gameReader = new GameReader();
        Wonder wonder = gameReader.readWonder(file);

        Assert.assertNotNull(wonder);
    }

    @Test
    public void testCompareWonderResult() throws IOException {
        File file = new File(path + wonderTestPath);
        GameReader gameReader = new GameReader();
        Wonder wonder = gameReader.readWonder(file);

        String expecteName = "Alexandria";
        Assert.assertEquals(expecteName, wonder.getName());

        //Sides comparison
        Side sideA = wonder.getSide("a");
        Assert.assertNotNull(sideA);

        Side sideB = wonder.getSide("b");
        Assert.assertNotNull(sideB);
    }

    @Test
    public void testCardIsNotNull() throws IOException {
        File file = new File(path + cardTestPath);
        GameReader gameReader = new GameReader();
        Building building = gameReader.readBuilding(file, "RESOURCE");

        Assert.assertNotNull(building);
    }
}
