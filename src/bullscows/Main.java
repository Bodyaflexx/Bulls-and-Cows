package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String length = String.valueOf(0);
        int numberOfPossibleSymbols;
        String secretCode = null;
        while (secretCode == null) {
            System.out.println("Please, enter the secret code's length:");
            length = scanner.nextLine();
            if (!length.matches("\\d+")) {
                System.out.println("Error: " + length + " isn't a valid number.");
                return;
            }
            System.out.println("Input the number of possible symbols in the code:");
            numberOfPossibleSymbols = scanner.nextInt();
            if (Integer.parseInt(length) > 37) {
                System.out.println("Error: can't generate a secret number with a length of " + length + " because there aren't enough unique digits.");
            } else {
                RandomNumber randomNumber = new RandomNumber(length, numberOfPossibleSymbols);
                if (randomNumber.checkingForExceptions() != null) {
                    System.out.println(randomNumber.checkingForExceptions());
                    return;
                }
                secretCode = randomNumber.getRandomNumber();
                randomNumber.printLine();
                System.out.println("Okay, let's start a game!");
            }
        }
        System.out.println("write your code: ");
        String yourCode = scanner.next();
        while (!checker(secretCode, yourCode, Integer.parseInt(length)).contains("Congratulations!")) {
            System.out.println(checker(secretCode, yourCode, Integer.parseInt(length)));
            System.out.println("write your code: ");
            yourCode = scanner.next();
        }
        System.out.println(checker(secretCode, yourCode, Integer.parseInt(length)));
    }

    private static String checker(String secretCode, String yourCode, int length) {
        int countOfCows = 0;
        int countOfBulls = 0;
        for (int i = 0; i < secretCode.length(); i++) {
            if (secretCode.charAt(i) == yourCode.charAt(i)) {
                countOfBulls++;
            }
            for (int j = 0; j < yourCode.length(); j++) {
                if (secretCode.charAt(i) == yourCode.charAt(j)) {
                    countOfCows++;
                }
            }
        }
        if (length == countOfBulls) {
            return countOfBulls + " bulls\nCongratulations! You guessed the secret code.";
        }
        countOfCows -= countOfBulls;
        return countOfCows > 0 && countOfBulls == 0 ? "Grade: " + countOfCows + " cows."
                : countOfBulls > 0 && countOfCows == 0 ? "Grade: " + countOfBulls + " bulls."
                : countOfBulls == 0 && countOfCows == 0 ? "Grade: None."
                : "Grade: " + countOfBulls + " bulls and " + countOfCows + " cows.";
    }
}
