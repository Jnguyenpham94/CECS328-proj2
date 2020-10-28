
/**
 * Main class
 */
//Jonathan Nguyen-Pham 016297682
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.Arrays;

public class Main {
    final static int N_VALUES = 10000;
    final static int C = 10; // number of elements to use insertion sort on

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int randomArray[] = new int[N_VALUES]; // random values
        int sorted[] = IntStream.rangeClosed(1, N_VALUES).toArray(); // sorted values 1 - 100,000
        int sortedReverse[] = new int[N_VALUES]; // sorted values 100,000 - 1
        int j = 0;
        for (int i = sorted.length - 1; i >= 0; i--) {// fills sortedReverse array with sorted arrays values in reverse
            sortedReverse[j] = sorted[i];
            j++;
        }

        // limits the random generator from 1 to 100,000
        int lower = 1;
        int upper = N_VALUES;
        int i = 0;
        while (i < N_VALUES) {
            randomArray[i] = random.nextInt(upper - lower) + lower;
            i++;
        }
        // int array[] = { 7, 10, 2, 1, 3, 5, 5, 11, 0, 20, 2, 8 }; test array
        int identical[] = new int[N_VALUES];
        for (int z : identical) {
            identical[z] = 1;
        }
        long startTime;
        long stopTime;
        int choice;
        do {
            mainMenu();
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Quitting program");
                    input.close();
                    System.exit(0);
                    break;
                case 2:
                    int dup1[] = sorted.clone();
                    int dup2[] = sorted.clone();
                    int dup3[] = sorted.clone();
                    startTime = System.nanoTime();
                    insertionSort(dup1);
                    stopTime = System.nanoTime();
                    System.out.println("Elapsed time: " + (stopTime - startTime) + " ns");
                    System.out.println("Array ordered: " + isOrdered(dup1));
                    startTime = System.nanoTime();
                    quickSort(dup2, 0, dup2.length - 1);
                    stopTime = System.nanoTime();
                    System.out.println("Elapsed time: " + (stopTime - startTime) + " ns");
                    System.out.println("Array ordered: " + isOrdered(dup2));
                    quickSort2(dup3, 0, dup3.length - 1);
                    stopTime = System.nanoTime();
                    System.out.println("Elapsed time: " + (stopTime - startTime) + " ns");
                    System.out.println("Array ordered: " + isOrdered(dup3));
                    break;
                case 3:
                    int dup4[] = randomArray.clone();
                    int dup5[] = randomArray.clone();
                    int dup6[] = randomArray.clone();
                    startTime = System.nanoTime();
                    insertionSort(dup4);
                    stopTime = System.nanoTime();
                    System.out.println("Elapsed time: " + (stopTime - startTime) + " ns");
                    System.out.println("Array ordered: " + isOrdered(dup4));
                    startTime = System.nanoTime();
                    quickSort(dup5, 0, dup5.length - 1);
                    stopTime = System.nanoTime();
                    System.out.println("Elapsed time: " + (stopTime - startTime) + " ns");
                    System.out.println("Array ordered: " + isOrdered(dup5));
                    quickSort2(dup6, 0, dup6.length - 1);
                    stopTime = System.nanoTime();
                    System.out.println("Elapsed time: " + (stopTime - startTime) + " ns");
                    System.out.println("Array ordered: " + isOrdered(dup6));
                    break;
                case 4:
                    int dup7[] = sortedReverse.clone();
                    int dup8[] = sortedReverse.clone();
                    int dup9[] = sortedReverse.clone();
                    startTime = System.nanoTime();
                    insertionSort(dup7);
                    stopTime = System.nanoTime();
                    System.out.println("Elapsed time: " + (stopTime - startTime) + " ns");
                    System.out.println("Array ordered: " + isOrdered(dup7));
                    startTime = System.nanoTime();
                    quickSort(dup8, 0, dup8.length - 1);
                    stopTime = System.nanoTime();
                    System.out.println("Elapsed time: " + (stopTime - startTime) + " ns");
                    System.out.println("Array ordered: " + isOrdered(dup8));
                    quickSort2(dup9, 0, dup9.length - 1);
                    stopTime = System.nanoTime();
                    System.out.println("Elapsed time: " + (stopTime - startTime) + " ns");
                    System.out.println("Array ordered: " + isOrdered(dup9));
                    break;
                case 5:
                    int dup10[] = identical.clone();
                    int dup11[] = identical.clone();
                    int dup12[] = identical.clone();
                    startTime = System.nanoTime();
                    insertionSort(dup10);
                    stopTime = System.nanoTime();
                    System.out.println("Elapsed time: " + (stopTime - startTime) + " ns");
                    System.out.println("Array ordered: " + isOrdered(dup10));
                    startTime = System.nanoTime();
                    quickSort(dup11, 0, dup11.length - 1);
                    stopTime = System.nanoTime();
                    System.out.println("Elapsed time: " + (stopTime - startTime) + " ns");
                    System.out.println("Array ordered: " + isOrdered(dup11));
                    startTime = System.nanoTime();
                    quickSort2(dup12, 0, dup12.length - 1);
                    stopTime = System.nanoTime();
                    System.out.println("Elapsed time: " + (stopTime - startTime) + " ns");
                    System.out.println("Array ordered: " + isOrdered(dup12));
                    break;
                default:
                    break;
            }
        } while (choice != 1);
    }

    public static void mainMenu() {
        System.out.println("Choose a task: ");
        System.out.println("1: quit program");
        System.out.println("2: sorted- containing the numbers 1 through 10,000 in increasing order.");
        System.out.println("3: random- containing the numbers 1 through 10,000 in increasing order.");
        System.out.println("4: reverse- containing the numbers 1 through 10,000 in increasing order.");
        System.out.println("5: identical- containing the number 1 10,000 times.");
    }

    private static int[] insertionSort(int array[]) {
        int size = array.length;
        int current = 1;
        int temp;
        while (current < size) {
            for (int i = 0; i < current; i++) {
                if (array[current] < array[i]) {
                    temp = array[i];
                    array[i] = array[current];
                    array[current] = temp;
                }
            }
            current++;
        }
        return array;
    }

    private static int[] quickSort(int array[], int left, int right) {
        int size = right - left + 1;
        if (size <= C) {
            return insertionSort(array);
        } else {
            // pivot of left, middle, right
            int middle = (left + right) / 2;
            int pivotIndex = medianof3(array, left, middle, right);

            int partition = partition(array, left, right, pivotIndex);

            quickSort(array, left, partition - 1); // sort left
            quickSort(array, partition + 1, right); // sort right
        }
        return array;
    }

    private static int[] quickSort2(int array[], int left, int right) {
        int size = right - left + 1;
        if (size <= C) {
            return insertionSort(array);
        } else {
            // pivot of left, middle, right
            int middle = (left + right) / 2;
            int pivotIndex = medianof3(array, left, middle, right);

            int partition = partition2(array, left, right, pivotIndex);

            quickSort2(array, left, partition - 1); // sort left
            quickSort2(array, partition + 1, right); // sort right
        }
        return array;
    }

    private static int medianof3(int array[], int left, int middle, int right) {
        int median = 0;
        if (array[left] <= array[middle] && array[middle] <= array[right]) {
            median = middle;
        } else if (array[middle] <= array[left] && array[left] <= array[right]) {
            median = left;
        } else if (array[right] <= array[middle] && array[right] <= array[left]) {
            median = right;
        }
        return partition(array, left, right, median);
    }

    private static int partition(int a[], int left, int right, int pivotIndex) {
        int pivotValue = a[pivotIndex];
        a = swap(a, pivotIndex, right);

        // partition the array, upon finding an element smaller than pivot,
        // swap it to the next position in the "store"
        int store = left;
        for (int i = left; i < right; i++) {
            if (a[i] <= pivotValue) {
                a = swap(a, store, i);
                store++;
            }
        }
        a = swap(a, right, store);
        return store;
    }

    private static int partition2(int a[], int left, int right, int pivotIndex) {
        int pivotValue = a[pivotIndex];
        a = swap(a, pivotIndex, right);

        // partition the array, upon finding an element smaller than pivot,
        // swap it to the next position in the "store"
        int store = left;
        for (int i = left; i < right; i++) {
            if (a[i] < pivotValue || a[i] < pivotValue && i % 2 == 0) {
                a = swap(a, store, i);
                store++;
            }
        }
        a = swap(a, right, store);
        return store;
    }

    private static int[] swap(int a[], int pivotIndex, int right) {
        int temp = a[pivotIndex];
        a[pivotIndex] = a[right];
        a[right] = temp;
        return a;
    }

    private static boolean isOrdered(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

}