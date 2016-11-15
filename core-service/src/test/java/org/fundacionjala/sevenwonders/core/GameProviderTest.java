package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.GameProvider;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by diego on 11/7/2016.
 */
public class GameProviderTest {

    @Test
    public void testTreeIsNotEmpty() throws IOException {
        GameProvider gameProvider = new GameProvider();
        gameProvider.readWonders();
        gameProvider.readCards();
    }
}
