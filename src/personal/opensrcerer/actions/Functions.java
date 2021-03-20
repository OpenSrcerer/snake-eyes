/*
 * Made for the Final Project in CS105, due December 4th 2020. <br>
 * This work is licensed under the GNU General Public License v3.0 <br>
 * GNU © 2020 Daniel Stefani / OpenSrcerer
 */

package personal.opensrcerer.actions;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * A class with functions created especially for this project's
 * calculations and arranged for easy access.
 */
public final class Functions {

    /**
     * @return Array of random longs valued at 0-100;
     */
    public static long[] getRandomValues() {
        Random random = new Random();
        long[] array = new long[6];

        for (int index = 0; index < 6; ++index) {
            array[index] = random.nextInt(101);
        }

        return array;
    }

    /**
     * @param population The population of integers.
     * @param mean Mean of this population.
     * @return Statistical variance of the population.
     */
    public static double getVariance(Collection<Integer> population, double mean) {
        double sqrDiff = 0;

        for (Integer integer : population) {
            sqrDiff = sqrDiff + Math.pow(integer - mean, 2);
        }

        return sqrDiff / population.size();
    }

    /**
     * @param s String to check
     * @param index Index to check
     * @return Whether the string s is a palindrome or not.
     */
    public static boolean isPalindrome(String s, int index) {
        if (index == (s.length() / 2))
            return true;

        if (s.charAt(index) != s.charAt(s.length() - index - 1))
            return false;

        return isPalindrome(s, index + 1);
    }

    /**
     * @param list A List of Integers.
     * @param multiplier What to multiply this list with.
     * @return The same list, with every element multiplied by given multiplier.
     */
    public static List<Integer> multiplyListBy(List<Integer> list, int multiplier) {
        return list.stream().map(e -> e * multiplier).collect(Collectors.toList());
    }

    /**
     * @param ints Array of integers to test
     * @param average Calculated average of these numbers
     * @return Number in the array closest to the average
     */
    public static int getClosestToAverage(int[] ints, double average) {
        int closest = 0;
        double delta = Double.MAX_VALUE;

        for (int i : ints) {
            if (Math.abs(average - i) < delta) {
                closest = i;
                delta = Math.abs(average - i);
            }
        }

        return closest;
    }

    /**
     * @param integer Given integer
     * @return Factorial of given integer
     */
    public static long getFactorial(int integer) {
        long returnValue = integer;

        for (long index = integer - 1; index >= 1; --index) {
            returnValue = returnValue * index;
        }

        return returnValue;
    }
}
