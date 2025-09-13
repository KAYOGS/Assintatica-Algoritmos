package algoritmos.assintatica.codigos;

public class BubbleSort {
   public static void bubbleSort(int[] vetor) {
      int auxiliar;
      int i, j;
      boolean swapped;
      
      for (i = 0; i < vetor.length - 1; i++) {
         swapped = false;
         for (j = 0; j < vetor.length - i - 1; j++) {
            if (vetor[j] > vetor[j + 1]) {
               auxiliar = vetor[j];
               vetor[j] = vetor[j + 1];
               vetor[j + 1] = auxiliar;
               swapped = true;
            }
         }
         if (!swapped) break;
      }
   }
}
