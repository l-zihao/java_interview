package algorithm.sort;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/29 9:26
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 7, 8, 6, 9, 5};
        bubbleSort(arr);
        printArray(arr);
    }

    static void bubbleSort(int[] arr) {

        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length-i-1; j++){
                if(arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                }
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
