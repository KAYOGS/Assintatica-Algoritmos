package algoritmos.assintatica;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;

import algoritmos.assintatica.codigos.*;

import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;

public class Main {
    public static void main(String[] args){
        int[] sizes = {100000, 160000, 220000, 280000, 340000, 400000, 700000};
        Random rand = new Random();
        Runtime runtime = Runtime.getRuntime();

        XYSeries bubbleSeries = new XYSeries("BubbleSort");
        XYSeries mergeSeries = new XYSeries("MergeSort");
        XYSeries quickSeries = new XYSeries("QuickSort");
        XYSeries heapSeries = new XYSeries("HeapSort");
        XYSeries insertionSeries = new XYSeries("InsertionSort");
        XYSeries selectSeries = new XYSeries("SelectionSort");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Relatorio_Sorts.pdf"));
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            document.add(new Paragraph("Relatório Comparativo Sorts", titleFont));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Este relatório apresenta uma análise teórica e empírica dos algoritmos BubbleSort, MergeSort, QuickSort, HeapSort, InsertionSort e SelectSort."));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Análise Teórica:", titleFont));
            document.add(new Paragraph(" - BubbleSort: O(n^2) no pior caso, O(1) memória extra (in-place)."));
            document.add(new Paragraph(" - MergeSort: O(n log n) no pior caso, O(n) memória extra."));
            document.add(new Paragraph(" - QuickSort: O(n^2) no pior caso, O(n) memória extra."));
            document.add(new Paragraph(" - HeapSort: O(n log n) no pior caso, O(1) memória extra."));
            document.add(new Paragraph(" - InsertionSort: O(n^2) no pior caso, O(1) memória extra."));
            document.add(new Paragraph(" - SelectSort: O(n^2) no pior caso, O(1) memória extra."));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(13);
            table.addCell("n");
            table.addCell("BubbleSort Tempo (ms)");
            table.addCell("BubbleSort Memória (bytes)");
            table.addCell("MergeSort Tempo (ms)");
            table.addCell("MergeSort Memória (bytes)");
            table.addCell("QuickSort Tempo (ms)");
            table.addCell("QuickSort Memória (bytes)");
            table.addCell("HeapSort Tempo (ms)");
            table.addCell("HeapSort Memória (bytes)");
            table.addCell("InsertionSort Tempo (ms)");
            table.addCell("InsertionSort Memória (bytes)");
            table.addCell("SelectSort Tempo (ms)");
            table.addCell("SelectSort Memória (bytes)");
            
            for (int n : sizes) {
                int[] arrOriginal = rand.ints(n, 0, n).toArray();
                
                runtime.gc();
                int[] arrBubble = arrOriginal.clone();
                long startBubble = System.nanoTime();
                BubbleSort.bubbleSort(arrBubble);
                long endBubble = System.nanoTime();
                long bubbleTime = (endBubble - startBubble) / 1_000_000;
                bubbleSeries.add(n, bubbleTime);

                runtime.gc();
                int[] arrMerge = arrOriginal.clone();
                long startMerge = System.nanoTime();
                MergeSort.mergeSort(arrMerge);
                long endMerge = System.nanoTime();
                long mergeTime = (endMerge - startMerge) / 1_000_000;
                mergeSeries.add(n, mergeTime);

                runtime.gc();
                int[] arrQuick = arrOriginal.clone();
                long startQuick = System.nanoTime();
                QuickSort.quickSort(arrQuick, 0, arrQuick.length - 1);
                long endQuick = System.nanoTime();
                long quickTime = (endQuick - startQuick) / 1_000_000;
                quickSeries.add(n, quickTime);
                
                runtime.gc();
                int[] arrHeap = arrOriginal.clone();
                long startHeap = System.nanoTime();
                HeapSort.heapSort(arrHeap);
                long endHeap = System.nanoTime();
                long heapTime = (endHeap - startHeap) / 1_000_000;
                heapSeries.add(n, heapTime);

                runtime.gc();
                int[] arrInsertion = arrOriginal.clone();
                long startInsertion = System.nanoTime();
                InsertionSort.insertionSort(arrInsertion);
                long endInsertion = System.nanoTime();
                long insertionTime = (endInsertion - startInsertion) / 1_000_000;
                insertionSeries.add(n, insertionTime);

                runtime.gc();
                int[] arrSelect = arrOriginal.clone();
                long startSelect = System.nanoTime();
                SelectSort.selectSort(arrSelect);
                long endSelect = System.nanoTime();
                long selectTime = (endSelect - startSelect) / 1_000_000;
                selectSeries.add(n, selectTime);

                table.addCell(String.valueOf(n));
                table.addCell(String.valueOf(bubbleTime));
                table.addCell(String.valueOf(0));
                table.addCell(String.valueOf(mergeTime));
                table.addCell(String.valueOf(0));
                table.addCell(String.valueOf(quickTime));
                table.addCell(String.valueOf(0));
                table.addCell(String.valueOf(heapTime));
                table.addCell(String.valueOf(0));
                table.addCell(String.valueOf(insertionTime));
                table.addCell(String.valueOf(0));
                table.addCell(String.valueOf(selectTime));
                table.addCell(String.valueOf(0));
            }
            document.add(table);

            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(bubbleSeries);
            dataset.addSeries(mergeSeries);
            dataset.addSeries(quickSeries);
            dataset.addSeries(heapSeries);
            dataset.addSeries(insertionSeries);
            dataset.addSeries(selectSeries);

            JFreeChart chart = ChartFactory.createXYLineChart(
                "Comparativo de Algoritmos de Ordenação",
                "Tamanho do Vetor (n)",
                "Tempo de Execução (ms)",
                dataset
            );

            ByteArrayOutputStream bas = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(bas, chart, 600, 400);
            Image chartImage = Image.getInstance(bas.toByteArray());
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Gráfico de Comparação de Tempo de Execução:", titleFont));
            document.add(new Paragraph(" "));
            document.add(chartImage);

            document.close();
            System.out.println("Relatório PDF criado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}