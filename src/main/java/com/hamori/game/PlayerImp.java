package com.hamori.game;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Author: Zoltan_Hamori
 */
public class PlayerImp implements Player {

    public Field move(Position position) {
        validatePosition(position);
        return findBestMove(position);
    }

    private Field findBestMove(Position position) {
        throw new NotImplementedException();
    }

    private void validatePosition(Position position) {
        if (isWinningPosition(position) || isDrawPosition(position)) {
            throw new IllegalStateException();
        }
    }

    private boolean isDrawPosition(Position position) {
        return false;
    }

    private boolean isWinningPosition(Position position) {
        return false;
    }

}
