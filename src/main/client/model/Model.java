package client.model;

public class Model {
    private int calculationValue;
    public void addTwoNumbers(int firstNumber, int secondNumber){
        calculationValue = firstNumber + secondNumber;
    }
    public int getCalculationValue(){
        return calculationValue;
    }
}