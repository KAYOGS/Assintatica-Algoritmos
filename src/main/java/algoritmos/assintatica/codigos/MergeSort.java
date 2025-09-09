package algoritmos.assintatica.codigos;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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

   public static void inserirValores() {
      try (Scanner scan = new Scanner(System.in)) {
         Integer tamanho = 0, valorInicial = 0, valorFinal = 0;
         long inicio, fim;
         while (tamanho <= 0 || valorFinal <= valorInicial) {
            System.out.println("Informe o tamanho do vetor: ");
            tamanho = scan.nextInt();
            System.out.println("Informe o valor inicial do Random: ");
            valorInicial = scan.nextInt();
            System.out.println("Informe o valor final do Random: ");
            valorFinal = scan.nextInt();
         }
         int[] dados = new Random().ints(tamanho, valorInicial, valorFinal).toArray();
         System.out.println("Vetor desordenado: ");
         System.out.println(Arrays.toString(dados));
         System.out.println("\n");
         inicio = System.nanoTime();
         mergeSort(dados);
         fim = System.nanoTime();
         System.out.println("\n");
         System.out.println("Vetor ordenado:");
         System.out.println(Arrays.toString(dados));
         System.out.println("Tempo de execução do Merge Sort: " + (fim - inicio) + "ms");
      } catch (Exception e) {
         System.out.println("Houve uma excessão durante a execução: " + e.getMessage());
      }
   }
}
