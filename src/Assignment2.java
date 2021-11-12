/**
 * CISC 380
 *
 * Starter code for Assignment 2
 * @author Matt Westerhaus & Sam Kulesa
 * Due Date: 10/10/21
 */


public class Assignment2 {

    /**
     * STARTER CODE FOR PROBLEM 2: Maximum Subarray
     *
     * @param array the array where the maximum subarray will be found
     * @return the sum of the subarray with the maximum sum
     */
    public static int maxSubArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        else if (array == null) {
            return 0;
        }
        else {
            return maxSubArrayRecursive(array, 0, array.length-1);
        }
    }

    /**
     * STARTER CODE FOR PROBLEM 2: Maximum Subarray
     *
     * @param array the array where the maximum subarray will be found
     * @param start the starting index where the recursive method will check
     * @param end the ending index where the recursive method will check
     * @return the sum of the subarray with the maximum sum.
     * Note: this method will return 0 if the length of the array is 0 and will be negative if the
     * array contains only negative numbers
     */
    private static int maxSubArrayRecursive(int[] array, int start, int end) {
        // ADD CODE TO COMPLETE THIS METHOD
        // HINT: you will need to add a base case and change the return value
        if(array.length == 0) return 0;

        if(start - end == 0) return array[start];

        int mid;
        int maxLeft;
        int maxRight;
        int combined;

        mid = (start + end) / 2;

        maxLeft = maxSubArrayRecursive(array, start, mid);
        maxRight = maxSubArrayRecursive(array, mid+1, end);
        combined = maxCombined(array, start, end, mid);

        //HINT: you will need to use maxLeft and maxRight in your solution

        return Math.max(Math.max(maxLeft, maxRight), combined);  // You will want to change this return value
    }

    static int maxCombined(int array[], int start, int end, int mid)
    {
        int sum;
        int leftSum;
        int rightSum;

        sum=0;
        leftSum = array[mid];
        rightSum= array[mid+1];

        // find the max sum of the left half, starting at the right end of the left subarray.
        for (int i=mid; i>= start; i--) {
            sum = sum+array[i];
            if (sum>leftSum) {
                leftSum = sum;
            }
        }

        // find the max sum of the right half, starting at the left end of the right subarray.
        sum = 0; //resetting to 0
        for (int i=mid+1; i<=end; i++) {
            sum =sum + array[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }

        return (leftSum+rightSum);
    }

    /**
     * STARTER CODE FOR PROBLEM 3: Dominating Entry
     *
     * @param array the array which will be evaluated for containing a dominant entry
     * @return the dominating entry, will return null if there is no dominating entry (return type Integer)
     */
    public static Integer dominant(int[] array) {

        if (array.length == 0) {
            return null;
        }
        else if (array == null) {
            return null;
        }
        return dominantRecursive(array, 0, array.length);
    }


    private static int dominantRecursive(int[] array, int start, int end) {
        // HINT: you will need to add a base case and change the return value
        if (end - start == 0) {
            return array[start];
        }

        int lMaj = dominantRecursive(array, start, end / 2);
        int rMaj = dominantRecursive(array, (end / 2) + 1, end);
        if (rMaj == lMaj) {
            return rMaj;
        } else if (rMaj > array.length / 2) {
            return rMaj;
        } else if (lMaj > array.length / 2) {
            return lMaj;
        }   else{
            int rTimes =0;
            int lTimes =0;
            for(int i=start; i<end; i++) {
                if (array[i] == rMaj) {
                    rTimes++;
                }
                if (array[i] == lMaj) {
                    lTimes++;
                }
            }
            if(rTimes > (end-start)/2){
                return rMaj;
            } else if (lTimes > (end-start)/2) {
                return lMaj;
            } else {
                return -1;
            }
        }
    }


    /**
     * STARTER CODE FOR PROBLEM 5a: Brute Force Hill
     *
     * Implements a brute force approach to finding a hill in an array
     * @param arr - an array of integers
     * @return - the index of a hill within the array

     * This algorithm works by checking the array to see first if it is there is one lone element which would return the index 0, then if there are only
     * two elements which would return whichever index holds a higher value and finally if there are three or more elements the algorithm checks each element of the array
     * to see if the element (i) is greater than the element before it (arr[i-1]) and following it (arr[i+1])
     *
     * everything outside of the for loop works in constant time and the for loop runs exactly n-1 times with constant work inside of it so the running time for this algorithm is
     * O(n-1) +c which can be simplified to O(n)
    */
    public static int bruteHill(int[] arr){
        if (arr.length == 0) {
            return -1;
        } else if (arr.length == 1){
            return 0;
        } else if (arr.length == 2){
            if (arr[0] > arr[1]) {
                return 0;
            } else
                return 1;
        } else {
            int result =0;
            for (int i=1; i< arr.length-1; i++) {
                if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                    return i;
                }
            }
        }
        return -1;

    }


    /**
     * STARTER CODE FOR PROBLEM 5b: Divide and Conquer Hill
     *
     * Implements a divide and conquer approach to finding a hill in an array
     * @param arr - an array of integers
     * @return - the index of a hill within the array
     *
     * The recurrence relation for this algorithm is 2T(n/2) +c. 2 recursive calls are made within the algorithm
     * amd each cut the array in half resulting in two branches being having T(n/2) running time. Everything
     * outside of the recursive calls runs constant being that there is nothing that changes based on
     * the size of the inputted array. Therefore the recurrence for this algorithm is 2T(n/2) +c.
     * The running time for this can be solved by the Master Theorem. A = 2, b = 2, and k =0. This triggers
     * the theorem's third case being that a > b^k, 2 > 2^0 or 2>1. This means that the algorithm is
     * θ(n^log2). Log(base 2)*2 simplifies to 1 so this algorithm is θ(n^1) or finally, θ(n).
     */
    public static int divideAndConquerHill(int[] arr){
        return dcrecursive(arr, 0, arr.length-1);
    }

    public static int dcrecursive(int[] arr, int startIndex, int endIndex){
        if (endIndex - startIndex <=0){
            return -1;
        } else if (endIndex - startIndex == 1){
            return endIndex-startIndex;
        } else if (endIndex - startIndex ==2) {
            if (arr[startIndex] > arr[endIndex]) {
                return startIndex;
            } else {
                return endIndex;
            }
        }

        int middleL = (((endIndex-startIndex)/2)-1) + startIndex;
        int middleR = ((endIndex-startIndex)/2) + startIndex;

        if (arr[middleL] > arr[middleR]) {
            return dcrecursive(arr, startIndex, middleL);
        } else {
            return dcrecursive(arr, middleR, endIndex);
        }
    }
}