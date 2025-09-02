package algoritmos.assintatica.codigos;

public class SelectSort {
    public static void selectSort(int[] array) {
        int length = array.length;

        for (int i = 0; i < length - 1; i++) {
            int indexI = i;

            for (int j = i + 1; j < length; j++)
                if (array[j] < array[i])
                    indexI = j;

            int temp = array[indexI];
            array[indexI] = array[i];
            array[i] = temp;
        }
    }
}
