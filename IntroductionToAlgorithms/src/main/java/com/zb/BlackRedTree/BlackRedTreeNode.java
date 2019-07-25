package com.zb.BlackRedTree;

public class BlackRedTreeNode<N extends Comparable<N>> {
    public final N n;
    public BlackRedTreeNode<N> left;
    public BlackRedTreeNode<N> right;
    public BlackRedTreeNode<N> parent;
    public Color color = Color.BLACK;

    public BlackRedTreeNode(N n) {
        this.n = n;
    }

    public N getDate() {
        return n;
    }

    enum Color{
        RED,
        BLACK
    }
}
