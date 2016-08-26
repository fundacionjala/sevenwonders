/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fundacionjala.sevenwonders.beans;

import java.util.ArrayList;
import java.util.List;
import org.fundacionjala.sevenwonders.core.rest.GameMock;
import org.springframework.stereotype.Component;

/**
 *
 * @author luisgumucio
 */
@Component
public class GameMockService {

    private List<GameMock> gameMockList;
    private int autoIncrementId;

    public GameMockService() {
        autoIncrementId = 1;
        gameMockList = new ArrayList<>();
        init();
    }
    
    public void addGame(GameMock game) {
        game.setId(autoIncrementId);
        gameMockList.add(game);
        autoIncrementId++;
    }

    public List<GameMock> getGames() {
        return gameMockList;
    }
    
    private void init() {
        gameMockList.add(new GameMock(1, "ABC", 3));
        gameMockList.add(new GameMock(2, "XGS", 7));
    }
}
