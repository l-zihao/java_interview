package algorithm.sort;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/29 11:26
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 7, 8, 6, 9, 5};
        mergeSort(arr, 0, arr.length-1);
        printArray(arr);
    }

    static void mergeSort(int[] arr, int l, int r) {
        if(l == r){
            return;
        }

        int mid = l + (r-l) /2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid+1, r);

        if(arr[mid] > arr[mid+1]){
            merge(arr, l, mid, r);
        }
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] cur = new int[r-l+1];

        int lt= l, gt=mid+1, k=0;
        while(k < cur.length){
            if(lt <= mid && gt <= r && arr[lt] <= arr[gt]){
                cur[k++] = arr[lt++];
            }else if(lt <= mid && gt <= r && arr[gt] < arr[lt]){
                cur[k++] = arr[gt++];
            }else if(lt <= mid){
                cur[k++] = arr[lt++];
            }else {
                cur[k++] = arr[gt++];
            }
        }

        for (int i = 0; i < cur.length; i++) {
            arr[l+i] = cur[i];
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
