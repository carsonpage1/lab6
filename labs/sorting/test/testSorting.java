package sorting.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

//import com.github.tkutche1.jgrade.gradedtest.GradedTest;

//import org.junit.FixMethodOrder;
import org.junit.Rule;
//import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Random;
import java.util.Arrays;

import sorting.sortingbenchmark;

public class testSorting {

    static final int N = 1000;
    static final int RANGE = Integer.MAX_VALUE;
    static final int TIMEOUT_PER_SORT_SECS = 600;  

    @Rule
    public Timeout globalTimeout = Timeout.seconds(TIMEOUT_PER_SORT_SECS);
	
    @Test
 //   @GradedTest(name="Sorting selectionSortOutputMatches", points=7.0)
    public void testSelectionSort() {
	int[] arr = new int[N];
	Random rand = new Random();
	for (int i=0; i < arr.length; ++i)
	  arr[i] = rand.nextInt(RANGE);

	int[] testArr = arr.clone();
	int[] goldArr = arr.clone();

	Arrays.sort(goldArr);

	sortingbenchmark.selectionSort.accept(testArr);

	for (int i=0; i < N; ++i)  {
	  assertEquals("Selection sort error: value at index " + i + " of sorted test array is not correct. \n\tExpected: " + goldArr[i] + "\n\t...but observed: " + testArr[i] + " instead.\n\tOriginal value at this index: " + arr[i] + "\n", goldArr[i], testArr[i]);
        }

    }

    @Test
 //   @GradedTest(name="Sorting mergeSortOutputMatches", points=7.0)
    public void testMergeSort() {
	int[] arr = new int[N];
	Random rand = new Random();
	for (int i=0; i < arr.length; ++i)
		  arr[i] = rand.nextInt(RANGE);
	
	int[] testArr = arr.clone();
	int[] goldArr = arr.clone();

	Arrays.sort(goldArr);
	
	sortingbenchmark.mergeSort.accept(testArr);

	for (int i=0; i < N; ++i)  {
	  assertEquals("Merge sort error: value at index " + i + " of sorted test array is not correct. \n\tExpected: " + goldArr[i] + "\n\t...but observed: " + testArr[i] + " instead.\n\tOriginal value at this index: " + arr[i] + "\n", goldArr[i], testArr[i]);
        }
    }

    @Test
  //  @GradedTest(name="Sorting heapSortOutputMatches", points=7.0)
    public void testHeapSort() {
	int[] arr = new int[N];
	Random rand = new Random();
	for (int i=0; i < arr.length; ++i)
		  arr[i] = rand.nextInt(RANGE);

	int[] testArr = arr.clone();
	int[] goldArr = arr.clone();

	Arrays.sort(goldArr);
	
	sortingbenchmark.heapSort.accept(testArr);

	for (int i=0; i < N; ++i)  {
	  assertEquals("Heap sort error: value at index " + i + " of sorted test array is not correct. \n\tExpected: " + goldArr[i] + "\n\t...but observed: " + testArr[i] + " instead.\n\tOriginal value at this index: " + arr[i] + "\n", goldArr[i], testArr[i]);
        }
    }

    @Test
//    @GradedTest(name="Sorting radixSortOutputMatches", points=7.0)
    public void testRadixSort() {
	int[] arr = new int[N];
	Random rand = new Random();
	for (int i=0; i < arr.length; ++i)
		  arr[i] = rand.nextInt(RANGE);

	int[] testArr = arr.clone();
	int[] goldArr = arr.clone();

	Arrays.sort(goldArr);
	
	sortingbenchmark.radixSort.accept(testArr);

	for (int i=0; i < N; ++i)  {
	  assertEquals("Radix sort error: value at index " + i + " of sorted test array is not correct. \n\tExpected: " + goldArr[i] + "\n\t...but observed: " + testArr[i] + " instead.\n\tOriginal value at this index: " + arr[i] + "\n", goldArr[i], testArr[i]);
        }
	
    }
}
