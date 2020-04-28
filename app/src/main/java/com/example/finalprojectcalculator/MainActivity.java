package com.example.finalprojectcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.lang3.math.Fraction;

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
        //Not finished
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
        return true;
    }

    /**
     * Method called by the equals button to solve the operation inputted by the user in the
     * Calculator View. Directs the passed string to the correct solve helper method depending on whether
     * the calculatorInput contains a fractions or not. Sets the answer into the Calculator View;
     * @param toSolve - String that represents the operation that needs to be parsed and solved.
     */
    public void compute(String toSolve) {
        //Not Finished
        if (!isValid(toSolve)) {
            calculatorInput = "";
            setCalculatorView(calculatorInput);
        }
            //return;
        //}
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This will compute the operation in the calculator view.");
        builder.create().show();
    }

    // This section contains helper functions for the compute method that performs the operations.

    /**
     * Solves an input that contains no fractions depending on the operator
     * @param x - first number
     * @param y - second number
     * @param operator - determines operation that is performed
     * @return the answer
     */
    //Placeholder return statements.
    public double solveNoFrac(double x, double y, String operator) {
        return 0;
    }

    public Fraction solveFrac1(Fraction x, double y, String operator) {
        return Fraction.getFraction(2.1);
    }

    public Fraction solveFrac2(double x, Fraction y, String operator) {
        return Fraction.getFraction(2.1);
    }

    public Fraction solveFrac3(Fraction x, Fraction y, String operator) {
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
     * Method called when clicking "Fraction" button.
     * @param fraction - button
     */
    public void onClickFraction(final View fraction) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This will allow user to build a fraction");
        builder.create().show();
    }

    /**
     * Method called when clicking "=" button.
     * @param equals - button
     */
    public void onClickEquals(final View equals) {
        compute(calculatorInput);
    }
}

