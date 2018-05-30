package github.clyoudu.tree;

import github.clyoudu.tree.node.TreeNode;

import java.util.*;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/17 16:14
 * @Description AbstractTree
 */
public abstract class AbstractTree<E> implements Tree<E> {

    protected TreeNode<E> root;

    public AbstractTree(TreeNode<E> root){
        this.root = root;
    }

    @Override
    public List<TreeNode<E>> preorderTraversal() {

        return preorderTraversal(root);
    }

    @Override
    public List<TreeNode<E>> postorderTraversal() {
        return postorderTraversal(root);
    }

    protected List<TreeNode<E>> preorderTraversal(TreeNode<E> node) {
        List<TreeNode<E>> result = new ArrayList<>();

        if(node != null){
            int level = node.getLevel();
            result.add(node);
            if(node.hashChildren()){
                List<TreeNode<E>> children = node.getChildren();
                for (TreeNode<E> child : children) {
                    result.addAll(preorderTraversal(child.setLevel(level + 1)));
                }
            }
        }

        return result;
    }

    protected List<TreeNode<E>> postorderTraversal(TreeNode<E> node) {
        List<TreeNode<E>> result = new ArrayList<>();

        if(node != null){
            int level = node.getLevel();
            if(node.hashChildren()){
                List<TreeNode<E>> children = node.getChildren();
                for (TreeNode<E> child : children) {
                    result.addAll(postorderTraversal(child.setLevel(level + 1)));
                }
            }
            result.add(node);
        }

        return result;
    }

    @Override
    public TreeNode<E> getRoot() {
        return root;
    }

    @Override
    public List<TreeNode<E>> getLeaves() {
        return getLeaves(root);
    }

    protected List<TreeNode<E>> getLeaves(TreeNode<E> node) {
        List<TreeNode<E>> result = new ArrayList<>();
        if(node != null){
            if(node.hashChildren()){
                List<TreeNode<E>> children = node.getChildren();
                for (TreeNode<E> child : children) {
                    result.addAll(getLeaves(child));
                }
            }else{
                result.add(node);
            }
        }
        return result;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public TreeNode<E> contains(E element) {
        return contains(element,root);
    }

    protected TreeNode<E> contains(E element, TreeNode<E> root) {
        if(root != null){
            if(element.equals(root.getElement())){
                return root;
            }

            if(root.hashChildren()){
                List<TreeNode<E>> children = root.getChildren();
                for (TreeNode<E> child : children) {
                    TreeNode<E> found = contains(element,child);
                    if(found != null){
                        return found;
                    }
                }
            }
        }
        return null;
    }


    @Override
    public E min(Comparator<E> comparator) {
        return min(root,comparator);
    }

    @Override
    public E max(Comparator<E> comparator) {
        return max(root,comparator);
    }

    protected E min(TreeNode<E> node, Comparator<E> comparator) {
        E min = null;
        if(node != null){
            min = node.getElement();

            if(node.hashChildren()){
                List<TreeNode<E>> children = node.getChildren();
                for (TreeNode<E> child : children) {
                    E minOfChild = min(child,comparator);
                    int f = comparator.compare(minOfChild,min);
                    if(f < 0){
                        min = minOfChild;
                    }
                }
            }
        }
        return min;
    }

    protected E max(TreeNode<E> node, Comparator<E> comparator) {
        E max = null;
        if(node != null){
            max = node.getElement();

            if(node.hashChildren()){
                List<TreeNode<E>> children = node.getChildren();
                for (TreeNode<E> child : children) {
                    E maxOfChild = min(child,comparator);
                    int f = comparator.compare(maxOfChild,max);
                    if(f > 0){
                        max = maxOfChild;
                    }
                }
            }
        }
        return max;
    }

    @Override
    public TreeNode<E> addChild(E parent, Collection<TreeNode<E>> child) {

        TreeNode<E> parentNode = contains(parent);
        if(parent == null){
            throw new RuntimeException("Node not exist!");
        }

        return addChild(parentNode,child);
    }

    protected TreeNode<E> addChild(TreeNode<E> parentNode, Collection<TreeNode<E>> child) {
        List<TreeNode<E>> children = parentNode.getChildren();
        if(children == null){
            children = new ArrayList<>();
            children.addAll(child);
        }

        return parentNode;
    }

    @Override
    public TreeNode<E> addChild(Collection<TreeNode<E>> child) {
        return addChild(root,child);
    }

    @Override
    public TreeNode<E> remove(E element) {
        return remove(contains(element));
    }

    protected TreeNode<E> remove(TreeNode<E> node) {
        if(node.equals(root)){
            TreeNode<E> toReturn = root;
            clear();
            return toReturn;
        }else{
            node.getParent().getChildren().remove(node);
            return node;
        }
    }

    @Override
    public boolean hasChild(E child) {
        return hasChild(root,child);
    }

    @Override
    public boolean hasChild(E parent, E child) {
        TreeNode<E> parentNode = contains(parent);
        if(parentNode == null){
            throw new RuntimeException("Node not exist!");
        }

        return hasChild(parentNode,child);
    }

    public boolean hasChild(TreeNode<E> parentNode, E child) {
        if(parentNode.hashChildren()){
            List<TreeNode<E>> children = parentNode.getChildren();
            for (TreeNode<E> childNode : children) {
                if(childNode.getElement().equals(child)){
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean hasSibling(TreeNode<E> referenceNode,E target) {
        if(referenceNode.hashChildren()){
            List<TreeNode<E>> children = referenceNode.getParent().getChildren();
            for (TreeNode<E> child : children) {
                if(child.getElement().equals(target)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasSibling(E reference,E target){
        TreeNode<E> referenceNode = contains(reference);
        if(referenceNode == null){
            throw new RuntimeException("Node not exist!");
        }

        if(referenceNode == root){
            return false;
        }

        return hasSibling(referenceNode,target);
    }

    protected boolean hasDescendant(TreeNode<E> parentNode,E child) {
        if(parentNode == null){
            return false;
        }

        if(hasChild(parentNode,child)){
            return true;
        }

        if(parentNode.hashChildren()){
            List<TreeNode<E>> children = parentNode.getChildren();
            for (TreeNode<E> childNode : children) {
                if(hasDescendant(childNode,child)){
                    return true;
                }
            }
        }

        return false;
    }

    protected boolean hasAncestor(E child,TreeNode<E> parentNode) {
        if(parentNode != root){
            return hasDescendant(parentNode,child);
        }
        return false;
    }

    @Override
    public boolean hasDescendant(E parent, E child) {
        TreeNode<E> parentNode = contains(parent);
        if(parentNode == null){
            throw new RuntimeException("Node not exist!");
        }
        return hasDescendant(parentNode,child);
    }

    @Override
    public boolean hasDescendant(E child) {
        return hasDescendant(root,child);
    }

    @Override
    public boolean hasAncestor(E child, E parent) {
        TreeNode<E> parentNode = contains(parent);
        if(parentNode == null){
            throw new RuntimeException("Node not exist!");
        }
        return hasAncestor(child,parentNode);
    }

    protected List<TreeNode<E>> path(TreeNode<E> parentNode, TreeNode<E> childNode) {
        List<TreeNode<E>> result = new ArrayList<>();
        TreeNode<E> curNode = childNode;
        while(curNode != null && curNode != parentNode && curNode.getParent() != null){
            result.add(curNode);
            curNode = curNode.getParent();
        }
        if(curNode == parentNode){
            result.add(curNode);
            Collections.reverse(result);
            return result;
        }
        return null;
    }

    protected int length(TreeNode<E> parentNode, TreeNode<E> childNode) {
        List<TreeNode<E>> path = path(parentNode,childNode);
        if(path == null){
            throw new RuntimeException("There is no path from parent node to child node");
        }
        return path.size() - 1;
    }

    protected int depth(TreeNode<E> node) {
        if(root == null){
            return 0;
        }
        return length(root,node) + 1;
    }

    protected int height(TreeNode<E> node) {
        List<TreeNode<E>> leaves = getLeaves();
        int maxHeight = 0;
        if(leaves != null && !leaves.isEmpty()){
            for (TreeNode<E> leaf : leaves) {
                List<TreeNode<E>> path = path(node,leaf);
                if(path != null){
                    maxHeight = Math.max(maxHeight,path.size());
                }
            }
        }
        return maxHeight;
    }

    @Override
    public List<TreeNode<E>> path(E parent, E child) {
        TreeNode<E> parentNode = contains(parent);
        if(parentNode == null){
            throw new RuntimeException("Node not exist!");
        }
        TreeNode<E> childNode = contains(child);
        if(childNode == null){
            throw new RuntimeException("Node not exist!");
        }
        return path(parentNode,childNode);
    }

    @Override
    public int length(E parent, E child) {
        TreeNode<E> parentNode = contains(parent);
        if(parentNode == null){
            throw new RuntimeException("Node not exist!");
        }
        TreeNode<E> childNode = contains(child);
        if(childNode == null){
            throw new RuntimeException("Node not exist!");
        }
        return length(parentNode,childNode);
    }

    @Override
    public int depth(E node) {
        TreeNode<E> treeNode = contains(node);
        if(treeNode == null){
            throw new RuntimeException("Node not exist!");
        }
        return depth(treeNode);
    }

    @Override
    public int height(E node) {
        TreeNode<E> treeNode = contains(node);
        if(treeNode == null){
            throw new RuntimeException("Node not exist!");
        }
        return height(treeNode);
    }

    @Override
    public int height() {
        return height(root);
    }
}
