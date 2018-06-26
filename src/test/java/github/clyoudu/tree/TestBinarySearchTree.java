package github.clyoudu.tree;

import java.util.Comparator;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/6/1 17:25
 * @Description TestBinarySearchTree
 */
public class TestBinarySearchTree {

    public static void main(String[] args){
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>(Integer::compareTo);
        binarySearchTree.addChild(5).addChild(10).addChild(7).addChild(8)
                .addChild(3).addChild(9).addChild(6).addChild(2)
                .addChild(1).addChild(4);

        System.out.println("========================binary search tree inorderTraversal===================");
        binarySearchTree.inorderTraversal().forEach(stringTreeNode -> System.out.println(stringTreeNode.getElement()));

        System.out.println("========================binary search tree max===================");
        System.out.println(binarySearchTree.max());

        System.out.println("========================binary search tree min===================");
        System.out.println(binarySearchTree.min());

        System.out.println("========================binary search tree remove===================");
        binarySearchTree.remove(5).getElement();
        binarySearchTree.inorderTraversal().forEach(stringTreeNode -> System.out.println(stringTreeNode.getElement()));



    }

}
