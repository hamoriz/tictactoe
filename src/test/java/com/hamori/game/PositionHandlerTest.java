package com.hamori.game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.InvalidParameterException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Author: Zoltan_Hamori
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Game.class)
public class PositionHandlerTest {

    @Autowired
    private PositionHandler positionHandler;


    @Test
    public void stepBottomLeftToEmpty() {
        Position position = positionHandler.step(new StringPosition("_________"), Field.BOTTOM_LEFT);
        assertThat(positionToString(position), is("______X__"));
    }


    @Test
    public void stepTopLeftToNonEmpty() {
        Position position = positionHandler.step(new StringPosition("______X__"), Field.TOP_LEFT);
        assertThat(positionToString(position), is("O_____X__"));
    }


    @Test(expected = InvalidParameterException.class)
    public void stepTopLeft2TimesToNonEmpty() {
        positionHandler.step(new StringPosition("______X__"), Field.BOTTOM_LEFT);
    }



    private String positionToString(Position position) {
        String positionString="";
        for(Field field : Field.values()) {
            positionString+=position.getFieldStatus(field).toString()=="EMPTY"?"_":position.getFieldStatus(field);
        }
        return positionString;
    }
}
