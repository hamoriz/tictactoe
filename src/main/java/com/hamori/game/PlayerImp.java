package com.hamori.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Author: Zoltan_Hamori
 */
@Component
public class PlayerImp implements Player {

    @Autowired
    private FieldEvaluator evaluator;

    public Field move(Position position) {
        validatePosition(position);
        return findBestMove(position);
    }

    private Field findBestMove(Position position) {
        throw new NotImplementedException();
    }

    private void validatePosition(Position position) {
        if (evaluator.isWinSituation(position) || evaluator.numberOfFreeCells(position)==0) {
            throw new IllegalStateException();
        }
    }

}
