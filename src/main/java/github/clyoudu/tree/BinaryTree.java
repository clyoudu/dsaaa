package github.clyoudu.tree;

import github.clyoudu.tree.node.BinaryTreeNode;
import github.clyoudu.tree.node.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/21 17:23
 * @Description BinaryTree
 */
public class BinaryTree<E> extends AbstractTree<E> implements Tree<E> {

    public BinaryTree(BinaryTreeNode<E> root) {
        super(root);
    }

    public BinaryTree(E[] array) {
        super(genRoot(array));
    }

    private static <E> TreeNode<E> genRoot(E[] array) {
        if (array == null || array.length == 0 || array[0] == null) {
            return null;
        }

        int index = 0;
        BinaryTreeNode<E> root = new BinaryTreeNode<>(array[index]);

        genRoot(array, index, root);

        return root;
    }

    private static <E> void genRoot(E[] array, int index, BinaryTreeNode<E> node) {
        int leftIndex = 2 * index + 1;
        if (leftIndex < array.length) {
            E leftElement = array[leftIndex];
            if(leftElement != null){
                BinaryTreeNode<E> leftChild = new BinaryTreeNode<>(leftElement);
                leftChild.setParent(node);
                node.setLeftChild(leftChild);
                genRoot(array,leftIndex,leftChild);
            }
        }

        int rightIndex = 2 * index + 2;
        if (rightIndex < array.length) {
            E rightElement = array[rightIndex];
            if(rightElement != null){
                BinaryTreeNode<E> rightChild = new BinaryTreeNode<>(rightElement);
                rightChild.setParent(node);
                node.setRightChild(rightChild);
                genRoot(array,rightIndex,rightChild);
            }
        }
    }

    @Override
    public List<TreeNode<E>> inorderTraversal() {
        return inorderTraversal(root);
    }

    protected List<TreeNode<E>> inorderTraversal(TreeNode<E> node) {
        List<TreeNode<E>> result = new ArrayList<>();

        if (node != null) {
            BinaryTreeNode<E> binaryNode = (BinaryTreeNode<E>) node;
            int level = binaryNode.getLevel();

            if (binaryNode.getLeftChild() != null) {
                result.addAll(inorderTraversal(binaryNode.getLeftChild().setLevel(level + 1)));
            }

            result.add(binaryNode);

            if (binaryNode.getRightChild() != null) {
                result.addAll(inorderTraversal(binaryNode.getRightChild().setLevel(level + 1)));
            }

        }

        return result;
    }

    protected TreeNode<E> remove(TreeNode<E> node) {
        if (node.equals(root)) {
            TreeNode<E> toReturn = root;
            clear();
            return toReturn;
        } else {
            BinaryTreeNode<E> parent = (BinaryTreeNode<E>) node.getParent();
            if (parent.getLeftChild() == node) {
                parent.setLeftChild(null);
            } else if (parent.getRightChild() == node) {
                parent.setRightChild(null);
            }
            return node;
        }
    }

    public E[] toArray(E[] a) {
        int height = height();
        Object[] result = new Object[(int) (Math.pow(2, height) - 1)];

        if (height != 0) {
            int index = 0;
            result[index] = root.getElement();
            toArray(root, index, result);
        }

        if (a.length < result.length)
            return (E[]) Arrays.copyOf(result, result.length, a.getClass());
        System.arraycopy(result, 0, a, 0, result.length);
        if (a.length > result.length)
            a[result.length] = null;
        return a;
    }

    private void toArray(TreeNode<E> node, int index, Object[] result) {
        BinaryTreeNode<E> binaryTreeNode = (BinaryTreeNode<E>) node;
        if (binaryTreeNode.getLeftChild() != null) {
            int leftIndex = 2 * index + 1;
            if (leftIndex >= result.length) {
                return;
            }
            result[leftIndex] = binaryTreeNode.getLeftChild().getElement();
            toArray(binaryTreeNode.getLeftChild(), leftIndex, result);
        }

        if (binaryTreeNode.getRightChild() != null) {
            int rightIndex = 2 * index + 2;
            if (rightIndex >= result.length) {
                return;
            }
            result[rightIndex] = binaryTreeNode.getRightChild().getElement();
            toArray(binaryTreeNode.getRightChild(), rightIndex, result);
        }
    }
}
