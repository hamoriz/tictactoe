package com.hamori.game;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Author: Zoltan_Hamori
 */
@EnableAutoConfiguration
public class PlayerTest {

    @Autowired
    private Player player;

        @Test
        public void discoverWinningMove() {
            Position position = new StringPosition(
                    "_XO" +
                            "_X_" +
                            "O__");

            Field field = player.move(position);
            assertThat(field, is(Field.BOTTOM_CENTRE));
        }

        @Test
        public void canAvoidOpponentWin() {
            Position position =  new StringPosition(
                    "_XO" +
                            "___" +
                            "O_X");

            Field field = player.move(position);
            assertThat(field, is(Field.CENTRE_CENTRE));
        }

        @Test
        public void canDiscoverCentralWinning() {
            Position position =  new StringPosition(
                    "_OX" +
                            "O_X" +
                            "X_O");

            Field field = player.move(position);
            assertThat(field, is(Field.CENTRE_CENTRE));
        }

        @Test
        public void canDiscoverCorner() {
            Position position =  new StringPosition(
                    "_O_" +
                            "O_X" +
                            "X__");

            Field field = player.move(position);
            assertThat(field, is(Field.TOP_RIGHT));
        }


}
