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

    private static final long SCORE_WINNING = 500L;
    private static final long SCORE_NEUTRAL = 400L;
    private static final long SCORE_LOSING = 100L;

    @Autowired
    private FieldEvaluator evaluator;

    @Autowired
    private PositionHandler positionHandler;

    public Field move(Position position) {
        evaluator.validatePosition(position);
        return getBestStep(position);
    }

    private Field getBestStep(Position position) {
        return getMaxValueStep(evaluateSteps(position, 0L)).getKey();
    }

    private Map.Entry<Field, Score> getMaxValueStep(Map<Field, Score> fieldLongMap) {
        return fieldLongMap.entrySet().stream().max((e1, e2) -> e1.getValue().compareTo(e2.getValue())).get();
    }

    private Map<Field, Score> evaluateSteps(Position position, Long depth) {
        List<Field> possibleSteps = getPossibleSteps(position);

        Map<Field, Score> fieldValues = new HashMap<>();
        for (Field field : possibleSteps) {
            Position nextPosition = positionHandler.step(position, field);
            if (evaluator.isWinSituation(nextPosition)) {
                fieldValues.put(field, new Score(depth, SCORE_WINNING));
                return fieldValues;
            }
        }

        for (Field field : possibleSteps) {
            Position nextPosition = positionHandler.step(position, field);
            fieldValues.put(field, new Score(depth, SCORE_NEUTRAL));

            if (evaluator.numberOfCells(nextPosition, FieldStatus.EMPTY) != 0) {
                Map<Field, Score> scores = evaluateSteps(nextPosition, depth + 1);

                Map.Entry<Field, Score> opponentBestStepValue = getMaxValueStep(scores);
                if (opponentBestStepValue.getValue().getStatus() == SCORE_WINNING) {
                    fieldValues.put(field, new Score(opponentBestStepValue.getValue().getDepth(), SCORE_LOSING));
                } else if (opponentBestStepValue.getValue().getStatus() == SCORE_LOSING) {
                    fieldValues.put(field, new Score(opponentBestStepValue.getValue().getDepth(), SCORE_WINNING));
                }
            }
        }
        return fieldValues;
    }

    private List<Field> getPossibleSteps(Position position) {
        return Arrays.asList(Field.values()).stream().filter(fld -> position.getFieldStatus(fld) == FieldStatus.EMPTY).collect(Collectors.toList());
    }


    private class Score implements Comparable<Score> {

        private Long depth;
        private Long status;

        public Score(Long depth, Long status) {
            this.depth = depth;
            this.status = status;
        }

        public Long getDepth() {
            return depth;
        }

        public Long getStatus() {
            return status;
        }


        @Override
        public int compareTo(Score o) {
            if (this.getStatus().equals(o.getStatus()) && this.getStatus().equals(SCORE_WINNING)) {
                return -this.getDepth().compareTo(o.getDepth());
            } else if (this.getStatus().equals(o.getStatus()) && this.getStatus().equals(SCORE_LOSING)) {
                return this.getDepth().compareTo(o.getDepth());
            } else {
                return this.getStatus().compareTo(o.getStatus());
            }
        }
    }


}
