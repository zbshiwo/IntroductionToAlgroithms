package com.zb.BlackRedTree;

import com.zb.base.Issue;
import java.util.function.Consumer;
import static com.zb.BlackRedTree.BlackRedTreeNode.Color;

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
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
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
                current = current.left;
            } else if (compareResult < 0) {
                current = current.right;
            } else {
                //TODO throw another exception
                throw new RuntimeException("not equals");
            }
        }
        newNode.parent = pCurrent;
        if (pCurrent.getDate().compareTo(newNode.getDate()) > 0) {
            pCurrent.left = newNode;
        } else {
            pCurrent.right = newNode;
        }
        newNode.color = Color.RED;

        //Refer to the binary search tree insert, finally fix the BlackRedTree
        balanceInsertion(newNode);
    }

    void balanceInsertion(BlackRedTreeNode<T> x) {
        // if the node p is the root node or if the parent of p is the root node
        if (x.parent == null || x.parent.parent == null) {
            return;
        }

        // if the parent of newNode is BLACK, inserting the red node does not destroy the red-black tree, do nothing
        while (x.parent.color == Color.RED) {
            // the grandparent of newNode must be black
            BlackRedTreeNode<T> xp = x.parent, uncle, xpp = xp.parent;
            if (xp == xpp.left) {
                // the first case: the uncle node of the x is red and the node xp is the left subtree
                //     x(red)           xpp(black)                               xpp(red)
                //                       /     \                                 /      \
                //                  xp(red)  uncle(red)           =>       xp(black) uncle(black)
                //                    /   \                                 /     \
                //                  x  or  x                               x  or   x
                if ((uncle = xpp.right) != null && uncle.color == Color.RED) {
                    xp.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    xpp.color = Color.RED;
                    xp = xpp;
                } else {
                    // the second case: the uncle node of the x is black, the node xp is the left subtree and the node x is the right subtree
                    //          xpp(black)                                       xpp(black)
                    //          /        \                                       /         \
                    //      xp(red)    uncle(black)                           x(red)    uncle(black)
                    //      /     \       /      \     leftRotate(xp)        /      \     /      \            => case 3
                    //     1     x(red)  4        5          =>           xp(red)    3   4        5
                    //           /    \                                   /     \
                    //          2      3                                 1       2
                    if (x == xp.right) {
                        x = leftRotate(xp);
                    }
                }
            } else {
                // the fourth case: the uncle node of the x is red and the node xp is the right subtree
                //    x(red)            xpp(black)
                //                       /     \
                //                 uncle(red)  xp(red)
                //                              /   \
                //                             x  or x
                if ((uncle = xpp.right) != null && uncle.color == Color.RED) {
                    xp.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    xpp.color = Color.RED;
                    xp = xpp;
                }
            }
        }
    }

    /**
     * left rotation according to the node p
     *        p                                        r
     *      /   \            left rotate             /   \
     *     a     r               =>                 p     b
     *         /  \                               /  \
     *        rl   b                             a    rl
     * @param p
     */
    private BlackRedTreeNode<T> leftRotate(BlackRedTreeNode<T> p) {
        BlackRedTreeNode<T> r, pp, rl;
        if (p != null && (r = p.right) != null) {
            if ((rl = p.right = r.left) != null) {                    // p.right = r.left;
                rl.parent = p;                                        // rl.parent = p;
            }
            // if the p node is the root node
            if ((pp = r.parent = p.parent) == null) {                 // r.parent = p.parent
                setRoot(r);
            }
            else if (pp.left == p) {
                pp.left = r;                                          // pp.left == r; | pp.right = r;
            } else {
                pp.right = r;
            }
            r.left = p;                                                // r.left = p;
            p.parent = r;                                              // p.parent = r;
        }
        return p;
    }

    /**
     * right rotation according to the node p
     *         p                                       l
     *       /  \             right rotate            / \
     *      l    a                 =>                b   p
     *    /  \                                          / \
     *   b    lr                                      lr   a
     * @param p
     */
    private BlackRedTreeNode<T> rightRotate(BlackRedTreeNode<T> p) {
        BlackRedTreeNode<T> l, pp, lr;
        if (p != null && (l = p.left) != null) {
            if ((lr = p.left = l.right) != null) {                           // p.left = l.right;
                lr.parent = p;                                               // lr.parent = p;
            }
            // if the node p is the root node
            if ((pp = l.parent = p.parent) == null) {                        // l.parent = p.parent;
                setRoot(l);
            } else if (pp.left == p) {                                       // pp.left = l | pp.right = l;
                pp.left = l;
            } else {
                pp.right = l;
            }
            l.right = p;                                                    // l.right = p;
            p.parent = l;                                                   // p.parent = l;
        }
        return p;
    }

    public void preOrder(Consumer<? super BlackRedTreeNode<? extends Comparable<T>>> consumer) {
        preOrder(getRoot(), consumer);
    }

    private void preOrder(BlackRedTreeNode<? extends Comparable<T>> root, Consumer<? super BlackRedTreeNode<? extends Comparable<T>>> consumer) {
        if (root != null) {
            consumer.accept(root);
            preOrder(root.left, consumer);
            preOrder(root.right, consumer);
        }
    }

    public void inOrder(Consumer<? super BlackRedTreeNode<? extends Comparable<T>>> consumer) {
        inOrder(getRoot(), consumer);
    }

    private void inOrder(BlackRedTreeNode<? extends Comparable<T>> root, Consumer<? super BlackRedTreeNode<? extends Comparable<T>>> consumer) {
        if (root != null) {
            inOrder(root.left, consumer);
            consumer.accept(root);
            inOrder(root.right, consumer);
        }
    }

    public void postOrder(Consumer<? super BlackRedTreeNode<? extends Comparable<T>>> consumer) {
        postOrder(getRoot(), consumer);
    }

    private void postOrder(BlackRedTreeNode<? extends Comparable<T>> root, Consumer<? super BlackRedTreeNode<? extends Comparable<T>>> consumer) {
        if (root != null) {
            postOrder(root.left, consumer);
            postOrder(root.right, consumer);
            consumer.accept(root);
        }
    }


}
