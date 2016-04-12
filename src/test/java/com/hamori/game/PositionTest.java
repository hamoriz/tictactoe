package com.hamori.game;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Author: Zoltan_Hamori
 */
public class PositionTest {

    @Test
    public void emptyPositionTest() {
        Position position = new StringPosition(
                "___" +
                        "___" +
                        "___");

        for (Field field : Field.values()) {
            assertThat(position.getFieldStatus(field), is(FieldStatus.EMPTY));
        }
    }


    @Test
    public void oPositionTest() {
        Position position = new StringPosition(
                "OOO" +
                        "OOO" +
                        "OOO");

        for (Field field : Field.values()) {
            assertThat(position.getFieldStatus(field), is(FieldStatus.O));
        }
    }

    @Test
    public void xPositionTest() {
        Position position = new StringPosition(
                "XXX" +
                        "XXX" +
                        "XXX");

        for (Field field : Field.values()) {
            assertThat(position.getFieldStatus(field), is(FieldStatus.X));
        }
    }


    @Test
    public void normalPositionTest() {
        Position position = new StringPosition(
                "XOX" +
                        "OXO" +
                        "___");

        assertThat(position.getFieldStatus(Field.TOP_LEFT), is(FieldStatus.X));
        assertThat(position.getFieldStatus(Field.TOP_CENTRE), is(FieldStatus.O));
        assertThat(position.getFieldStatus(Field.TOP_RIGHT), is(FieldStatus.X));
        assertThat(position.getFieldStatus(Field.CENTRE_LEFT), is(FieldStatus.O));
        assertThat(position.getFieldStatus(Field.CENTRE_CENTRE), is(FieldStatus.X));
        assertThat(position.getFieldStatus(Field.CENTRE_RIGHT), is(FieldStatus.O));
        assertThat(position.getFieldStatus(Field.BOTTOM_LEFT), is(FieldStatus.EMPTY));
        assertThat(position.getFieldStatus(Field.BOTTOM_CENTRE), is(FieldStatus.EMPTY));
        assertThat(position.getFieldStatus(Field.BOTTOM_RIGHT), is(FieldStatus.EMPTY));
    }

    @Test(expected = NullPointerException.class)
    public void stepNull() {
        Position position =(new StringPosition("______X__"));
        position.getFieldStatus(null);
    }

}
