package algoritmos.assintatica;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class Entradas {
    public static Map<String, int[]> gerarTodasEntradas(int tamanho) {
        Map<String, int[]> entradas = new HashMap<>();
        Random rand = new Random();

        int[] baseComRepeticao = new int[tamanho];
        for (int j = 0; j < tamanho; j++) {
            baseComRepeticao[j] = rand.nextInt(tamanho);
        }

        Set<Integer> uniqueNums = new HashSet<>();
        while (uniqueNums.size() < tamanho) {
            uniqueNums.add(rand.nextInt(tamanho * 10));
        }
        int[] baseUnico = uniqueNums.stream().mapToInt(Integer::intValue).toArray();

        int[] crescenteComRepeticao = baseComRepeticao.clone();
        Arrays.sort(crescenteComRepeticao);
        entradas.put("Crescente com repeticao", crescenteComRepeticao);

        int[] decrescenteComRepeticao = crescenteComRepeticao.clone();
        for (int j = 0; j < tamanho / 2; j++) {
            int temp = decrescenteComRepeticao[j];
            decrescenteComRepeticao[j] = decrescenteComRepeticao[tamanho - 1 - j];
            decrescenteComRepeticao[tamanho - 1 - j] = temp;
        }
        entradas.put("Decrescente com repeticao", decrescenteComRepeticao);

        entradas.put("Aleatorio com repeticao", baseComRepeticao.clone());

        int[] crescenteSemRepeticao = baseUnico.clone();
        Arrays.sort(crescenteSemRepeticao);
        entradas.put("Crescente sem repeticao", crescenteSemRepeticao);

        int[] decrescenteSemRepeticao = crescenteSemRepeticao.clone();
        for (int j = 0; j < tamanho / 2; j++) {
            int temp = decrescenteSemRepeticao[j];
            decrescenteSemRepeticao[j] = decrescenteSemRepeticao[tamanho - 1 - j];
            decrescenteSemRepeticao[tamanho - 1 - j] = temp;
        }
        entradas.put("Decrescente sem repeticao", decrescenteSemRepeticao);

        entradas.put("Aleatorio sem repeticao", baseUnico.clone());

        return entradas;
    }
}