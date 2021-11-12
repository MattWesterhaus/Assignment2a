import java.util.*;


/**
 * CISC 380
 * Algorithms Assignment 2
 *
 * Driver file for the bruteHill() and divideAndConquerHill() methods in Assignment2.java
 * Tests and calculates the performance of 
 * the brute force and Divide & Conquer algorithms to find a hill.
 *
 *
 * @author Matt Westerhaus and Sam Kulesa
 * Due Date: 10/10/21
 */



public class Assignment2HillDriver {


    public static void main(String[] args){
        executionTimeOnSize(1000,2048);
    }//main

    /**
     * Initializes the tests so the first test does not take the initial drop in performance
     *
     */
    public static void initialize(){
        for(int i = 0; i < 1000; i++){
            testBrute(getRandomArray(1000));
            testDandC(getRandomArray(1000));
        }
    }//initialize

    /**
     * tests the brute force and DandC implementations on arrays of size of ascending powers of 2, up to max size.
     * and prints the results of average execution time over the given amount of iterations.
     * @param iterations the number of iterations to perform on each size
     * @param maxSize    the max size of array to be tested.
     *
     */
    public static void executionTimeOnSize(int iterations,int maxSize){
        initialize();
        System.out.format("Test Case\tSize\tBruteForce\tDandC\t\tdifference\n");
        for(int i = 1; i <= maxSize; i = i*2){
            executionTimeRandom(iterations,i);
        }
        for(int i = 1; i <= maxSize; i = i*2){
            executionTimeBeginning(iterations,i);
        }
        for(int i = 1; i <= maxSize; i = i*2){
            executionTimeOneHill(iterations,i);
        }

    }//performanceOnSize

    /**
     * returns an array with exactly one hill at a random location
     * @param length - the length of the array to be generated
     * @return an array of integers with exactly one hill
     */
    public static int[] getOneHillArray(int length){
        if(length < 0){
            throw new IllegalArgumentException("length cannot be negative.");
        }

        int[] arr = new int[length];
        Random r = new Random();

        //index of the hill
        int hillIdx = r.nextInt(length);

        for(int i = 0; i < length; i++){
            if(i <= hillIdx){
                //assign the value of the current index.
                arr[i] = i;

            }else if(i > hillIdx){
                //assign the value from the previous index minus one.
                arr[i] = arr[i-1] - 1;
            }
        }
        return arr;
    }//getOneHillArray

    /**
     * creates a randomly generated array that guarantees a hill at the start of the array
     * @param length The length of the array. If less than 2, this method will return an array of size 2.
     * @return an array with a hill at the starting indexes
     *
     */
    public static int[] getHillAtFrontArray(int length){
        int[] result;
        if(length < 2){
            result = new int[2];
        }else{
            result = getRandomArray(length);
        }
        result[0] = 1;
        result[1] = 0;

        return result;

    }//getHillAtFrontArray

    /**
     * Gets a random array of integers
     * @return an array of randomly generated integers
     */
    public static int[] getRandomArray(int length){

        int[] arr = new int[length];
        Random r = new Random();

        for(int i = 0; i < length; i++){
            arr[i] = r.nextInt(100);
        }
        return arr;
    }//getRandomArray

    /**
     * calculates the average execution time of the brute force and D&C implementations
     *  of the hill problem on arrays with one random hill.
     * prints to the screen the average execution time of both implementations.
     * @param iterations the number of times the test is done before averaging.
     * @param length the length of the arrays tested
     */
    public static void executionTimeOneHill(int iterations, int length){

        double avgBrute = 0.0;
        double avgDandC = 0.0;

        for(int i = 0; i < iterations; i++){
            int[] arr     = getOneHillArray(length);
            int[] arrCopy = Arrays.copyOf(arr,arr.length);

            avgBrute = avgBrute + testBrute(arr);
            avgDandC = avgDandC + testDandC(arrCopy);
        }
        avgBrute = avgBrute / iterations;
        avgDandC = avgDandC / iterations;
        double difference = avgBrute - avgDandC;
        System.out.format("OneHill\t%8d\t%-4.3f ns\t%-4.3f ns\t%-4.3f ns\n",length, avgBrute, avgDandC,difference);


    }//executionTimeOneHill

    /**
     * calculates the average execution time of the brute force and D&C implementations
     * of the hill problem on arrays that start with a hill.
     * prints to the screen the average execution time of both implementations.
     * @param iterations the number of times the test is done before averaging.
     * @param length the length of the arrays tested
     */
    public static void executionTimeBeginning(int iterations,int length){

        double avgBrute = 0.0;
        double avgDandC = 0.0;

        for(int i = 0; i < iterations; i++){
            int[] arr     = getHillAtFrontArray(length);
            int[] arrCopy = Arrays.copyOf(arr,arr.length);

            avgBrute = avgBrute + testBrute(arr);
            avgDandC = avgDandC + testDandC(arrCopy);
        }
        avgBrute = avgBrute / iterations;
        avgDandC = avgDandC / iterations;
        double difference = avgBrute - avgDandC;
        System.out.format("Begin\t%8d\t%-4.3f ns\t%-4.3f ns\t%-4.3f ns\n",length, avgBrute, avgDandC,difference);
    }//executionTimeBeginning

    /**
     * calculates the average execution time of the brute force and D&C implementations
     * of the hill problem on random arrays.
     * prints to the screen the average execution time of both implementations.
     * @param iterations the number of times the test is done before averaging.
     * @param length the length of the arrays tested
     */
    public static void executionTimeRandom(int iterations, int length){

        double avgBrute = 0.0;
        double avgDandC = 0.0;

        for(int i = 0; i < iterations; i++){
            int[] arr     = getRandomArray(length);
            int[] arrCopy = Arrays.copyOf(arr,arr.length);

            avgBrute = avgBrute + testBrute(arr);
            avgDandC = avgDandC + testDandC(arrCopy);
        }
        avgBrute = avgBrute / iterations;
        avgDandC = avgDandC / iterations;
        double difference = avgBrute - avgDandC;
        System.out.format("Random\t%8d\t%-4.3f ns\t%-4.3f ns\t%-4.3f ns\n",length, avgBrute, avgDandC,difference);


    }//executionTimeRandom

    /**
     * gets the execution time, in nanoseconds
     * for the brute force implementation to find a hill
     * in the given array.
     *
     * @param array - the array to search for a hill
     * @return the time in nanoseconds to find a hill
     *
     */
    public static long testBrute( int[] array){

        long result = -1;

        long start  =  System.nanoTime();
        Assignment2.bruteHill(array);
        long end    =  System.nanoTime();
        result = end -start;

        return result;
    }//testBrute

    /**
     * gets the execution time, in nanoseconds
     * for the divide and conquer implementation to find a hill
     * in the given array.
     *
     * @param array - the array to search for a hill
     * @return the time in nanoseconds to find a hill
     *
     */
    public static long testDandC(int[] array){
        long result = -1;

        long start  = System.nanoTime();
        Assignment2.divideAndConquerHill(array);
        long end    =  System.nanoTime();
        result = end - start;

        return result;
    }//testDandC


}//HillDriver