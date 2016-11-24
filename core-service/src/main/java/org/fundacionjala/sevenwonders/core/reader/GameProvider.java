package org.fundacionjala.sevenwonders.core.reader;

import org.fundacionjala.sevenwonders.core.Card;
import org.fundacionjala.sevenwonders.core.Deck;
import org.fundacionjala.sevenwonders.core.Wonder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 11/18/2016.
 */
public class GameProvider {

    private String absolutePath;
    private GameReader reader;

    public GameProvider() throws IOException {
        this.absolutePath = new File("").getAbsolutePath();
        this.reader = new GameReader();
    }

    public Deck getBuildings() throws IOException {
        String cardsPath = "/src/main/java/org/fundacionjala/sevenwonders/resources/Cards/buildings";
        File file = new File(absolutePath + cardsPath);
        List<Card> cardList = new ArrayList<>();
        File[] listofFolders = file.listFiles();
        for (File currentFolder : listofFolders) {
            String folderName = currentFolder.getName().toUpperCase();
            File[] listofFiles = currentFolder.listFiles();
            for (File currentFile : listofFiles) {
                cardList.add(reader.readBuilding(currentFile, folderName));
            }
        }
        return new Deck(cardList);
    }

    public List<Wonder> getWonders() throws IOException {
        String wondersPath = "/src/main/java/org/fundacionjala/sevenwonders/resources/Wonders";
        File file = new File(absolutePath + wondersPath);
        List<Wonder> wonderList = new ArrayList<>();
        File[] listofFiles = file.listFiles();
        for (File currentFile : listofFiles) {
            wonderList.add(reader.readWonder(currentFile));
        }
        return wonderList;
    }
}