package algorithm.sort;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/28 19:55
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {0, 6, 12, 10, 8, 4, 7, 6, 7, 13, 2, 6, 7, 0, 13};
        //selectionSort(arr);
        selectionSort2(arr);
        printArray(arr);
    }

    static void selectionSort(int[] arr) {

        int minIndex;
        for(int i=0; i<arr.length; i++){
            minIndex=i;
            for(int j=i+1; j<arr.length; j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }

    }

    static void selectionSort2(int[] arr) {

        int minIndex, maxIndex;
        for(int i=0; i<arr.length/2; i++){
            minIndex=i;
            maxIndex=i;
            for(int j=i+1; j<arr.length-i; j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
                if(arr[j] > arr[maxIndex]){
                    maxIndex = j;
                }
            }
            swap(arr, i, minIndex);
            if(maxIndex == i){ //坑点
                swap(arr, arr.length-1-i, minIndex);
            }else{
                swap(arr, arr.length-1-i, maxIndex);
            }
        }

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
