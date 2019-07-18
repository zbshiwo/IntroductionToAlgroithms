package com.zb.BlackRedTree;

public class BlackRedTreeNode<N extends Comparable<N>> {
    private final N n;
    private BlackRedTreeNode<N> left;
    private BlackRedTreeNode<N> right;
    private BlackRedTreeNode<N> parent;
    private Color color = Color.BLACK;

    public BlackRedTreeNode(N n) {
        this.n = n;
    }

    public N getDate() {
        return n;
    }

    public BlackRedTreeNode<N> getLeft() {
        return left;
    }

    public void setLeft(BlackRedTreeNode<N> left) {
        this.left = left;
    }

    public BlackRedTreeNode<N> getRight() {
        return right;
    }

    public void setRight(BlackRedTreeNode<N> right) {
        this.right = right;
    }

    public BlackRedTreeNode<N> getParent() {
        return parent;
    }

    public void setParent(BlackRedTreeNode<N> parent) {
        this.parent = parent;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    enum Color{
        RED,
        BLACK
    }
}
