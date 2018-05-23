package github.clyoudu.tree.node;

import java.util.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/17 15:23
 * @Description AbstractTreeNode
 */
public abstract class AbstractTreeNode<E> implements TreeNode<E> {

    protected E element;
    protected List<TreeNode<E>> children;
    protected TreeNode<E> parent;
    protected int level;

    public AbstractTreeNode(E element){
        this.element = element;
    }

    @Override
    public List<TreeNode<E>> getChildren() {
        return children;
    }

    @Override
    public E getElement() {
        return element;
    }

    public AbstractTreeNode<E> setChildren(List<TreeNode<E>> children) {
        this.children = children;
        return this;
    }

    public AbstractTreeNode setElement(E element) {
        this.element = element;
        return this;
    }

    @Override
    public TreeNode<E> getParent() {
        return parent;
    }

    protected AbstractTreeNode<E> setParent(TreeNode<E> parent) {
        this.parent = parent;
        this.level = parent.getLevel() + 1;
        return this;
    }

    @Override
    public boolean hashChildren() {
        return children != null && !children.isEmpty();
    }

    @Override
    public int getLevel() {
        return level;
    }

    public TreeNode<E> setLevel(int level) {
        this.level = level;
        return this;
    }
}
