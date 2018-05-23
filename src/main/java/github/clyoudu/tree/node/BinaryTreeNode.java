package github.clyoudu.tree.node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/21 17:24
 * @Description BinaryTreeNode
 */
public class BinaryTreeNode<E> extends AbstractTreeNode<E> implements TreeNode<E> {

    protected BinaryTreeNode<E> leftChild;

    protected BinaryTreeNode<E> rightChild;

    public BinaryTreeNode(E element) {
        super(element);
    }

    public BinaryTreeNode<E> getLeftChild() {
        return leftChild;
    }

    public BinaryTreeNode setLeftChild(BinaryTreeNode<E> leftChild) {
        this.leftChild = leftChild;
        return this;
    }

    public BinaryTreeNode<E> getRightChild() {
        return rightChild;
    }

    public BinaryTreeNode setRightChild(BinaryTreeNode<E> rightChild) {
        this.rightChild = rightChild;
        return this;
    }

    @Override
    public List<TreeNode<E>> getChildren() {
        List<TreeNode<E>> result = new ArrayList<>();
        if(leftChild != null){
            result.add(leftChild);
        }
        if(rightChild != null){
            result.add(rightChild);
        }
        return result;
    }

    @Override
    public AbstractTreeNode<E> setChildren(List<TreeNode<E>> children) {
        if(children == null || children.isEmpty()){
            this.children = null;
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
            leftChild = (BinaryTreeNode<E>) children.get(0);
            rightChild = (BinaryTreeNode<E>) children.get(1);
        }else{
            leftChild = (BinaryTreeNode<E>) children.get(0);
        }

        return this;
    }

    @Override
    public boolean hashChildren() {
        return leftChild != null || rightChild != null;
    }

    public BinaryTreeNode<E> addChild(BinaryTreeNode<E> node) {
        if(leftChild == null){
            leftChild = (BinaryTreeNode<E>) node.setParent(this);
        }else if(rightChild == null){
            rightChild = (BinaryTreeNode<E>) node.setParent(this);
        }else {
            throw new RuntimeException("Children size must lte 2.");
        }
        return this;
    }
}
