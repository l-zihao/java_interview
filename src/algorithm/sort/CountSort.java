package algorithm.sort;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 11:27
 */
public class CountSort {

    public static void main(String[] args) {
        int[] arr = {0, 6, 5, 1, 8, 4, 7, 6, 7, 1, 2, 6, 7, 0, 8, 9};
        int[] result = countSort(arr);
        printArray(result);
    }

    private static int[] countSort(int[] arr) {
        int[] result = new int[arr.length];
        int[] count = new int[10];

        for(int i=0; i<arr.length; i++){
            count[arr[i]]++;
        }

        for(int i=0,j=0; i<count.length; i++){
            while (count[i]>0){
                result[j++]=i;
                count[i]--;
            }
        }
        return result;
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
