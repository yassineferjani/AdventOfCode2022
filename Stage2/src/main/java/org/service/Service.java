package org.service;

import java.util.List;

public class Service {
    private static int calculateMoveScore(char myMove) {
        return switch (myMove) {
            case 'X' -> 1;
            case 'Y' -> 2;
            case 'Z'->3;
            default -> throw new IllegalStateException("Unexpected value: " + myMove);
        };
    }

    private static int calculateRoundscore(char opponent , char myMove){
        if (opponent == 'A' && myMove == 'Y'
                || opponent == 'B' && myMove == 'Z'
                || opponent == 'C' && myMove == 'X') {
            return  6;
        } else if (opponent == 'A' && myMove == 'Z'
                || opponent == 'B' && myMove == 'X'
                || opponent == 'C' && myMove == 'Y') {
            return  0;
        } else {
            return  3;
        }
    }

    private static int decryptingUltraTopSecretStrategySore(char opponent, char myMove){
        if (myMove =='X'){
            return switch (opponent){
                case 'A' -> 3;
                case 'B' -> 1;
                case 'C' -> 2;
                default -> throw new IllegalStateException("Unexpected value: " + opponent);
            };
        }else if (myMove =='Y'){
            return switch (opponent){
                case 'A' -> 4;
                case 'B' -> 5;
                case 'C' -> 6;
                default -> throw new IllegalStateException("Unexpected value: " + opponent);
            };
        } else{
            return switch (opponent){
                case 'A' -> 8;
                case 'B' -> 9;
                case 'C' -> 7;
                default -> throw new IllegalStateException("Unexpected value: " + opponent);
            };
        }
    }

    private static int calculatescore(char myMove , char opponent){
        return calculateMoveScore(myMove) + calculateRoundscore(opponent,myMove);
    }

    public static int calculateTotalScore(List<String> list){
        int score = 0;
        for (String s:list) {
            char opponent = s.charAt(0);
            char myMove = s.charAt(2);
            score+= calculatescore(myMove,opponent);
        }
        return score;
    }

    public static int calculateTopSecretStrategyTotalSore(List<String> list){
        int score = 0;
        for (String s:list) {
            char opponent = s.charAt(0);
            char myMove = s.charAt(2);
            score+= decryptingUltraTopSecretStrategySore(opponent, myMove);
        }
        return score;

    }


}
