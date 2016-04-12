package com.hamori.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author: Zoltan_Hamori
 */
@Component
public class PlayerImp implements Player {

    private static final long SCORE_WINNING = 1L;
    private static final long SCORE_LOSING = 4L;
    private static final long SCORE_NEUTRAL = 3L;

    @Autowired
    private FieldEvaluator evaluator;

    @Autowired
    private PositionHandler positionHandler;

    public Field move(Position position) {
        evaluator.validatePosition(position);
        return getBestStep(position);
    }

    private Field getBestStep(Position position) {
       return getMaxValueStep(evaluateSteps(position)).getKey();
    }

    private Map.Entry<Field,Long> getMaxValueStep(Map<Field, Long> fieldLongMap) {
        return fieldLongMap.entrySet().stream().min((e1,e2)-> Long.compare(e1.getValue(),e2.getValue())).get();
    }

    private  Map<Field,Long> evaluateSteps(Position position) {
        List<Field> possibleSteps = getPossibleSteps(position);

        Map<Field, Long> fieldValues = new HashMap<>();
        for (Field field : possibleSteps) {
            Position nextPosition = positionHandler.step(position, field);
            if (evaluator.isWinSituation(nextPosition)) {
                fieldValues.put(field, SCORE_WINNING);
                return fieldValues;
            }
        }

        for (Field field : possibleSteps) {
          Position nextPosition = positionHandler.step(position, field);

          if (evaluator.numberOfCells(nextPosition, FieldStatus.EMPTY) != 0) {
              Map.Entry<Field, Long> opponentBestStepValue =  getMaxValueStep(evaluateSteps(nextPosition));
              if (opponentBestStepValue.getValue()==SCORE_WINNING) {
                  fieldValues.put(field, SCORE_LOSING);
              } else if (opponentBestStepValue.getValue()==SCORE_LOSING) {
                  fieldValues.put(field, SCORE_WINNING);
              } else {
                  fieldValues.put(field, SCORE_NEUTRAL);
              }
            } else {
                fieldValues.put(field, SCORE_NEUTRAL);
            }
        }

        return fieldValues;
    }

    private List<Field> getPossibleSteps(Position position) {
        return Arrays.asList(Field.values()).stream().filter(fld -> position.getFieldStatus(fld) == FieldStatus.EMPTY).collect(Collectors.toList());
    }


}
