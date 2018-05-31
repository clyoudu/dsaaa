package github.clyoudu.tree;

import github.clyoudu.tree.node.ThreadBinaryTreeNode;
import github.clyoudu.tree.node.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/30 14:11
 * @Description PreOrderThreadedBinaryTree
 */
public class PreOrderThreadedBinaryTree<E> extends ThreadedBinaryTree<E> implements Tree<E> {

    private ThreadBinaryTreeNode<E> preNode = null;

    public PreOrderThreadedBinaryTree(ThreadBinaryTreeNode<E> root) {
        super(root);
        preOrderThreadHelp(root);
    }
    public PreOrderThreadedBinaryTree(E[] array) {
        super(array);
        preOrderThreadHelp((ThreadBinaryTreeNode<E>) super.root);
    }

    @Override
    public ThreadBinaryTreeNode<E> preNode(E element) {
        ThreadBinaryTreeNode<E> node = (ThreadBinaryTreeNode<E>) contains(element);
        if(node == null){
            throw new RuntimeException("node not exist!");
        }

        return preNode(node);
    }

    protected ThreadBinaryTreeNode<E> preNode(ThreadBinaryTreeNode<E> node) {
        if(node.getLeftFlag() == 1){
            return (ThreadBinaryTreeNode<E>) node.getLeftChild();
        }

        ThreadBinaryTreeNode<E> parent = (ThreadBinaryTreeNode<E>) node.getParent();
        if(parent != null && (parent.getLeftFlag() == 1 || node == parent.getLeftChild())){//如果有父节点，且父节点没有左孩子或左孩子就是自己
            return parent;
        }else if(parent != null && parent.getLeftFlag() != 1){//如果有父节点且有左孩子，那么寻找左子树的最右节点
            ThreadBinaryTreeNode<E> returnNode = (ThreadBinaryTreeNode<E>) parent.getLeftChild();
            while(returnNode.hashChildren()){
                returnNode = (ThreadBinaryTreeNode<E>) returnNode.getRightChild();
            }
            return returnNode;
        }

        return null;
    }

    @Override
    public ThreadBinaryTreeNode<E> nextNode(E element) {
        ThreadBinaryTreeNode<E> node = (ThreadBinaryTreeNode<E>) contains(element);
        if(node == null){
            throw new RuntimeException("node not exist!");
        }

        return nextNode(node);
    }

    protected ThreadBinaryTreeNode<E> nextNode(ThreadBinaryTreeNode<E> node) {
        if(node.getRightFlag() == 1){
            return (ThreadBinaryTreeNode<E>) node.getRightChild();
        }

        if(node.getLeftFlag() != 1){
            return (ThreadBinaryTreeNode<E>) node.getLeftChild();
        }

        if(node.getLeftFlag() == 1 && node.getRightFlag() != 1){
            return (ThreadBinaryTreeNode<E>) node.getRightChild();
        }

        return null;
    }

    protected void preOrderThreadHelp(ThreadBinaryTreeNode<E> node) {
        if(node == null){
            return;
        }

        //当前节点左孩子为空，则设置左孩子为该节点的前驱
        if(node.getLeftChild() == null){
            node.setLeftFlag(1);
            node.setLeftChild(preNode);
        }

        //当前节点前驱的右孩子为空，则设置前驱的右孩子为当前节点
        if(preNode != null && preNode.getRightChild() == null){
            preNode.setRightFlag(1);
            preNode.setRightChild(node);
        }

        preNode = node;

        //处理左孩子
        if(node.getLeftFlag() != 1){
            preOrderThreadHelp((ThreadBinaryTreeNode<E>) node.getLeftChild());
        }

        //处理右孩子
        if(node.getRightFlag() != 1){
            preOrderThreadHelp((ThreadBinaryTreeNode<E>) node.getRightChild());
        }
    }

    public List<TreeNode<E>> threadPreOrderTraversal() {
        List<TreeNode<E>> result = new ArrayList<>();

        ThreadBinaryTreeNode<E> node = (ThreadBinaryTreeNode<E>) root;
        while(node != null){
            while(node.getLeftFlag() != 1){
                result.add(node);//如果这个节点不是任何一个节点的前驱，访问该节点
                node = (ThreadBinaryTreeNode<E>) node.getLeftChild();//并继续遍历左子树
            }//直到找到一个节点，这个节点是某一个节点的前驱

            result.add(node);//访问这个节点，这个节点不存在左子树了，直接访问右子树或后继
            node = (ThreadBinaryTreeNode<E>) node.getRightChild();//继续遍历右子树
        }

        return result;
    }
}
