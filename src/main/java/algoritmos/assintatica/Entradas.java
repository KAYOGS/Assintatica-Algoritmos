package algoritmos.assintatica;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class Entradas {
    public static Map<String, int[]> gerarTodasEntradas(int tamanho) {
        Map<String, int[]> entradas = new HashMap<>();
        Random rand = new Random();

        int[] baseUnico = new int[tamanho];
        Set<Integer> uniqueNums = new HashSet<>();
        while (uniqueNums.size() < tamanho) {
            uniqueNums.add(rand.nextInt(tamanho * 10));
        }
        int i = 0;
        for (Integer num : uniqueNums) {
            baseUnico[i++] = num;
        }

        int[] baseComRepeticao = new int[tamanho];
        for (int j = 0; j < tamanho; j++) {
            baseComRepeticao[j] = rand.nextInt(tamanho);
        }

        int[] crescenteComRepeticao = Arrays.copyOf(baseComRepeticao, tamanho);
        Arrays.sort(crescenteComRepeticao);
        entradas.put("Crescente com repeticao", crescenteComRepeticao);

        int[] decrescenteComRepeticao = Arrays.copyOf(crescenteComRepeticao, tamanho);
        for (int j = 0; j < tamanho / 2; j++) {
            int temp = decrescenteComRepeticao[j];
            decrescenteComRepeticao[j] = decrescenteComRepeticao[tamanho - 1 - j];
            decrescenteComRepeticao[tamanho - 1 - j] = temp;
        }
        entradas.put("Decrescente com repeticao", decrescenteComRepeticao);

        entradas.put("Aleatorio com repeticao", baseComRepeticao);

        int[] crescenteSemRepeticao = Arrays.copyOf(baseUnico, tamanho);
        Arrays.sort(crescenteSemRepeticao);
        entradas.put("Crescente sem repeticao", crescenteSemRepeticao);

        int[] decrescenteSemRepeticao = Arrays.copyOf(crescenteSemRepeticao, tamanho);
        for (int j = 0; j < tamanho / 2; j++) {
            int temp = decrescenteSemRepeticao[j];
            decrescenteSemRepeticao[j] = decrescenteSemRepeticao[tamanho - 1 - j];
            decrescenteSemRepeticao[tamanho - 1 - j] = temp;
        }
        entradas.put("Decrescente sem repeticao", decrescenteSemRepeticao);

        int[] aleatorioSemRepeticao = baseUnico;
        entradas.put("Aleatorio sem repeticao", aleatorioSemRepeticao);

        return entradas;
    }
}
