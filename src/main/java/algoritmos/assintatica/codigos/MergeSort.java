package algoritmos.assintatica.codigos;

public class MergeSort {
   public static void mergeSort(int[] vetor) {
      if (vetor.length <= 1)
         return;
      int mid = vetor.length / 2;
      int[] left = new int[mid];
      int[] right = new int[vetor.length - mid];
      System.arraycopy(vetor, 0, left, 0, mid);
      System.arraycopy(vetor, mid, right, 0, vetor.length - mid);
      mergeSort(left);
      mergeSort(right);
      merge(vetor, left, right);
   }

   public static void merge(int[] vetor, int[] left, int[] right) {
      int i = 0, j = 0, k = 0;
      while (i < left.length && j < right.length) {
         if (left[i] <= right[j]) {
            vetor[k++] = left[i++];
         } else {
            vetor[k++] = right[j++];
         }
      }
      while (i < left.length) {
         vetor[k++] = left[i++];
      }
      while (j < right.length) {
         vetor[k++] = right[j++];
      }
   }
}
