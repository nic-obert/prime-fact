package com.uba.factorizer;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to prime factorizer :)");

        BigInteger inputNumber;
        while (true) {

            inputNumber = getInput();
            if (checkNumber(inputNumber) == -1) {
                break;
            }
            long start = System.currentTimeMillis();
            BigInteger[][] result = factorize(inputNumber);
            long end = System.currentTimeMillis() - start;
            displayResult(result[0], result[1]);
            System.out.println("\nIt took " + end + " ms");
        }

    }

    private static void displayResult(BigInteger[] factors, BigInteger[] numbers) {
        System.out.println("This is your number factorized");
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == null) {
                break;
            }
            System.out.println(numbers[i] + " | " + factors[i]);
        }
    }

    private static BigInteger getInput() {
        System.out.print("Enter your number to factorize: ");
        String input = Main.scanner.next();
        if (input.equals("exit")) {
            System.exit(0);
        }
        return new BigInteger(input);
    }

    private static byte checkNumber(BigInteger inputNumber) {
        if (BigInteger.valueOf(1).compareTo(inputNumber) > 0) {
            System.out.println("Your has to be greater than 0!");
            return -1;
        }
        return 1;

    }

    private static BigInteger[][] factorize(BigInteger number) {
        BigInteger root;
        BigInteger[] factors = new BigInteger[100];
        BigInteger[] numbers = new BigInteger[100];
        numbers[0] = number;
        int factorsIndex = 0;
        boolean factorizing = true;
        boolean foundFactor;

        while (factorizing) {
            foundFactor = false;
            root = number.sqrt();
            for (BigInteger i = BigInteger.valueOf(2); i.compareTo(root.add(BigInteger.valueOf(1))) < 0; i = i.add(BigInteger.valueOf(1))) {
                if (number.mod(i).equals(BigInteger.valueOf(0))) {
                    System.out.println();
                    factors[factorsIndex] = i;
                    factorsIndex ++;
                    number = number.divide(i);
                    numbers[factorsIndex] = number;
                    foundFactor = true;
                    break;
                }
            }
            if (!foundFactor) {
                factors[factorsIndex] = number;
                numbers[factorsIndex+1] = BigInteger.valueOf(1);
                factorizing = false;
            }
        }
        return new BigInteger[][] {
                factors,
                numbers
        };
    }
}
