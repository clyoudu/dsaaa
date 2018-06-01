package github.clyoudu.tree;

import github.clyoudu.tree.node.ThreadBinaryTreeNode;
import github.clyoudu.tree.node.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/30 15:00
 * @Description ThreadedBinaryTree
 */
public abstract class ThreadedBinaryTree<E> extends BinaryTree<E> implements Tree<E> {

    public ThreadedBinaryTree(ThreadBinaryTreeNode<E> root) {
        super(root);
    }

    public ThreadedBinaryTree(E[] array){
        super(genRoot(array));
    }

    private static <E> ThreadBinaryTreeNode<E> genRoot(E[] array) {
        if (array == null || array.length == 0 || array[0] == null) {
            return null;
        }

        int index = 0;
        ThreadBinaryTreeNode<E> root = new ThreadBinaryTreeNode<>(array[index]);

        genRoot(array, index, root);

        return root;
    }

    private static <E> void genRoot(E[] array, int index, ThreadBinaryTreeNode<E> node) {
        int leftIndex = 2 * index + 1;
        if (leftIndex < array.length) {
            E leftElement = array[leftIndex];
            if(leftElement != null){
                ThreadBinaryTreeNode<E> leftChild = new ThreadBinaryTreeNode<>(leftElement);
                leftChild.setParent(node);
                node.setLeftChild(leftChild);
                genRoot(array,leftIndex,leftChild);
            }
        }

        int rightIndex = 2 * index + 2;
        if (rightIndex < array.length) {
            E rightElement = array[rightIndex];
            if(rightElement != null){
                ThreadBinaryTreeNode<E> rightChild = new ThreadBinaryTreeNode<>(rightElement);
                rightChild.setParent(node);
                node.setRightChild(rightChild);
                genRoot(array,rightIndex,rightChild);
            }
        }
    }

    @Override
    public List<TreeNode<E>> preorderTraversal() {

        return preorderTraversal(root);
    }

    @Override
    public List<TreeNode<E>> postorderTraversal() {
        return postorderTraversal(root);
    }

    protected List<TreeNode<E>> preorderTraversal(TreeNode<E> node) {
        List<TreeNode<E>> result = new ArrayList<>();

        if(node != null){
            int level = node.getLevel();
            result.add(node);
            if(node.hashChildren()){
                ThreadBinaryTreeNode<E> threadBinaryTreeNode = (ThreadBinaryTreeNode<E>) node;
                if(threadBinaryTreeNode.getLeftChild() != null && threadBinaryTreeNode.getLeftFlag() != 1){
                    result.addAll(preorderTraversal(threadBinaryTreeNode.getLeftChild().setLevel(level + 1)));
                }
                if(threadBinaryTreeNode.getRightChild() != null && threadBinaryTreeNode.getRightFlag() != 1){
                    result.addAll(preorderTraversal(threadBinaryTreeNode.getRightChild().setLevel(level + 1)));
                }

            }
        }

        return result;
    }

    protected List<TreeNode<E>> postorderTraversal(TreeNode<E> node) {
        List<TreeNode<E>> result = new ArrayList<>();

        if(node != null){
            int level = node.getLevel();
            if(node.hashChildren()){
                ThreadBinaryTreeNode<E> threadBinaryTreeNode = (ThreadBinaryTreeNode<E>) node;
                if(threadBinaryTreeNode.getLeftChild() != null && threadBinaryTreeNode.getLeftFlag() != 1){
                    result.addAll(postorderTraversal(threadBinaryTreeNode.getLeftChild().setLevel(level + 1)));
                }
                if(threadBinaryTreeNode.getRightChild() != null && threadBinaryTreeNode.getRightFlag() != 1){
                    result.addAll(postorderTraversal(threadBinaryTreeNode.getRightChild().setLevel(level + 1)));
                }
            }
            result.add(node);
        }

        return result;
    }

    @Override
    public List<TreeNode<E>> inorderTraversal() {
        return inorderTraversal(root);
    }

    protected List<TreeNode<E>> inorderTraversal(TreeNode<E> node) {
        List<TreeNode<E>> result = new ArrayList<>();

        if (node != null) {
            ThreadBinaryTreeNode<E> binaryNode = (ThreadBinaryTreeNode<E>) node;
            int level = binaryNode.getLevel();

            if (binaryNode.getLeftChild() != null && binaryNode.getLeftFlag() != 1) {
                result.addAll(inorderTraversal(binaryNode.getLeftChild().setLevel(level + 1)));
            }

            result.add(binaryNode);

            if (binaryNode.getRightChild() != null && binaryNode.getRightFlag() != 1) {
                result.addAll(inorderTraversal(binaryNode.getRightChild().setLevel(level + 1)));
            }


        }

        return result;
    }

    public  ThreadBinaryTreeNode<E> preNode(E element){
        ThreadBinaryTreeNode<E> node = (ThreadBinaryTreeNode<E>) contains(element);
        if(node == null){
            throw new RuntimeException("node not exist!");
        }

        return preNode(node);
    }

    protected abstract ThreadBinaryTreeNode<E> preNode(ThreadBinaryTreeNode<E> node);

    public ThreadBinaryTreeNode<E> nextNode(E element) {
        ThreadBinaryTreeNode<E> node = (ThreadBinaryTreeNode<E>) contains(element);
        if(node == null){
            throw new RuntimeException("node not exist!");
        }

        return nextNode(node);
    }

    protected abstract ThreadBinaryTreeNode<E> nextNode(ThreadBinaryTreeNode<E> node);

}
