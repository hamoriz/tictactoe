package com.hamori.game;

/**
 * Author: Zoltan_Hamori
 */

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Utility class to provide functions used generally
 */
@Component
public class FieldEvaluator {

    private static final List<Set<Field>> winningSituations = new ArrayList<Set<Field>>() {{
        add(new HashSet<Field>() {{
            add(Field.BOTTOM_LEFT);
            add(Field.BOTTOM_CENTRE);
            add(Field.BOTTOM_RIGHT);
        }});
        add(new HashSet<Field>() {{
            add(Field.CENTRE_LEFT);
            add(Field.CENTRE_CENTRE);
            add(Field.CENTRE_RIGHT);
        }});
        add(new HashSet<Field>() {{
            add(Field.TOP_LEFT);
            add(Field.TOP_CENTRE);
            add(Field.TOP_RIGHT);
        }});
        add(new HashSet<Field>() {{
            add(Field.BOTTOM_LEFT);
            add(Field.CENTRE_LEFT);
            add(Field.TOP_LEFT);
        }});
        add(new HashSet<Field>() {{
            add(Field.BOTTOM_CENTRE);
            add(Field.CENTRE_CENTRE);
            add(Field.TOP_CENTRE);
        }});
        add(new HashSet<Field>() {{
            add(Field.BOTTOM_RIGHT);
            add(Field.CENTRE_RIGHT);
            add(Field.TOP_RIGHT);
        }});
        add(new HashSet<Field>() {{
            add(Field.BOTTOM_RIGHT);
            add(Field.CENTRE_CENTRE);
            add(Field.TOP_LEFT);
        }});
        add(new HashSet<Field>() {{
            add(Field.BOTTOM_LEFT);
            add(Field.CENTRE_CENTRE);
            add(Field.TOP_RIGHT);
        }});
    }};


    /**
     * Checks whether the position represents a winning situation
     *
     * @param position
     * @return true if it is a win
     */
    public boolean isWinSituation(Position position) {

        for (Set<Field> winningSet : winningSituations) {
            if (winningSet.stream()
                    .allMatch(field -> position.getFieldStatus(field) == FieldStatus.O) ||
                    winningSet.stream()
                            .allMatch(field -> position.getFieldStatus(field) == FieldStatus.X)) {
                return true;
            }

        }
        return false;
    }


    /**
     * Calculates the number of free cells on the board
     *
     * @param position
     * @return number of EMPTY cells
     */
    public long numberOfCells(Position position, FieldStatus status) {
        return Arrays.asList(Field.values()).stream().
                filter(field -> position.getFieldStatus(field) == status).count();

    }


    public void validatePosition(Position position) {
        if (isWinSituation(position) || numberOfCells(position, FieldStatus.EMPTY) == 0) {
            throw new IllegalStateException();
        }
    }

}
