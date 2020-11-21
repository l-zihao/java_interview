package algorithm.sort;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/29 12:06
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 7, 8, 6, 9, 5};
        shellSort(arr);
        printArray(arr);
    }

    static void shellSort(int[] arr) {
        int h=1;
        while(h<=arr.length/3){
            h = h * 3 + 1;
        }

        for (int gap=h; gap>0; gap=(gap-1)/3){
            for(int i=gap; i<arr.length; i++){
                int temp=arr[i];
                int j;
                for(j=i; j>gap-1 && arr[j-gap]>temp; j-=gap){
                    arr[j]=arr[j-gap];
                }
                arr[j]=temp;
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
