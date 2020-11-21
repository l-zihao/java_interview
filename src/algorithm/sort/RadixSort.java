package algorithm.sort;

import java.util.Arrays;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 11:36
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {421, 240, 35, 532, 305, 430, 124};
        radixSort(arr);
        printArray(arr);
    }

    //实现基数排序 LSD-从最低位开始排 MSD-从最高位开始排
    private static void radixSort(int[] arr) {
        int[] result = new int[arr.length];
        int[] count = new int[10];

        int maxBin = maxBin(arr);
        for(int i=0; i<maxBin; i++){
            int division = (int) Math.pow(10, i);
            for (int j = 0; j < arr.length; j++) {
                int num = arr[j]/division%10;
                count[num]++;
            }

            for (int m = 1; m < count.length; m++) {
                count[m] = count[m] + count[m-1];
            }

            for (int n = 0; n < arr.length; n++) {
                int num = arr[n]/division%10;
                result[--count[num]] = arr[n];
            }

            System.arraycopy(result, 0, arr, 0, result.length);
            Arrays.fill(count, 0);
        }
    }

    //计算数组里元素的最大位数
    private static int maxBin(int[] arr) {
        int maxLen=0;
        for (int i = 0; i < arr.length; i++) {
            int size = Integer.toString(arr[i]).length();
            maxLen = size > maxLen ? size : maxLen;
        }
        return maxLen;
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
