package client.model;
import common.model.game.*;

import java.util.ArrayList;
import java.util.List;

public class Model {
    List<Game> listOfGames = new ArrayList<>();

    private int calculationValue;

    public void addTwoNumbers(int firstNumber, int secondNumber){
        calculationValue = firstNumber + secondNumber;
    }
    public int getCalculationValue(){
        return calculationValue;
    }

    public void addPlayer(String nick) {

    }
}