package com.mycompany.var26lab2;


import java.util.Random;

public class Var26lab2 {

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
    
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }
    
    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
      
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }
     
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
    
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }
    
    
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size * 10); 
        }
        
        return arr;
    }
    
    public static int[] generatePartiallySortedArray(int size) {
        int[] arr = generateRandomArray(size);
        quickSort(arr); 
        
        Random random = new Random();
        int elementsToShuffle = size / 4;
        
        for (int i = 0; i < elementsToShuffle; i++) {
            int index1 = random.nextInt(size);
            int index2 = random.nextInt(size);
            
            int temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;
        }
        
        return arr;
    }
    
    public static int[] generateReverseSortedArray(int size) {
        int[] arr = generateRandomArray(size);
        quickSort(arr);
        
        for (int i = 0; i < size / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[size - 1 - i];
            arr[size - 1 - i] = temp;
        }
        
        return arr;
    }
    
    public static int[] generateArrayWithDuplicates(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        
        int uniqueValuesCount = Math.max(1, size / 10);
        int[] uniqueValues = new int[uniqueValuesCount];
        
        for (int i = 0; i < uniqueValuesCount; i++) {
            uniqueValues[i] = random.nextInt(size * 10);
        }
        
        for (int i = 0; i < size; i++) {
            arr[i] = uniqueValues[random.nextInt(uniqueValuesCount)];
        }
        
        return arr;
    }
    
    public static int[] generateAlmostSortedArray(int size) {
        int[] arr = generateRandomArray(size);
        quickSort(arr); 
        
        Random random = new Random();
        int elementsToShuffle = size / 10;
        
        for (int i = 0; i < elementsToShuffle; i++) {
            int index1 = random.nextInt(size);
            int index2 = random.nextInt(size);
            
            int temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;
        }
        
        return arr;
    }
    
    public static int[] copyArray(int[] original) {
        int[] copy = new int[original.length];
        System.arraycopy(original, 0, copy, 0, original.length);
        return copy;
    }
    
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    public static long testSortingAlgorithm(String algorithmName, int[] testArray, int runs) {
        long totalTime = 0;
        long bestTime = Long.MAX_VALUE;
        
        System.out.println("Testing " + algorithmName + " on array of size " + testArray.length);
        
        for (int i = 0; i < runs; i++) {
            int[] arrayCopy = copyArray(testArray);
            
            long startTime = System.nanoTime();
            
            switch (algorithmName) {
                case "Selection Sort":
                    selectionSort(arrayCopy);
                    break;
                case "Merge Sort":
                    mergeSort(arrayCopy);
                    break;
                case "QuickSort":
                    quickSort(arrayCopy);
                    break;
            }
            
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            
            if (!isSorted(arrayCopy)) {
                System.out.println("ERROR: Array not sorted correctly!");
                return -1;
            }
            
            totalTime += duration;
            if (duration < bestTime) {
                bestTime = duration;
            }
            
            System.out.println("  Run " + (i + 1) + ": " + duration / 1000000.0 + " ms");
        }
        
        double averageTimeMs = (totalTime / runs) / 1000000.0;
        double bestTimeMs = bestTime / 1000000.0;
        
        System.out.println("  Best time: " + String.format("%.3f", bestTimeMs) + " ms");
        System.out.println("  Average time: " + String.format("%.3f", averageTimeMs) + " ms");
        System.out.println();
        
        return bestTime;
    }
    
    public static void runAllTests() {
        int[] sizes = {500, 5000, 20000}; 
        
        String[] arrayTypes = {
            "Random Array",
            "Partially Sorted (75%)",
            "Reverse Sorted",
            "Many Duplicates (10% unique)",
            "Almost Sorted (90%)"
        };
        
        String[] algorithms = {"Selection Sort", "Merge Sort", "QuickSort"};
        int runs = 5;
        
        for (int size : sizes) {
            System.out.println("=" .repeat(80));
            System.out.println("ARRAY SIZE: " + size);
            System.out.println("=" .repeat(80));
            
            for (String arrayType : arrayTypes) {
                System.out.println("\n" + "─" .repeat(50));
                System.out.println("ARRAY TYPE: " + arrayType);
                System.out.println("─" .repeat(50));
                
                int[] testArray;
                switch (arrayType) {
                    case "Random Array":
                        testArray = generateRandomArray(size);
                        break;
                    case "Partially Sorted (75%)":
                        testArray = generatePartiallySortedArray(size);
                        break;
                    case "Reverse Sorted":
                        testArray = generateReverseSortedArray(size);
                        break;
                    case "Many Duplicates (10% unique)":
                        testArray = generateArrayWithDuplicates(size);
                        break;
                    case "Almost Sorted (90%)":
                        testArray = generateAlmostSortedArray(size);
                        break;
                    default:
                        testArray = generateRandomArray(size);
                        break;
                }
                
                for (String algorithm : algorithms) {
                    testSortingAlgorithm(algorithm, testArray, runs);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("SORTING ALGORITHMS COMPARISON");
        System.out.println("Algorithms: Selection Sort, Merge Sort, QuickSort");
        System.out.println("Testing on various array types and sizes");
        System.out.println();
        
        runAllTests();
        
        System.out.println("=" .repeat(80));
        System.out.println("TESTING COMPLETED");
        System.out.println("=" .repeat(80));
    }
}
