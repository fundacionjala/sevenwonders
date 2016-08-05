package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.core.rest.*;
import org.fundacionjala.sevenwonders.core.rest.Player;
import org.junit.Assert;
import org.junit.Test;

/**
 * Used to test all the functionality of the {@link GameRoom}.
 *
 * @author Juan Manuel Barahona
 */
public class GameRoomTest {

    @Test
    public void testAddPlayer(){
        int maxPlayers = 2;
        GameRoom gameRoom = new GameRoom(maxPlayers);

        gameRoom.addPlayer(new Player());
        gameRoom.addPlayer(new Player());

        Assert.assertEquals(2, gameRoom.getPlayers().size());
        Assert.assertFalse(gameRoom.getMaxPlayers() > gameRoom.getPlayers().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddPlayerFail(){
        int maxPlayers = 2;
        GameRoom gameRoom = new GameRoom(maxPlayers);

        gameRoom.addPlayer(new Player());
        gameRoom.addPlayer(new Player());
        gameRoom.addPlayer(new Player());
        Assert.fail();
    }

    @Test
    public void testAssignWonders(){
        int maxPlayers = 3;
        GameRoom gameRoom = new GameRoom(maxPlayers);

        gameRoom.addPlayer(new Player());
        gameRoom.addPlayer(new Player());
        gameRoom.addPlayer(new Player());

        Game game = gameRoom.createGame();

        Assert.assertEquals(3, game.getPlayers().size());
        game.getPlayers().stream().forEach(player -> {
            Assert.assertTrue(player.getDeck().size() > 0);
            Assert.assertTrue(player.getCity().getWonder().getName() != null);
        });

    }

}
