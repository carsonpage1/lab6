package sorting;

import bridges.base.LineChart;
import bridges.benchmark.SortingBenchmark;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Arrays;


import credentials.*;
//import heaps.MinHeap.HeapPQEntry;	

public class sortingbenchmark {

  // NOTE: The following sorting methods use the Java "Consumer"
  // model. For our purposes, it's sufficient to know that "arr"
  // is an input array of ints to be sorted, and the method should
  // ensure that when it finishes execution, "arr" is sorted in 
  // non-decreasing order, with its minimum element at index 0.

  /** Runs a selection sort.
    * @param arr An array of ints to be sorted.
    * @post arr is sorted.
  */
	public static Consumer<int[]> selectionSort = arr -> {
	    if (arr.length <= 1) {
	        return;
	    }

	    for (int i = 0; i < arr.length - 1; i++) {
	        int minIndex = i;

	        for (int j = i + 1; j < arr.length; j++) {
	            if (arr[j] < arr[minIndex]) {
	                minIndex = j;
	            }
	        }

	        int temp = arr[i];
	        arr[i] = arr[minIndex];
	        arr[minIndex] = temp;
	    }
	};

  /** Runs a merge sort.
    * @param arr An array of ints to be sorted.
    * @post arr is sorted.
  */
	public static Consumer<int[]> mergeSort = arr -> {
	    if (arr.length <= 1) {
	        return;
	    }

	    int mid = arr.length / 2;
	    int[] leftArr = Arrays.copyOfRange(arr, 0, mid);
	    int[] rightArr = Arrays.copyOfRange(arr, mid, arr.length);

	    sortingbenchmark.mergeSort.accept(leftArr);
	    sortingbenchmark.mergeSort.accept(rightArr);
		 

	    merge(leftArr, rightArr, arr);
	};

	public static void merge(int[] leftArr, int[] rightArr, int[] mainArr) {
	    int i = 0, j = 0, k = 0;

	    while (i < leftArr.length && j < rightArr.length) {
	        if (leftArr[i] <= rightArr[j]) {
	            mainArr[k++] = leftArr[i++];
	        } else {
	            mainArr[k++] = rightArr[j++];
	        }
	    }

	    while (i < leftArr.length) {
	        mainArr[k++] = leftArr[i++];
	    }

	    while (j < rightArr.length) {
	        mainArr[k++] = rightArr[j++];
	    }
	}


  public static void Heapsort(int[] numbers, int numbersSize) {
	    for (int i = numbersSize / 2 - 1; i >= 0; i--) {
	        MaxHeapPercolateDown(i, numbers, numbersSize);
	    }
	    for (int i = numbersSize - 1; i > 0; i--) {
	        int temp = numbers[0];
	        numbers[0] = numbers[i];
	        numbers[i] = temp;
	        MaxHeapPercolateDown(0, numbers, i);
	    }
	}

	public static void MaxHeapPercolateDown(int nodeIndex, int[] heapArr, int arrSize) {
	    int childIndex = 2 * nodeIndex + 1;
	    int value = heapArr[nodeIndex];

	    while (childIndex < arrSize) {
	        if (childIndex + 1 < arrSize && heapArr[childIndex + 1] > heapArr[childIndex]) {
	            childIndex++;
	        }

	        if (value >= heapArr[childIndex]) {
	            break;
	        }

	        heapArr[nodeIndex] = heapArr[childIndex];
	        nodeIndex = childIndex;
	        childIndex = 2 * nodeIndex + 1;
	    }

	    heapArr[nodeIndex] = value;
	}
  /** Runs a heap sort.
    * @param arr An array of ints to be sorted.
    * @post arr is sorted.
  */
  public static Consumer <int[]> heapSort = arr -> {
    //TODO: Delete the following line and implement 
    // heap sort on the input array instead.
	  Heapsort(arr, arr.length);
  };
  
  public static int RadixGetMaxLength(int[] array, int n) {
	    int max = 0;
	    for (int i = 0; i < n; i++) {
	        int temp = Math.abs(array[i]);
	        int digits = (temp == 0) ? 1 : (int) Math.floor(Math.log10(temp) + 1);

	        int digitCount = digits;
	        if (digitCount > max) {
	            max = digitCount;
	        }
	    }
	    return max;
	}
  /** Runs a radix sort.
    * @param arr An array of ints to be sorted.
    * @post arr is sorted.
  */
 
  public static Consumer<int[]> radixSort = arr -> {
	  ArrayList<Integer>[] buckets = new ArrayList[10];
		
		int maxDigits = RadixGetMaxLength(arr, arr.length);
		
		int pow10 = 1;
		for(int i = 0; i < buckets.length; i++) {
			buckets[i] = new ArrayList<>();
		}
		
		for (int digitIndex = 0; digitIndex < maxDigits; digitIndex++) {
			for(int i=0; i < arr.length; i++) {
				int bucketIndex = (Math.abs(arr[i]) / pow10) % 10;
				buckets[bucketIndex].add(arr[i]);
			}
			int arrayIndex = 0;
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < buckets[i].size();j++) {
					
					arr[arrayIndex++] = buckets[i].get(j);
					
				}
				
			}
			pow10 *= 10;
			for (int i = 0; i < 10; i++) {
				buckets[i] = new ArrayList<>();
				}
			}
		
		
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
     bench.run("Selection sort", selectionSort);
     bench.run("Merge sort", mergeSort);
     bench.run("Heap sort", heapSort);
     bench.run("Radix sort", radixSort);

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
     bench2.run("Selection sort", selectionSort);
     bench2.run("Merge sort", mergeSort);
     bench2.run("Heap sort", heapSort);
     bench2.run("Radix sort", radixSort);
=======
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
>>>>>>> branch 'master' of https://github.com/wustl-cse247-fl24/m6-lab-carsonpage1.git

    bridges.setDataStructure(plot2);
    bridges.visualize();
  }

}
