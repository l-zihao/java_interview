package algorithm.sort;

import java.util.Random;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/29 15:13
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 7, 8, 6, 9, 5};
        //quickSort(arr, 0, arr.length-1);
        quickSort3Ways(arr, 0, arr.length-1);
        printArray(arr);
    }

    static void quickSort(int[] arr, int l, int r) {
        if(l>=r) return;

        int p = partition(arr, l, r);
        quickSort(arr, l, p-1);
        quickSort(arr, p+1, r);
    }

    static void quickSort3Ways(int[] arr, int l, int r) {
        if(l>=r) return;

        Random random = new Random();
        int pos = random.nextInt(r - l + 1) + l;
        swap(arr, l, pos);
        int lt=l, i=l+1, gt=r+1;
        while (i<gt){
            if(arr[i] < arr[l]){ //[l+1..lt]
                swap(arr, i, lt+1);
                lt++;
                i++;
            }else if(arr[i] > arr[l]){ //[gt..r]
                swap(arr, i, gt-1);
                gt--;
            }else {//[lt..i)
                i++;
            }
        }
        swap(arr, l, lt);

        quickSort(arr, l, lt-1);
        quickSort(arr, gt, r);
    }

    private static int partition(int[] arr, int l, int r) {
        Random random = new Random();
        int pos = random.nextInt(r - l + 1) + l;
        swap(arr, l, pos);

        int lt=l+1, gt=r;
        while (true){
            while(lt<=r && arr[lt] <= arr[l]){ //[l+1..lt)
                lt++;
            }
            while(gt>=l+1 && arr[gt] > arr[l]){ //(gt..r]
                gt--;
            }
            if(lt > gt) break;
            swap(arr, lt, gt);
            lt++;
            gt--;
        }
        swap(arr, l, gt);
        return gt;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
