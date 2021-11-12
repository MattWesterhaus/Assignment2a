
/**
 * CISC 380
 * Algorithms Assignment 2
 *
 * Driver file for Max Sum Subarray and Dominant Entry in Assignment2.java
 *
 *
 * @author Matt Westerhaus and Sam Kulesa
 * Due Date: 10/10/21
 */


public class Assignment2Driver {

    public static void main(String[] args){

        int[] array = {4, -6, 3, 1, 9, -8, 2, -10, 13};
        System.out.println(Assignment2.maxSubArray(array));

        int[] array2 = {1, 3, 5, 3, 3, 6, 3};
        System.out.println(Assignment2.dominant(array2));
    }//main

}