package sorting;

import bridges.base.LineChart;
import bridges.benchmark.SortingBenchmark;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

import credentials.*;

public class sortingbenchmark {

  // NOTE: The following sorting methods use the Java "Consumer"
  // model. For our purposes, it's sufficient to know that "arr"
  // is an input array of ints to be sorted, and the method should
  // ensure that when it finishes execution, "arr" is sorted in 
  // increasing order, with its minimum element at index 0.

  public static Consumer <int[]> selectionSort = arr -> {
    //TODO: Delete the following line and implement 
    // selection sort on the input array instead.
    Arrays.sort(arr);

  };

  public static Consumer <int[]> mergeSort = arr -> {
    //TODO: Delete the following line and implement 
    // merge sort on the input array instead.
    Arrays.sort(arr);

  };

  public static Consumer <int[]> heapSort = arr -> {
    //TODO: Delete the following line and implement 
    // heap sort on the input array instead.
    Arrays.sort(arr);

  };

  public static Consumer <int[]> radixSort = arr -> {
    //TODO: Delete the following line and implement 
    // radix sort on the input array instead.
    Arrays.sort(arr);

  };

  /** Runs a built-in Java sort.
    * @param arr An array of ints to be sorted.
    * @post arr is sorted.
  */
  public static Consumer <int[]> javaSort = arr -> {
    // DO NOT MODIFY this code.  It is included for benchmarking.
    Arrays.sort(arr);
  };


  public static void main(String[] args) throws IOException, RateLimitException, InterruptedException {

    Bridges bridges = new Bridges(Assignment.ASSIGNMENT_ID, User.USERNAME, User.APIKEY);

    bridges.setTitle("M6 Lab: Sorting Benchmark");
    bridges.setDescription("Sorting Benchmark Test");

    LineChart plot = new LineChart();
    plot.setTitle("Sort Runtime");
    SortingBenchmark bench = new SortingBenchmark(plot);
    bench.linearRange(100, 20000, 50);
    bench.setTimeCap(1000 * 1); // 1 seconds
    bench.run("Built-in Java sort", javaSort);

    // TODO: uncomment the lines below once sorts are implemented to 
    // generate their running time graphs
    // bench.run("Selection sort", selectionSort);
    // bench.run("Merge sort", mergeSort);
    // bench.run("Heap sort", heapSort);
    // bench.run("Radix sort", radixSort);

    bridges.setDataStructure(plot);
    bridges.visualize();

    LineChart plot2 = new LineChart();
    plot2.setTitle("Sort Runtime");
    SortingBenchmark bench2 = new SortingBenchmark(plot2);
    bench2.geometricRange(100, 10000000, 1.5);
    bench2.setTimeCap(1000 * 1); // 1 second
    bench2.run("Built-in Java sort", javaSort);

    // TODO: uncomment the lines below once sorts are implemented
    // generate their running time graphs
    // bench2.run("Selection sort", selectionSort);
    // bench2.run("Merge sort", mergeSort);
    // bench2.run("Heap sort", heapSort);
    // bench2.run("Radix sort", radixSort);

    bridges.setDataStructure(plot2);
    bridges.visualize();
  }

}
