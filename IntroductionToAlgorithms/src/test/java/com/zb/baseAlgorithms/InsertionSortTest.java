package com.zb.baseAlgorithms;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InsertionSortTest {
    private InsertionSort insertionSort;
    private int[] array;
    private int[] ascArray;
    private int[] descArray;

    @Before
    public void initInsertionSort() {
        insertionSort = new InsertionSort();
        array = new int[] {1, 2, 4, 1, 2, 8, 7, 6, 5, 2, 3, 4, 7, 6, 2, 7, 7};
        ascArray = new int[] {1, 1, 2, 2, 2, 2, 3, 4, 4, 5, 6, 6, 7, 7, 7, 7, 8};
        descArray = new int[] {8, 7, 7, 7, 7, 6, 6, 5, 4, 4, 3, 2, 2, 2, 2, 1, 1};
    }

    @Test
    public void testInsertionSortAsc() {
        assertArrayEquals("failure - arrays not same", ascArray, insertionSort.insertionSortAsc(array));
    }

    @Test
    public void testInsertionSortDesc() {
        assertArrayEquals(descArray, insertionSort.insertionSortDesc(array));
    }

}