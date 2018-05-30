package github.clyoudu.tree.node;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/30 14:12
 * @Description ThreadBinaryTreeNode
 */
public class ThreadBinaryTreeNode<E> extends BinaryTreeNode<E> implements TreeNode<E> {

    private int leftFlag;
    private int rightFlag;

    public ThreadBinaryTreeNode(E element) {
        super(element);
    }

    public int getLeftFlag() {
        return leftFlag;
    }

    public ThreadBinaryTreeNode setLeftFlag(int leftFlag) {
        this.leftFlag = leftFlag;
        return this;
    }

    public int getRightFlag() {
        return rightFlag;
    }

    public ThreadBinaryTreeNode setRightFlag(int rightFlag) {
        this.rightFlag = rightFlag;
        return this;
    }
}
