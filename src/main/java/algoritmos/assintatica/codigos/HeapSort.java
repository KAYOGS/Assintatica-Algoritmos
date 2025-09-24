package algoritmos.assintatica.codigos;

public class HeapSort {
    public static void heapSort(int[] vetor) {
        int n = vetor.length;

        // Constrói o heap máximo (reorganiza o array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(vetor, n, i);
        }

        for (int j = n - 1; j > 0; j--) {
            int aux = vetor[0];
            vetor[0] = vetor[j];
            vetor[j] = aux;

            heapify(vetor, j, 0);
        }
    }

    private static void heapify(int[] vetor, int n, int i) {
        int raiz = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && vetor[left] > vetor[raiz]) {
            raiz = left;
        }

        if (right < n && vetor[right] > vetor[raiz]) {
            raiz = right;
        }

        if (raiz != i) {
            int aux = vetor[i];
            vetor[i] = vetor[raiz];
            vetor[raiz] = aux;

            heapify(vetor, n, raiz);
        }
    }
}