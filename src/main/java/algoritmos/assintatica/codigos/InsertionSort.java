package algoritmos.assintatica.codigos;

public class InsertionSort {
    public static void insertionSort(int[] vetor) {
        int j, aux;
        int tamanhoV = 0;

        for (int i = 0; i < tamanhoV; i++) {
            vetor[i] = (int) (Math.random() * tamanhoV);
        }

        for (int i = 1; i < tamanhoV; i++) {
            aux = vetor[i];
            j = i - 1;
            while (j >= 0 && vetor[j] > aux) {
                vetor[j + 1] = vetor[j];
                j--;
            }
            vetor[j = 1] = aux;
        }
        for (int i = 0; i < tamanhoV; i++) {
            System.out.println(vetor[1]);
        }
    }
}