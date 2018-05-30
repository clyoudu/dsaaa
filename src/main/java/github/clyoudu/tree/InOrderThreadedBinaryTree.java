package github.clyoudu.tree;

import github.clyoudu.list.ArrayList;
import github.clyoudu.list.List;
import github.clyoudu.tree.node.ThreadBinaryTreeNode;
import github.clyoudu.tree.node.TreeNode;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/30 15:12
 * @Description InOrderThreadedBinaryTree
 */
public class InOrderThreadedBinaryTree<E> extends ThreadedBinaryTree<E> implements Tree<E> {

    ThreadBinaryTreeNode<E> preNode = null;

    public InOrderThreadedBinaryTree(ThreadBinaryTreeNode<E> root) {
        super(root);
        inOrderThreadHelp(root);
    }
    public InOrderThreadedBinaryTree(E[] array) {
        super(array);
        inOrderThreadHelp((ThreadBinaryTreeNode<E>) super.root);
    }

    protected void inOrderThreadHelp(ThreadBinaryTreeNode<E> node) {
        if(node == null){
            return;
        }

        //处理左孩子
        if(node.getLeftFlag() != 1){
            inOrderThreadHelp((ThreadBinaryTreeNode<E>) node.getLeftChild());
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

        //处理右孩子
        if(node.getRightFlag() != 1){
            inOrderThreadHelp((ThreadBinaryTreeNode<E>) node.getRightChild());
        }
    }


    public List<TreeNode<E>> threadInOrderTraversal() {

        List<TreeNode<E>> result = new ArrayList<>();

        ThreadBinaryTreeNode<E> node = (ThreadBinaryTreeNode<E>) root;

        //先找到最左的节点
        while(node != null && node.getLeftFlag() != 1){
            node = (ThreadBinaryTreeNode<E>) node.getLeftChild();
        }

        //然后找后继
        while(node != null){
            result.add(node);
            if(node.getRightFlag() == 1){//如果节点存在后继，说明节点已经没有右子树，直接访问后继
                node = (ThreadBinaryTreeNode<E>) node.getRightChild();
            }else{//否则表明该节点还有右子树，那么访问右子树最左的节点
                node = (ThreadBinaryTreeNode<E>) node.getRightChild();
                while(node != null && node.getLeftFlag() != 1){
                    node = (ThreadBinaryTreeNode<E>) node.getLeftChild();
                }
            }
        }

        return result;

    }
}
