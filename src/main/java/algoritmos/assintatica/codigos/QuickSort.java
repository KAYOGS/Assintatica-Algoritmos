package algoritmos.assintatica.codigos;

public class QuickSort {
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivoIndex = partition(array, low, high);
            quickSort(array, low, pivoIndex - 1);
            quickSort(array, pivoIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int middle = low + (high - low) / 2;
        
        int tempPivot = array[middle];
        array[middle] = array[high];
        array[high] = tempPivot;

        int pivot = array[high]; 
        
        int i = (low - 1); 

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp2 = array[i];
                array[i] = array[j];
                array[j] = temp2;
            }
        }
        
        int temp3 = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp3;

        return i + 1;
    }
}
