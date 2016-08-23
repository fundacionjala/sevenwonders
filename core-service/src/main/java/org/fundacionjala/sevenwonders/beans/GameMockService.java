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

    private List<GameMock> list;

    public GameMockService() {
        list = new ArrayList<>();
        init();
    }
    
    public void addGame(GameMock game) {
        list.add(game);
    }

    public List<GameMock> getGames() {
        return list;
    }
    
    private void init() {
        list.add(new GameMock(1, "ABC", "classic", 3));
        list.add(new GameMock(2, "XGS", "classic", 7));
    }
}
