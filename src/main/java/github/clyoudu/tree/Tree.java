package github.clyoudu.tree;

import github.clyoudu.tree.node.TreeNode;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/17 15:11
 * @Description Tree
 */
public interface Tree<E> {

    List<TreeNode<E>> preorderTraversal();

    List<TreeNode<E>> postorderTraversal();

    List<TreeNode<E>> inorderTraversal();

    TreeNode<E> getRoot();

    List<TreeNode<E>> getLeaves();

    void clear();

    boolean isEmpty();

    TreeNode<E> contains(E element);

    E min(Comparator<E> comparator);

    E max(Comparator<E> comparator);

    TreeNode<E> addChild(E parent, Collection<TreeNode<E>> child);

    TreeNode<E> addChild(Collection<TreeNode<E>> child);

    TreeNode<E> remove(E element);

    boolean hasChild(E child);

    boolean hasChild(E parent,E child);

    boolean hasSibling(E reference,E target);

    boolean hasDescendant(E parent,E child);

    boolean hasDescendant(E child);

    boolean hasAncestor(E child,E parent);

    List<TreeNode<E>> path(E parent,E child);

    int length(E parent,E child);

    int depth(E node);

    int height(E node);

    int height();

}
