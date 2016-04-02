package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/4/2.
 */
public class FindMaximumSubArray {

    int maxLeft,maxRight = 0;
    public static void main(String[] args){

        int[] array = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
        FindMaximumSubArray findMaximumSubArray = new FindMaximumSubArray();
        int result = findMaximumSubArray.findMaxiMumSubArray(array,0,array.length-1);
        System.out.println(result);
    }

    //求整个数组的最大子序列的和
    public int findMaxiMumSubArray(int[] array,int low,int high){
        int mid = 0;

        //注意,这里容易出现数组边界溢出
        if(high == low)
            return array[low];
        else{

            mid = (low+high)/2;
            int leftSum = findMaxiMumSubArray(array,low,mid);
            int rightSum = findMaxiMumSubArray(array,mid+1,high);
            int crossSum = findMaxCrossingSubArray(array,low,mid,high);
            if(leftSum>=rightSum&&rightSum>=crossSum)
                return leftSum;
            if(rightSum>=leftSum&&leftSum>=crossSum)
                return rightSum;
            return crossSum;
        }
    }

    //求跨越数组中间值的最大和
    public int findMaxCrossingSubArray(int[] array,int low,int mid,int high){
        int leftSum = -100000;
        int rightSum = -100000;
        int sum = 0;

        for(int i = mid; i >= low; i--){
            sum += array[i];
            if(sum > leftSum){
                leftSum = sum;
                maxLeft = i;
            }
        }
        sum = 0;
        for(int j = mid+1;j <= high; j++){
            sum += array[j];
            if(sum > rightSum){
                rightSum = sum;
                maxRight = j;
            }
        }

        return leftSum+rightSum;
    }
}
