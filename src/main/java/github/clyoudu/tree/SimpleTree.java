package github.clyoudu.tree;

import github.clyoudu.tree.node.TreeNode;

import java.util.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/17 15:44
 * @Description SimpleTree
 */
public class SimpleTree<E> extends AbstractTree<E> implements Tree<E> {

    public SimpleTree(TreeNode<E> root) {
        super(root);
    }

    @Override
    public List<TreeNode<E>> inorderTraversal() {
        return postorderTraversal();
    }

    protected List<TreeNode<E>> inorderTraversal(TreeNode<E> node) {
        return postorderTraversal();
    }
}
