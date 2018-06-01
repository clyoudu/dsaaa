package github.clyoudu.tree;

import github.clyoudu.tree.node.BinaryTreeNode;
import github.clyoudu.tree.node.TreeNode;

import java.util.Collection;
import java.util.Comparator;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/6/1 15:49
 * @Description BinarySearchTree
 */
public class BinarySearchTree<E> extends BinaryTree<E> implements Tree<E> {

    private Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        super((BinaryTreeNode<E>) null);
        this.comparator = comparator;
    }

    @Override
    public TreeNode<E> addChild(Collection<TreeNode<E>> child) {
        if (child != null && !child.isEmpty()) {
            child.forEach(threeNode -> addChild(threeNode.getElement()));
        }
        return null;
    }

    @Override
    public TreeNode<E> addChild(E parent, Collection<TreeNode<E>> child) {
        return addChild(child);
    }

    @Override
    protected TreeNode<E> addChild(TreeNode<E> parentNode, Collection<TreeNode<E>> child) {
        return addChild(child);
    }

    public BinarySearchTree<E> addChild(E element) {
        if (root == null) {
            root = new BinaryTreeNode<>(element);
        } else {
            return addChild(root, element, null);
        }

        return this;
    }

    protected BinarySearchTree<E> addChild(TreeNode<E> node, E element, BinaryTreeNode<E> parent) {
        if (node == null) {
            int compare = comparator.compare(parent.getElement(), element);
            if(compare > 0){
                parent.setLeftChild(new BinaryTreeNode<>(element));
            }else{
                parent.setRightChild(new BinaryTreeNode<>(element));
            }
            return this;
        }
        int compare = comparator.compare(node.getElement(), element);
        BinaryTreeNode<E> binaryTreeNode = (BinaryTreeNode<E>) node;
        if (compare == 0) {//重复
            return this;
        } else if (compare > 0) {
            return addChild(binaryTreeNode.getLeftChild(), element, binaryTreeNode);
        } else {
            return addChild(binaryTreeNode.getRightChild(), element, binaryTreeNode);
        }
    }

    @Override
    protected E min(TreeNode<E> node, Comparator<E> comparator) {
        if (node == null) {
            return null;
        }

        BinaryTreeNode<E> binaryTreeNode = (BinaryTreeNode<E>) node;
        while (binaryTreeNode.getLeftChild() != null) {
            binaryTreeNode = binaryTreeNode.getLeftChild();
        }

        return binaryTreeNode.getElement();
    }

    @Override
    protected E max(TreeNode<E> node, Comparator<E> comparator) {
        if (node == null) {
            return null;
        }

        BinaryTreeNode<E> binaryTreeNode = (BinaryTreeNode<E>) node;
        while (binaryTreeNode.getRightChild() != null) {
            binaryTreeNode = binaryTreeNode.getRightChild();
        }

        return binaryTreeNode.getElement();
    }

    public E max() {
        return max(comparator);
    }

    public E min() {
        return min(comparator);
    }

    @Override
    protected TreeNode<E> contains(E element, TreeNode<E> root) {
        BinaryTreeNode<E> binaryTreeNode = (BinaryTreeNode<E>) root;
        if (binaryTreeNode != null) {
            int compare = comparator.compare(binaryTreeNode.getElement(), element);
            if (compare == 0) {
                return binaryTreeNode;
            } else if (compare > 0) {
                return contains(element, binaryTreeNode.getLeftChild());
            } else {
                return contains(element, binaryTreeNode.getRightChild());
            }
        }

        return null;
    }

    @Override
    public TreeNode<E> remove(E element) {
        return remove(root, element);
    }

    @Override
    protected TreeNode<E> remove(TreeNode<E> node) {
        return remove(root, node.getElement());
    }

    protected TreeNode<E> remove(TreeNode<E> node, E element) {
        if (node == null) {
            return null;
        }

        int compare = comparator.compare(node.getElement(), element);
        BinaryTreeNode<E> binaryTreeNode = (BinaryTreeNode<E>) node;
        if (compare > 0) {
            return remove(binaryTreeNode.getLeftChild(), element);
        } else if (compare < 0) {
            return remove(binaryTreeNode.getRightChild(), element);
        } else {//别跑就是删除你
            if (binaryTreeNode.getChildren().size() == 2) {//如果有两个孩子
                binaryTreeNode.setElement(min(binaryTreeNode.getRightChild(), comparator));//节点更改为右子树最小值，这样左子树不会做任何更改
                remove(binaryTreeNode.getRightChild(), binaryTreeNode.getElement());//同时删除右子树这个最小值
            } else {//如果只有一个孩子或没有孩子
                if(binaryTreeNode.getLeftChild() != null){
                    binaryTreeNode.setElement(binaryTreeNode.getLeftChild().getElement()).setChildren(binaryTreeNode.getLeftChild().getChildren());
                }else if(binaryTreeNode.getRightChild() != null){
                    binaryTreeNode.setElement(binaryTreeNode.getRightChild().getElement()).setChildren(binaryTreeNode.getRightChild().getChildren());
                }else{
                    binaryTreeNode.setElement(null).setChildren(null);
                }
            }

            return binaryTreeNode;
        }
    }
}
