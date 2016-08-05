package org.fundacionjala.sevenwonders.beans;

import org.fundacionjala.sevenwonders.core.Game;
import org.fundacionjala.sevenwonders.core.GameRoom;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by dwits on 04/08/2016.
 */

@Component
public class GameService {
    private final Map<String, Game> games = new TreeMap<>();
    private String autoIncrementId;

    public GameService(){
        autoIncrementId = 1 + "";
    }

    public void createGame(Game game) {
        games.put(autoIncrementId, game);
        autoIncrementId = (Integer.parseInt(autoIncrementId) + 1) + "";
    }
}
