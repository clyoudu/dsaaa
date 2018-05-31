package github.clyoudu.tree;

import github.clyoudu.list.ArrayList;
import github.clyoudu.list.List;
import github.clyoudu.tree.node.ThreadBinaryTreeNode;
import github.clyoudu.tree.node.TreeNode;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/30 15:16
 * @Description PostOrderThreadedBinaryTree
 */
public class PostOrderThreadedBinaryTree<E> extends ThreadedBinaryTree<E> implements Tree<E>  {

    private ThreadBinaryTreeNode<E> preNode = null;

    public PostOrderThreadedBinaryTree(ThreadBinaryTreeNode<E> root) {
        super(root);
        postOrderThreadHelp(root);
    }
    public PostOrderThreadedBinaryTree(E[] array) {
        super(array);
        postOrderThreadHelp((ThreadBinaryTreeNode<E>) super.root);
    }

    @Override
    public ThreadBinaryTreeNode<E> preNode(E element) {
        return null;
    }

    @Override
    public ThreadBinaryTreeNode<E> nextNode(E element) {
        return null;
    }

    protected void postOrderThreadHelp(ThreadBinaryTreeNode<E> node) {
        if(node == null){
            return;
        }

        //处理左孩子
        if(node.getLeftFlag() != 1){
            postOrderThreadHelp((ThreadBinaryTreeNode<E>) node.getLeftChild());
        }

        //处理右孩子
        if(node.getRightFlag() != 1){
            postOrderThreadHelp((ThreadBinaryTreeNode<E>) node.getRightChild());
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
    }

    public List<TreeNode<E>> threadPostOrderTraversal() {

        List<TreeNode<E>> result = new ArrayList<>();

        ThreadBinaryTreeNode<E> node = (ThreadBinaryTreeNode<E>) root;

        //先找到最左的节点
        while(node != null && node.getLeftFlag() != 1){
            node = (ThreadBinaryTreeNode<E>) node.getLeftChild();
        }

        //然后找后继
        ThreadBinaryTreeNode<E> pre = null;
        while(node != null){
            if(node.getRightFlag() == 1){//如果节点存在后继，说明节点已经没有右子树，直接访问该节点
                result.add(node);
                pre = node;//保存这个节点
                node = (ThreadBinaryTreeNode<E>) node.getRightChild();//直接访问后继
            }else{//否则，节点存在右子树
                if(node.getRightChild() == pre){//如果当前节点的右孩子等于前一个处理的节点，说明这个节点是一个右孩子的父节点，下一次访问的节点应该是这个父节点的右孩子
                    result.add(node);
                    if(node == root){//如果这个父节点已经是根节点了，遍历结束
                        break;
                    }

                    pre = node;
                    node = (ThreadBinaryTreeNode<E>) node.getParent();
                }else{//否则，说明pre节点是当前节点node的左子树，那么直接去找node节点右子树的最左节点
                    node = (ThreadBinaryTreeNode<E>) node.getRightChild();
                    while(node != null && node.getLeftFlag() != 1){
                        node = (ThreadBinaryTreeNode<E>) node.getLeftChild();
                    }//最左节点找到后，首先访问这个节点
                }

            }
        }

        return result;

    }
}
