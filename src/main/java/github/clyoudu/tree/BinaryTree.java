package github.clyoudu.tree;

import github.clyoudu.tree.node.TreeNode;

import java.util.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/21 17:23
 * @Description BinaryTree
 */
public class BinaryTree<E> extends AbstractTree<E> implements Tree<E> {

    public BinaryTree(TreeNode<E> root) {
        super(root);
    }

    @Override
    public List<TreeNode<E>> inorderTraversal() {
        return null;
    }
}
