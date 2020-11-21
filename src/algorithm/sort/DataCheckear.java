package algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/29 9:13
 */
public class DataCheckear {

    static int[] generateRandomArray(){
        Random random = new Random();
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10000);
        }
        return arr;
    }

    static boolean check(){
        int[] arr1 = generateRandomArray();
        int[] arr2 = new int[arr1.length];

        System.arraycopy(arr1, 0, arr2, 0, arr1.length);

        Arrays.sort(arr1);
        //SelectionSort.selectionSort(arr2);
        //SelectionSort.selectionSort2(arr2);
        //BubbleSort.bubbleSort(arr2);
        //InsertionSort.insertionSort(arr2);
        //ShellSort.shellSort(arr2);
        //MergeSort.mergeSort(arr2, 0, arr2.length-1);
        //QuickSort.quickSort(arr2, 0, arr2.length-1);
        QuickSort.quickSort3Ways(arr2, 0, arr2.length-1);
        boolean same = true;
        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i] != arr2[i]){
                same = false;
                break;
            }
        }

        return same;
    }

    public static void main(String[] args) {
        System.out.println(check() ? "right" : "error");
    }
}
