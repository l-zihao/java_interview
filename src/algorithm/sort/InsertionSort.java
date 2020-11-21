package algorithm.sort;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/29 9:33
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 7, 8, 6, 9, 5};
        insertionSort(arr);
        printArray(arr);
    }

    static void insertionSort(int[] arr) {
        for(int i=1; i<arr.length; i++){
            int temp=arr[i];
            int j;
            for(j=i; j>0 && arr[j-1]>temp; j--){
                arr[j]=arr[j-1];
            }
            arr[j]=temp;
        }
    }

    /*//二分插入
    static void binarySort(int[] arr) {
        for(int i=1; i<arr.length; i++){
            int temp=arr[i];
            int j;
            int left = 0, right=i, mid;
            while(left < right){
                mid = (left+right)/2;
                if(arr[mid] > temp){
                    right = mid;
                }else if(arr[mid] < temp){
                    left = mid;
                }
            }
            arr[j]=temp;
        }
    }*/

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
