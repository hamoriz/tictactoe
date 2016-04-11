package com.hamori.game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Author: Zoltan_Hamori
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Game.class)
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
        Position position = new StringPosition(
                "_XO" +
                        "___" +
                        "O_X");

        Field field = player.move(position);
        assertThat(field, is(Field.CENTRE_CENTRE));
    }

    @Test
    public void canDiscoverCentralWinning() {
        Position position = new StringPosition(
                "_OX" +
                        "O_X" +
                        "X_O");

        Field field = player.move(position);
        assertThat(field, is(Field.CENTRE_CENTRE));
    }

    @Test
    public void canDiscoverCorner() {
        Position position = new StringPosition(
                "_O_" +
                        "O_X" +
                        "X__");

        Field field = player.move(position);
        assertThat(field, is(Field.TOP_RIGHT));
    }

    @Test(expected = IllegalStateException.class)
    public void wonByX() {
        player.move(new StringPosition(
                "_XO" +
                        "_X_" +
                        "OX_"));
    }

    @Test(expected = IllegalStateException.class)
    public void wonByO() {
        player.move(new StringPosition(
                "OOO" +
                        "_X_" +
                        "OX_"));
    }


    @Test(expected = IllegalStateException.class)
    public void winSituationTop() {
        player.move(new StringPosition(
                "OOO" +
                        "___" +
                        "___"));
    }

    @Test(expected = IllegalStateException.class)
    public void winSituationCenter1() {
        player.move(new StringPosition(
                "___" +
                        "OOO" +
                        "___"));
    }

    @Test(expected = IllegalStateException.class)
    public void winSituationCenter2() {
        player.move(new StringPosition(
                "_O_" +
                        "_O_" +
                        "_O_"));
    }

    @Test(expected = IllegalStateException.class)
    public void winSituationBottom() {
        player.move(new StringPosition(
                "___" +
                        "___" +
                        "OOO"));
    }

    @Test(expected = IllegalStateException.class)
    public void winSituationLeft() {
        player.move(new StringPosition(
                "O__" +
                        "O__" +
                        "O_X"));
    }

    @Test(expected = IllegalStateException.class)
    public void winSituationRight() {
        player.move(new StringPosition(
                "__O" +
                        "__O" +
                        "__O"));
    }

    @Test(expected = IllegalStateException.class)
    public void winSituationDiagonal1() {
        player.move(new StringPosition(
                "O__" +
                        "_O_" +
                        "__O"));
    }

    @Test(expected = IllegalStateException.class)
    public void winSituationDiagonal2() {
        player.move(new StringPosition(
                "__O" +
                        "_O_" +
                        "O__"));
    }


    @Test(expected = IllegalStateException.class)
    public void noMoreStep() {
        player.move(new StringPosition(
                "XOO" +
                        "OXX" +
                        "OXO_"));
    }
}
