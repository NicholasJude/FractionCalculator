package com.example.finalprojectcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.lang3.math.Fraction;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    /**
     * String created in the Calculator View
     */
    private static String calculatorInput;

    /**
     * Called by the Android system when the activity is to be set up.
     * @param savedInstanceState information from the previously terminated instance (unused)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);
        calculatorInput = "";
        setCalculatorView(calculatorInput);
    }

    /**
     * Sets the string shown in the calculator view.
     * @param input - String that needs to be shown
     */
    public void setCalculatorView(String input) {
        TextView calculatorView = findViewById(R.id.calculatorView);
        calculatorView.setText(input);
    }

    /**
     * Checks to make sure that the passed calculatorInput can be computed.
     * @param input - String inputted into the Calculator View
     * @return whether the input is valid
     */
    public boolean isValid(String input) {
        if (input.equals("")) {
            return false;
        }
        int additionCount = 0;
        int subtractionCount = 0;
        int multiplicationCount = 0;
        int divisionCount = 0;
        for(int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+') {
                additionCount++;
            }
            if (input.charAt(i) == '-') {
                subtractionCount++;
            }
            if (input.charAt(i) == 'x') {
                multiplicationCount++;
            }
            if (input.charAt(i) == '÷') {
                divisionCount++;
            }
        }
        if (additionCount > 1 || subtractionCount > 1 || multiplicationCount > 1 || divisionCount > 1) {
            return false;
        }
        if (additionCount == 0 && subtractionCount == 0 && multiplicationCount == 0 && divisionCount == 0) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the calculator view string involves converting fraction to double or double to fraction.
     * @param input - String inputted into Calculator View.
     * @return if the calculator can perform conversion.
     */
    public boolean isValidForConverting(String input) {
        int additionCount = 0;
        int subtractionCount = 0;
        int multiplicationCount = 0;
        int divisionCount = 0;
        int decimalCount = 0;
        int convertCount = 0;
        for(int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+') {
                additionCount++;
            }
            if (input.charAt(i) == '-') {
                subtractionCount++;
            }
            if (input.charAt(i) == 'x') {
                multiplicationCount++;
            }
            if (input.charAt(i) == '÷') {
                divisionCount++;
            }
            if (input.charAt(i) == '.') {
                decimalCount++;
            }
            if (input.charAt(i) == '>') {
                convertCount++;
            }
        }
        if (additionCount > 0 || subtractionCount > 0 || multiplicationCount > 0 || divisionCount > 0) {
            return false;
        }
        if (decimalCount > 1) {
            return false;
        }
        if (convertCount == 0 || convertCount > 3) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the string in the calculator view contains a fraction.
     * @param input - Calculator view string
     * @return if the string contains a fraction
     */
    public boolean containsFraction(String input) {
        int fractionCount = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '/') {
                fractionCount++;
            }
        }
        return fractionCount != 0;
    }

    /**
     * Obtains the operator of the operation as a string.
     * @param input - Calculator view string
     * @return the operator
     */
    public String getOperator(String input) {
        int additionCount = 0;
        int subtractionCount = 0;
        int multiplicationCount = 0;
        int divisionCount = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+') {
                additionCount++;
            }
            if (input.charAt(i) == '-') {
                subtractionCount++;
            }
            if (input.charAt(i) == 'x') {
                multiplicationCount++;
            }
            if (input.charAt(i) == '÷') {
                divisionCount++;
            }
        }
        if (additionCount == 1) {
            return Pattern.quote("+");
        }
        if (subtractionCount == 1) {
            return "-";
        }
        if (multiplicationCount == 1) {
            return "x";
        }
        if (divisionCount == 1) {
            return "÷";
        }
        return "o";
    }

    /**
     * Method called by the equals button to solve the operation inputted by the user in the
     * Calculator View. Directs the passed string to the correct solve helper method depending on whether
     * the calculatorInput contains a fractions or not. Sets the answer into the Calculator View;
     * @param toSolve - String that represents the operation that needs to be parsed and solved.
     */
    public void compute(String toSolve) {
        if (isValidForConverting(toSolve)) {
            String[] parsing = toSolve.split(">>>");
            String howConvert = parsing[1].trim();
            String unknownNeedsConverting = parsing[0].trim();
            Fraction isFraction = null;
            double isNumber = 0.0000;
            if (containsFraction(unknownNeedsConverting)) {
                isFraction = Fraction.getFraction(unknownNeedsConverting);
            }
            if (!(containsFraction(unknownNeedsConverting))) {
                isNumber = Double.parseDouble(unknownNeedsConverting);
            }
            if (howConvert.equals("NUMBER") && isNumber == 0.0000) {
                String output = Double.toString(isFraction.doubleValue());
                calculatorInput = output;
                setCalculatorView(output);
                return;
            }
            if (howConvert.equals("NUMBER") && isFraction == null) {
                String output = Double.toString(isNumber);
                calculatorInput = output;
                setCalculatorView(output);
                return;
            }
            if (howConvert.equals("FRACTION") && isFraction == null) {
                String output = Fraction.getFraction(isNumber).toString();
                calculatorInput = output;
                setCalculatorView(output);
                return;
            }
            if (howConvert.equals("FRACTION") && isNumber == 0.0000) {
                String output = isFraction.toString();
                calculatorInput = output;
                setCalculatorView(output);
                return;
            }
            return;
        }
        if (!isValid(toSolve)) {
            calculatorInput = "";
            setCalculatorView(calculatorInput);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please enter a valid input. Ex: 7 + 8, 4/5 + 8, or 2/3 >>> NUMBER");
            builder.create().show();
            return;
        }
        //Breaking down the toSolve string
        String operator = getOperator(toSolve);
        String[] operationPieces = toSolve.split(operator);
        String piece1 = operationPieces[0].trim();
        String piece2 = operationPieces[1].trim();
        double firstNum = 0.0000;
        Fraction firstFrac = null;
        double secondNum = 0.0000;
        Fraction secondFrac = null;
        if (containsFraction(piece1)) {
            firstFrac = Fraction.getFraction(piece1);
        }
        if (!(containsFraction(piece1))) {
            firstNum = Double.parseDouble(piece1);
        }
        if (containsFraction(piece2)) {
            secondFrac = Fraction.getFraction(piece2);
        }
        if (!(containsFraction(piece2))) {
            secondNum = Double.parseDouble(piece2);
        }
        //Solving the operation
        if (firstFrac == null && secondFrac == null) {
            double answer = solveNoFrac(firstNum, secondNum, operator);
            String output = Double.toString(answer);
            calculatorInput = output;
            setCalculatorView(output);
            return;
        }
        if (firstNum == 0.0000 && secondNum == 0.0000) {
            Fraction answer = solveFrac3(firstFrac, secondFrac, operator);
            String output = answer.toString();
            calculatorInput = output;
            setCalculatorView(output);
            return;
        }
        if (firstNum == 0.0000 && secondFrac == null) {
            Fraction answer = solveFrac1(firstFrac, secondNum, operator);
            String output = answer.toString();
            calculatorInput = output;
            setCalculatorView(output);
            return;
        }
        if (firstFrac == null && secondNum == 0.0000) {
            Fraction answer = solveFrac2(firstNum, secondFrac, operator);
            String output = answer.toString();
            calculatorInput = output;
            setCalculatorView(output);
        }
    }

    // This section contains helper functions for the compute method that performs the operations.

    /**
     * Solves an input that contains no fractions depending on the operator
     * @param x - first number
     * @param y - second number
     * @param operator - determines operation that is performed
     * @return the answer
     */
    public double solveNoFrac(double x, double y, String operator) {
        if (operator.equals(Pattern.quote("+"))) {
            return x + y;
        }
        if (operator.equals("-")) {
            return x - y;
        }
        if (operator.equals("x")) {
            return x * y;
        }
        if (operator.equals("÷")) {
            return x / y;
        }
        System.out.println("Does not work");
        return 0;
    }

    /**
     * Solves an input that contains fraction as first number and double as second number.
     * @param x - fraction
     * @param y - double
     * @param operator - determines operation
     * @return the answer
     */
    public Fraction solveFrac1(Fraction x, double y, String operator) {
        if (operator.equals("+")) {
            double fracAsDouble = x.doubleValue();
            return Fraction.getFraction(fracAsDouble + y);
        }
        if (operator.equals("-")) {
            double fracAsDouble = x.doubleValue();
            return Fraction.getFraction(fracAsDouble - y);
        }
        if (operator.equals("x")) {
            double fracAsDouble = x.doubleValue();
            return Fraction.getFraction(fracAsDouble * y);
        }
        if (operator.equals("÷")) {
            double fracAsDouble = x.doubleValue();
            return Fraction.getFraction(fracAsDouble / y);
        }
        System.out.println("Does not work");
        return Fraction.getFraction(2.1);
    }

    /**
     * Solves an input that contains double as first number and fraction as second number
     * @param x - double
     * @param y - fraction
     * @param operator - determines operation
     * @return the answer
     */
    public Fraction solveFrac2(double x, Fraction y, String operator) {
        if (operator.equals("+")) {
            double fracAsDouble = y.doubleValue();
            return Fraction.getFraction(x + fracAsDouble);
        }
        if (operator.equals("-")) {
            double fracAsDouble = y.doubleValue();
            return Fraction.getFraction(x - fracAsDouble);
        }
        if (operator.equals("x")) {
            double fracAsDouble = y.doubleValue();
            return Fraction.getFraction(x * fracAsDouble);
        }
        if (operator.equals("÷")) {
            double fracAsDouble = y.doubleValue();
            return Fraction.getFraction(x / fracAsDouble);
        }
        System.out.println("Does not work");
        return Fraction.getFraction(2.1);
    }

    /**
     * Solves an input that contains two fractions
     * @param x - fraction 1
     * @param y - fraction 2
     * @param operator - determines operation
     * @return the answer
     */
    public Fraction solveFrac3(Fraction x, Fraction y, String operator) {
        if (operator.equals("+")) {
            return x.add(y);
        }
        if (operator.equals("-")) {
            return x.subtract(y);
        }
        if (operator.equals("x")) {
            return x.multiplyBy(y);
        }
        if (operator.equals("÷")) {
            return x.divideBy(y);
        }
        System.out.println("Does not work");
        return Fraction.getFraction(2.1);
    }

    // This section contains all of the onClick methods for the buttons on the calculator.

    /**
     * Method called when clicking "1" button.
     * @param one - button
     */
    public void onClick1(final View one) {
        String original = calculatorInput;
        String newInput = original + "1";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "2" button.
     * @param two - button
     */
    public void onClick2(final View two) {
        String original = calculatorInput;
        String newInput = original + "2";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "3" button.
     * @param three - button
     */
    public void onClick3(final View three) {
        String original = calculatorInput;
        String newInput = original + "3";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "4" button.
     * @param four - button
     */
    public void onClick4(final View four) {
        String original = calculatorInput;
        String newInput = original + "4";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "5" button.
     * @param five - button
     */
    public void onClick5(final View five) {
        String original = calculatorInput;
        String newInput = original + "5";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "6" button.
     * @param six - button
     */
    public void onClick6(final View six) {
        String original = calculatorInput;
        String newInput = original + "6";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "7" button.
     * @param seven - button
     */
    public void onClick7(final View seven) {
        String original = calculatorInput;
        String newInput = original + "7";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "8" button.
     * @param eight - button
     */
    public void onClick8(final View eight) {
        String original = calculatorInput;
        String newInput = original + "8";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "9" button.
     * @param nine - button
     */
    public void onClick9(final View nine) {
        String original = calculatorInput;
        String newInput = original + "9";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "0" button.
     * @param zero - button
     */
    public void onClick0(final View zero) {
        String original = calculatorInput;
        String newInput = original + "0";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "+" button.
     * @param plus - button
     */
    public void onClickPlus(final View plus) {
        String original = calculatorInput;
        String newInput = original + " + ";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "-" button.
     * @param minus - button
     */
    public void onClickMinus(final View minus) {
        String original = calculatorInput;
        String newInput = original + " - ";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "x" button.
     * @param times - button
     */
    public void onClickTimes(final View times) {
        String original = calculatorInput;
        String newInput = original + " x ";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "÷" button.
     * @param divide - button
     */
    public void onClickDivide(final View divide) {
        String original = calculatorInput;
        String newInput = original + " ÷ ";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "." button.
     * @param decimal - button
     */
    public void onClickDecimal(final View decimal) {
        String original = calculatorInput;
        String newInput = original + ".";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "C" button.
     * @param clear - button
     */
    public void onClickClear(final View clear) {
        calculatorInput = "";
        setCalculatorView(calculatorInput);
    }

    /**
     * Method called when clicking "Build Fraction" button.
     * @param fraction - button
     */
    public void onClickFraction(final View fraction) {
        String original = calculatorInput;
        String newInput = original + "/";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "Fraction to Number" button.
     * @param fraction - button
     */
    public void onClickConvertFracToNum(final View fraction) {
        String original = calculatorInput;
        String newInput = original + " >>> NUMBER";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "Number to Fraction" button.
     * @param fraction - button
     */
    public void onClickConvertNumToFrac(final View fraction) {
        String original = calculatorInput;
        String newInput = original + " >>> FRACTION";
        calculatorInput = newInput;
        setCalculatorView(newInput);
    }

    /**
     * Method called when clicking "=" button.
     * @param equals - button
     */
    public void onClickEquals(final View equals) {
        compute(calculatorInput);
    }
}

