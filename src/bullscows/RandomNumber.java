package bullscows;

import java.util.Random;

public class RandomNumber {
    String length;
    int numberOfPossibleSymbols;
    private final String allSymbols = "0123456789abcdefghijklmnopqrstuvwxyz";

    public RandomNumber(String length, int numberOfPossibleSymbols) {
        this.numberOfPossibleSymbols = numberOfPossibleSymbols;
        this.length = String.valueOf(length);
    }

    public String getRandomNumber() {
        StringBuilder trueSymbols = findTrueSymbols(numberOfPossibleSymbols, allSymbols);
        StringBuilder result = new StringBuilder();
        while (result.length() < Integer.parseInt(length)) {
            Random random = new Random();
            int number = random.nextInt(trueSymbols.length());
            result.append(trueSymbols.charAt(number));
            trueSymbols.deleteCharAt(number);
        }
        return result.toString();
    }

//    private boolean checkOnUnique(StringBuilder str, int element) {
//        for (int i = 0; i < str.length(); i++) {
//            if (Character.getNumericValue(str.charAt(i)) == element) {
//                return false;
//            }
//        }
//        return true;
//    }

    public static StringBuilder findTrueSymbols(int length, String allSymbols) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(allSymbols.charAt(i));
        }
        return result;
    }

    public void printLine() {
        System.out.print("The secret is prepared: ");
        for (int i = 0; i < Integer.parseInt(length); i++) {
            System.out.print("*");
        }
        System.out.print(" ");
        System.out.print("(");
        if (numberOfPossibleSymbols < 11) {
            System.out.print("0-" + (numberOfPossibleSymbols - 1));
        } else {
            System.out.print("0 - 9, a-" + allSymbols.charAt(numberOfPossibleSymbols - 1));
        }
        System.out.print(")\n");
    }
    public String checkingForExceptions(){
        String result = null;
        if(Integer.parseInt(length) > numberOfPossibleSymbols){
            result = "Error: it's not possible to generate a code with a length of " + length +
                    " with " + numberOfPossibleSymbols + " unique symbols.";
        }
        if(Integer.parseInt(length) < 1){
            result = "Error: "+ length +" isn't a valid number.";
        }
        if(!length.matches("\\d+")){
            result = "Error: "+ length +" isn't a valid number.";
        }
        if(numberOfPossibleSymbols > 36){
            result = "Error: maximum number of possible symbols in the code is 36 (0-9, a-z).";
        }
        return result;
    }
}
