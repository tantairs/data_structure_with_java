package com.lianxi.data_structure_with_java;

/**
 * Created by tantairs on 16/4/10.
 */
public class BinarySearch {
    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 6, 7};
        int result = doBinarySearchIte(array, 0, array.length - 1, 5);
        BinarySearch binarySearch = new BinarySearch();
        int result2 = binarySearch.doBinarySearchRec(array,0,array.length-1,5);
        System.out.println(result+"; "+ result2);
    }

    public static int doBinarySearchIte(int[] array, int begin, int end, int x) {
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (array[mid] < x)
                begin = mid + 1;
            if (array[mid] > x)
                end = mid - 1;
            if (array[mid] == x)
                return mid;
        }
        return -1;
    }

    public int doBinarySearchRec(int[] array, int begin, int end, int x) {
        int mid = (begin + end) / 2;
        if (array[mid] == x)
            return mid;
        else if (array[mid] > x)
            return doBinarySearchRec(array, begin, mid - 1, x);
        else if (array[mid] < x)
            return doBinarySearchRec(array, mid + 1, end, x);
        return -1;
    }
}
