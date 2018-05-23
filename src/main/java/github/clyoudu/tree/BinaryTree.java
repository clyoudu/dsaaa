package github.clyoudu.tree;

import github.clyoudu.tree.node.BinaryTreeNode;
import github.clyoudu.tree.node.TreeNode;

import java.util.ArrayList;
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

    @Override
    public List<TreeNode<E>> inorderTraversal() {
        return inorderTraversal(root);
    }

    protected List<TreeNode<E>> inorderTraversal(TreeNode<E> node) {
        List<TreeNode<E>> result = new ArrayList<>();

        if(node != null){
            BinaryTreeNode<E> binaryNode  = (BinaryTreeNode<E>) node;
            int level = binaryNode.getLevel();

            if(node.hashChildren()){
                result.addAll(inorderTraversal(binaryNode.getLeftChild().setLevel(level + 1)));
                result.add(binaryNode);
                result.addAll(inorderTraversal(binaryNode.getRightChild().setLevel(level + 1)));
            }
        }

        return result;
    }

    protected TreeNode<E> remove(TreeNode<E> node) {
        if(node.equals(root)){
            TreeNode<E> toReturn = root;
            clear();
            return toReturn;
        }else{
            BinaryTreeNode<E> parent = (BinaryTreeNode<E>) node.getParent();
            if(parent.getLeftChild() == node){
                parent.setLeftChild(null);
            }else if(parent.getRightChild() == node){
                parent.setRightChild(null);
            }
            return node;
        }
    }
}
