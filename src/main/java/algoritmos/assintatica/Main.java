package algoritmos.assintatica;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;

import algoritmos.assintatica.codigos.*;

import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {100000, 160000, 220000, 280000, 340000, 400000, 460000, 520000, 580000, 640000, 700000};

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Relatorio_Sorts.pdf"));
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            document.add(new Paragraph("Relatorio Comparativo de Algoritmos de Ordenacao", titleFont));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Analise Teorica:", titleFont));
            document.add(new Paragraph(" "));
            
            document.add(new Paragraph("Bubble Sort:"));
            document.add(new Paragraph(" - Complexidade de Tempo: O(n2) para o melhor, medio e pior caso."));
            document.add(new Paragraph(" - Complexidade de Espaco: O(1)."));
            document.add(new Paragraph(" - Notas: Extremamente ineficiente para grandes volumes de dados."));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Merge Sort:"));
            document.add(new Paragraph(" - Complexidade de Tempo: O(n log n) em todos os casos."));
            document.add(new Paragraph(" - Complexidade de Espaco: O(n)."));
            document.add(new Paragraph(" - Notas: Eficiente e estavel, mas consome mais memoria."));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Quick Sort:"));
            document.add(new Paragraph(" - Complexidade de Tempo: O(n log n) no caso medio. O(n2) no pior caso."));
            document.add(new Paragraph(" - Complexidade de Espaco: O(log n) a O(n)."));
            document.add(new Paragraph(" - Notas: Um dos mais rapidos na pratica, mas sensivel a entrada no pior caso."));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Heap Sort:"));
            document.add(new Paragraph(" - Complexidade de Tempo: O(n log n) em todos os casos."));
            document.add(new Paragraph(" - Complexidade de Espaco: O(1)."));
            document.add(new Paragraph(" - Notas: Eficiente, estavel e usa pouca memoria."));
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Insertion Sort:"));
            document.add(new Paragraph(" - Complexidade de Tempo: O(n) no melhor caso. O(n2) no caso medio e pior caso."));
            document.add(new Paragraph(" - Complexidade de Espaco: O(1)."));
            document.add(new Paragraph(" - Notas: Extremamente rapido para dados quase ordenados."));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Selection Sort:"));
            document.add(new Paragraph(" - Complexidade de Tempo: O(n2) em todos os casos."));
            document.add(new Paragraph(" - Complexidade de Espaco: O(1)."));
            document.add(new Paragraph(" - Notas: Sempre realiza o mesmo numero de comparacoes."));
            
            document.add(new Paragraph(" "));

            String[] tiposEntrada = {
                "Crescente com repeticao", "Decrescente com repeticao", "Aleatorio com repeticao",
                "Crescente sem repeticao", "Decrescente sem repeticao", "Aleatorio sem repeticao"
            };
            
            for (String tipo : tiposEntrada) {
                XYSeries bubbleSeries = new XYSeries("BubbleSort");
                XYSeries mergeSeries = new XYSeries("MergeSort");
                XYSeries quickSeries = new XYSeries("QuickSort");
                XYSeries heapSeries = new XYSeries("HeapSort");
                XYSeries insertionSeries = new XYSeries("InsertionSort");
                XYSeries selectSeries = new XYSeries("SelectionSort");
                
                for (int n : sizes) {
                    Map<String, int[]> todasAsEntradas = Entradas.gerarTodasEntradas(n);
                    int[] arrOriginal = todasAsEntradas.get(tipo);

                    int[] arrBubble = arrOriginal.clone();
                    long startBubble = System.nanoTime();
                    BubbleSort.bubbleSort(arrBubble);
                    long endBubble = System.nanoTime();
                    bubbleSeries.add(n, (endBubble - startBubble) / 1_000_000.0);

                    int[] arrMerge = arrOriginal.clone();
                    long startMerge = System.nanoTime();
                    MergeSort.mergeSort(arrMerge);
                    long endMerge = System.nanoTime();
                    mergeSeries.add(n, (endMerge - startMerge) / 1_000_000.0);

                    int[] arrQuick = arrOriginal.clone();
                    long startQuick = System.nanoTime();
                    QuickSort.quickSort(arrQuick, 0, arrQuick.length - 1);
                    long endQuick = System.nanoTime();
                    quickSeries.add(n, (endQuick - startQuick) / 1_000_000.0);

                    int[] arrHeap = arrOriginal.clone();
                    long startHeap = System.nanoTime();
                    HeapSort.heapSort(arrHeap);
                    long endHeap = System.nanoTime();
                    heapSeries.add(n, (endHeap - startHeap) / 1_000_000.0);

                    int[] arrInsertion = arrOriginal.clone();
                    long startInsertion = System.nanoTime();
                    InsertionSort.insertionSort(arrInsertion);
                    long endInsertion = System.nanoTime();
                    insertionSeries.add(n, (endInsertion - startInsertion) / 1_000_000.0);

                    int[] arrSelect = arrOriginal.clone();
                    long startSelect = System.nanoTime();
                    SelectSort.selectSort(arrSelect);
                    long endSelect = System.nanoTime();
                    selectSeries.add(n, (endSelect - startSelect) / 1_000_000.0);
                }

                XYSeriesCollection dataset = new XYSeriesCollection();
                dataset.addSeries(bubbleSeries);
                dataset.addSeries(mergeSeries);
                dataset.addSeries(quickSeries);
                dataset.addSeries(heapSeries);
                dataset.addSeries(insertionSeries);
                dataset.addSeries(selectSeries);

                JFreeChart chart = ChartFactory.createXYLineChart(
                    "Tempo de Execucao - " + tipo,
                    "Tamanho do Vetor (n)",
                    "Tempo (ms)",
                    dataset
                );

                ByteArrayOutputStream bas = new ByteArrayOutputStream();
                ChartUtils.writeChartAsPNG(bas, chart, 500, 300);
                Image chartImage = Image.getInstance(bas.toByteArray());
                
                document.add(new Paragraph(" "));
                document.add(new Paragraph("Grafico de Comparacao: " + tipo, new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD)));
                document.add(new Paragraph(" "));
                document.add(chartImage);
            }

            document.close();
            System.out.println("Relatorio PDF criado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}