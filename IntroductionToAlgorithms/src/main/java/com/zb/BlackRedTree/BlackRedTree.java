package com.zb.BlackRedTree;

import com.zb.base.Issue;
import java.util.function.Consumer;

/**
 * 红黑树
 * 1、每个节点是红色的，或是黑色的
 * 2、根节点是黑色的
 * 3、每个叶子结点是黑色的
 * 4、如果一个节点是红色的，那么它的两个子节点都是黑色的
 * 5、对每个节点，从该节点到其所有后代叶节点的简单路径上，均包含相同数目的黑色节点
 * @param <T>
 */
public class BlackRedTree<T extends Comparable<T>> {
    private BlackRedTreeNode<T> root;

    public BlackRedTreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(BlackRedTreeNode<T> root) {
        this.root = root;
    }

    public int height() {
        return height(root);
    }

    private int height(BlackRedTreeNode<? extends Comparable<T>> root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.getLeft());
        int rightHeight = height(root.getRight());
        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }

    @Issue("https://www.cnblogs.com/xuqiang/archive/2011/05/16/2047001.html")
    public void insert(BlackRedTreeNode<T> newNode) {
        BlackRedTreeNode<T> root = getRoot();
        if (root == null) {
            setRoot(newNode);
            return;
        }

        BlackRedTreeNode<T> current, pCurrent = null;
        current = root;
        while (current != null) {
            pCurrent = current;
            int compareResult = current.getDate().compareTo(newNode.getDate());
            if (compareResult > 0) {
                current = current.getLeft();
            } else if (compareResult < 0) {
                current = current.getRight();
            } else {
                //TODO throw another exception
                throw new RuntimeException();
            }
        }
        newNode.setParent(pCurrent);
        if (pCurrent.getDate().compareTo(newNode.getDate()) > 0) {
            pCurrent.setLeft(newNode);
        } else {
            pCurrent.setRight(newNode);
        }
        newNode.setColor(BlackRedTreeNode.Color.RED);

        //Refer to the binary search tree insert, finally fix the BlackRedTree
        balanceInsertion(newNode);
    }

    void balanceInsertion(BlackRedTreeNode<T> newNode) {
        // if the parent of newNode is BLACK, inserting the red node does not destroy the red-black tree, do nothing
        while (newNode.getParent().getColor() == BlackRedTreeNode.Color.RED) {
            // the first case:
            // the second case:
            // the last case:
        }
    }

    private void leftRotate(T root) {

    }

    public void preOrder(Consumer<? super BlackRedTreeNode<? extends Comparable<T>>> consumer) {
        preOrder(getRoot(), consumer);
    }

    private void preOrder(BlackRedTreeNode<? extends Comparable<T>> root, Consumer<? super BlackRedTreeNode<? extends Comparable<T>>> consumer) {
        if (root != null) {
            consumer.accept(root);
            preOrder(root.getLeft(), consumer);
            preOrder(root.getRight(), consumer);
        }
    }

    public void inOrder(Consumer<? super BlackRedTreeNode<? extends Comparable<T>>> consumer) {
        inOrder(getRoot(), consumer);
    }

    private void inOrder(BlackRedTreeNode<? extends Comparable<T>> root, Consumer<? super BlackRedTreeNode<? extends Comparable<T>>> consumer) {
        if (root != null) {
            inOrder(root.getLeft(), consumer);
            consumer.accept(root);
            inOrder(root.getRight(), consumer);
        }
    }

    public void postOrder(Consumer<? super BlackRedTreeNode<? extends Comparable<T>>> consumer) {
        postOrder(getRoot(), consumer);
    }

    private void postOrder(BlackRedTreeNode<? extends Comparable<T>> root, Consumer<? super BlackRedTreeNode<? extends Comparable<T>>> consumer) {
        if (root != null) {
            postOrder(root.getLeft(), consumer);
            postOrder(root.getRight(), consumer);
            consumer.accept(root);
        }
    }
}
