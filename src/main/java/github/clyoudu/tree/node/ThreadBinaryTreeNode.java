package github.clyoudu.tree.node;

import java.util.ArrayList;
import java.util.List;

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

    public BinaryTreeNode<E> getLeftChildThread() {
        if(leftFlag == 1){
            return null;
        }

        return super.getLeftChild();
    }

    public BinaryTreeNode<E> getRightChildThread() {
        if(rightFlag == 1){
            return null;
        }

        return super.getRightChild();
    }

    @Override
    public List<TreeNode<E>> getChildren() {
        List<TreeNode<E>> result = new ArrayList<>();
        if(leftFlag == 0 && leftChild != null){
            result.add(leftChild);
        }
        if(rightFlag == 0 && rightChild != null){
            result.add(rightChild);
        }
        return result;
    }

    @Override
    public AbstractTreeNode<E> setChildren(List<TreeNode<E>> children) {
        if(children == null || children.isEmpty()){
            this.children = null;
            setLeftChild(null);
            setRightChild(null);
            return this;
        }

        if(children.size() > 2){
            throw new RuntimeException("Children size must lte 2.");
        }

        for (TreeNode<E> child : children) {
            if(!(child instanceof BinaryTreeNode)){
                throw new RuntimeException("Children must be a instance of BinaryTreeNode");
            }
        }

        if(children.size() == 2){
            setLeftChild((BinaryTreeNode<E>) children.get(0));
            setRightChild((BinaryTreeNode<E>) children.get(1));
        }else{
            setLeftChild((BinaryTreeNode<E>) children.get(0));
        }

        return this;
    }

    @Override
    public boolean hashChildren() {
        return (leftFlag == 0 && leftChild != null) || (rightFlag == 0 && rightChild != null);
    }
}
