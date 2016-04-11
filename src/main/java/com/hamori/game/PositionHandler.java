package com.hamori.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;

/**
 * Author: Zoltan_Hamori
 */
@Component
public class PositionHandler {


    @Autowired
    private FieldEvaluator evaluator;


    public Position step(Position position, Field field) {

        if (position.getFieldStatus(field)!=FieldStatus.EMPTY) {
            throw new InvalidParameterException();
        }
        return fld -> {
            if (fld == field) {
                return getNextFieldStatus(position);
            } else {
                return position.getFieldStatus(fld);
            }
        };
    }

    private FieldStatus getNextFieldStatus(Position  position) {
        if (evaluator.numberOfCells(position,FieldStatus.O)>= evaluator.numberOfCells(position,FieldStatus.X)) {
            return FieldStatus.X;
        } else {
           return  FieldStatus.O;
        }
    }
}
