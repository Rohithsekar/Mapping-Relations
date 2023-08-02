package com.example.mapping.utilities;

import java.util.Random;

public class Utility {

    /**
     * In the generateRandomTenDigitNumber method, we use the Random class from the java.util package to generate a
     * random number between 1,000,000,000 (inclusive) and 9,999,999,999 (exclusive). This will guarantee that the
     * number is a ten-digit integer.
     *
     * Note: Since the maximum value of an int in Java is 2,147,483,647, and we want a ten-digit number, we add
     * 1,000,000,000 to the random number generated, ensuring it is in the required range.
     */

    public static int generateRandomTenDigitNumber() {
        Random random = new Random();
        // Generate a random number between 1,000,000,000 and 9,999,999,999
        int randomNumber = random.nextInt(900000000) + 1000000000;
        return randomNumber;
    }

    public static long generateRandom16DigitNumber() {
        Random random = new Random();
        long leftLimit = 1000000000000000L; // 16-digit number starts with 1 followed by 15 zeros
        long rightLimit = 9999999999999999L; // 16-digit number with all digits as 9s
        return leftLimit + (long) (random.nextDouble() * (rightLimit - leftLimit));
    }
}
