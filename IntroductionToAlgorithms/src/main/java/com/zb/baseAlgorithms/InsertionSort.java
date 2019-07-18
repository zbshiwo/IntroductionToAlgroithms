package com.zb.baseAlgorithms;

public class InsertionSort {

    /**
     * 插入排序（正序）
     * @param array
     * @return
     */
    public int[] insertionSortAsc(int[] array) {
        for (int j = 1; j < array.length; j++) {
            int temp = array[j];
            int i = j - 1;
            while (i >= 0 && array[i] > temp) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = temp;
        }
        return array;
    }

    public int[] insertionSortDesc(int[] array) {
        for (int j = 1; j < array.length; j++) {
            int temp = array[j];
            int i = j - 1;
            while (i >= 0 && array[i] < temp) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = temp;
        }
        return array;
    }
}
