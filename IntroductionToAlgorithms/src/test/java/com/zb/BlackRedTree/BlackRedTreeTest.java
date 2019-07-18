package com.zb.BlackRedTree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlackRedTreeTest {
    BlackRedTree<BlackRedTreeNode<Integer>, Integer> blackRedTree;

    @Before
    public void init() {
        blackRedTree = new BlackRedTree<>();
//        blackRedTree.insert(null);
//        blackRedTree.insert(null);
//        blackRedTree.insert(null);
//        blackRedTree.insert(null);
//        blackRedTree.insert(null);
    }

    @Test
    public void getRoot() {
    }

    @Test
    public void height() {
    }

    @Test
    public void insert() {
        blackRedTree.insert(new BlackRedTreeNode<>(1));
        blackRedTree.insert(new BlackRedTreeNode<>(2));
    }

    @Test
    public void preOrder() {
    }
}