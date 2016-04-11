package com.hamori.game;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Zoltan_Hamori
 */
public class StringPosition implements Position {

    private Map<Field, FieldStatus> fields;

    public StringPosition(String field) {
        fields = new HashMap<>();
        prepareFieldMap(field);
    }

    @Override
    public FieldStatus getFieldStatus(Field field) {
        return fields.get(field);
    }


    private void prepareFieldMap(String position) {

        char[] fieldChars = position.toCharArray();
        for (int i = 0; i < 9; i++) {
            fields.put(Field.values()[i], convertCharToStatus(fieldChars[i]));
        }

    }

    private FieldStatus convertCharToStatus(char status) {
        switch (status) {
            case 'O':
                return FieldStatus.O;
            case 'X':
                return FieldStatus.X;
            case '_':
                return FieldStatus.EMPTY;

        }

        throw new InvalidParameterException(status + " character is not accepted");
    }


}
