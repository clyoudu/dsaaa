package github.clyoudu.tree.node;

import java.util.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/17 15:17
 * @Description TreeNode
 */
public interface TreeNode<E> {

    List<TreeNode<E>> getChildren();

    E getElement();

    TreeNode<E> getParent();

    boolean hashChildren();

    int getLevel();

    TreeNode<E> setLevel(int level);

}
