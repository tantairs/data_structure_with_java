package com.lianxi.the_beautity_of_programming;

/**
 * Created by tantairs on 10/9/16.
 */
public class MaxSubArraySum_2_14 {

    public static void main(String[] args){
        int[] array = {-5,7,1,4,-23,-4,6,-11,55,-8,5,11,-2,3,5,-13,2};
        System.out.println(maxSum(array));
        System.out.println(maxSumImproved1(array));
        System.out.println(maxSumImprovedDivideConque(0,array.length-1,array));
        System.out.println(maxSumImprovedDP(array));
    }

    /**
     * 时间复杂度 o(n3)
     * @param array
     * @return
     */
    public static int maxSum(int[] array){
        int maxValue = Integer.MIN_VALUE;
        for(int i = 1; i <= array.length; i++){
            for(int j = 0; j < array.length; j++){
                int temp = 0;
                for(int k = j; k < i+j && k < array.length; k++){
                    temp += array[k];
                }
                if(temp > maxValue){
                    maxValue = temp;
                }
            }
        }

        return maxValue;
    }

    /**
     * 时间复杂度O(n2)
     * @param array
     * @return
     */
    public static int maxSumImproved1(int[] array){
        int maxvalue = Integer.MIN_VALUE;
        for(int i = 0; i < array.length; i++){
            int temp = 0;
            for(int j = i; j < j+i+1 && j < array.length; j++){
                temp += array[j];
                if(temp > maxvalue)
                    maxvalue = temp;
            }
        }
        return maxvalue;
    }

    /**
     * 时间复杂度O(nlogn)
     * @param array
     * @return
     */
    public static int maxSumImprovedDivideConque(int begin, int end, int[] array){
        if(end == begin){
            return array[begin];
        }else {
            int mid = begin + (end - begin)/2;
            int leftMaxSum = maxSumImprovedDivideConque(begin,mid,array);
            int rightMaxSum = maxSumImprovedDivideConque(mid+1,end,array);

            int crossingMaxSum = crossingMaxSum(begin,end,array);
            return Max(leftMaxSum,rightMaxSum,crossingMaxSum);
        }


    }

    public static int crossingMaxSum(int begin, int end, int[] array){
        int mid = begin + (end - begin)/2;
        int leftMax = Integer.MIN_VALUE;
        int tempLeft = 0;
        for(int i = mid; i >= begin; i--){
            tempLeft += array[i];
            if(tempLeft > leftMax){
                leftMax = tempLeft;
            }
        }

        int rightMax = Integer.MIN_VALUE;
        int tempRight = 0;
        for(int i = mid+1; i <= end; i++){

            tempRight += array[i];
            if(tempRight > rightMax){
                rightMax = tempRight;
            }
        }

        int result = leftMax + rightMax;
        return result;
    }

    public static int Max(int a, int b){
        return a > b ? a : b;
    }

    public static int Max(int a, int b, int c){
        if(a >= b){
            if(b >= c ){
                return a;
            }else {
                if(a >= c){
                    return a;
                }else {
                    return c;
                }
            }
        }else {
            if(b >= c){
                return b;
            }else {
                return c;
            }
        }

    }

    /**
     * 用动态规划的思想去解决问题 时间复杂度为O(n)
     * @param array
     * @return
     */
    public static int maxSumImprovedDP(int[] array){
        int maxValue = Integer.MIN_VALUE;

        int currentSum = array[0];
        for(int i = 1; i < array.length; i++){
            if(currentSum <= 0){
                currentSum = array[i];
            }else {
                currentSum += array[i];
            }
            if(currentSum > maxValue){
                maxValue = currentSum;
            }

        }
        return maxValue;
    }

}
