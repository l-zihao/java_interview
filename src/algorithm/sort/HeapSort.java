package algorithm.sort;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 10:21
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {0, 6, 12, 10, 8, 4, 7, 6, 7, 13, 2, 6, 7, 0, 13};
        heapSort(arr);
        printArray(arr);
    }

    static void heapSort(int[] arr) {
        //1.构建小顶堆
        for(int i=arr.length/2-1; i>=0; i--){
            shiftDown(arr, i, arr.length);
        }
        //2.原地堆排序
        for(int j=arr.length-1; j>0; j--){
            swap(arr, 0, j);
            shiftDown(arr, 0, j);
        }
    }

    private static void shiftDown(int[] arr, int k, int len) {
        while(2*k+1<len){
            int j=2*k+1;
            if(j+1<len && arr[j+1]<arr[j]){
                j+=1;
            }
            if(arr[k]<=arr[j]) break;
            swap(arr, k, j);
            k=j;
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
