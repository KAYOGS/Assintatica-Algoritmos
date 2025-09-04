package algoritmos.assintatica.codigos;

public class BubbleSort {
   public static void bubbleSort(int[] vetor) {
      int auxiliar;
      int i, j;
      boolean swapped;
      System.out.println("Vetor desordenado");
      System.out.println("\n");
      for (i = 0; i < vetor.length - 1; i++) {
         System.out.println(" " + vetor[i]);
      }
      System.out.println(" ");
      for (i = 0; i < vetor.length - 1; i++) {
         System.out.println("Loop: " + (i + 1));
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
         System.out.println("Vetor ordenado");
         System.out.println("\n");
         for (i = 0; i < vetor.length; i++) {
            System.out.println(" " + vetor[i]);
         }
      }
   }
}
