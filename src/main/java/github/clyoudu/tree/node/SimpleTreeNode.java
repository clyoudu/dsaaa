package github.clyoudu.tree.node;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/17 15:15
 * @Description SimpleTreeNode
 */
public class SimpleTreeNode<E> extends AbstractTreeNode<E> implements TreeNode<E> {

    public SimpleTreeNode(E element) {
        super(element);
    }


    public SimpleTreeNode<E> addChild(SimpleTreeNode<E> node){
        if(children == null){
            children = new ArrayList<>();
        }
        children.add(node.setParent(this));
        return this;
    }


}
