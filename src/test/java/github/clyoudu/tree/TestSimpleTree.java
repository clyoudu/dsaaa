package github.clyoudu.tree;

import github.clyoudu.tree.node.SimpleTreeNode;
import github.clyoudu.tree.node.TreeNode;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/18 11:51
 * @Description TestSimpleTree
 */
public class TestSimpleTree {

    public static void main(String[] args){
        SimpleTreeNode<String> github = new SimpleTreeNode<>("github")
                .addChild(
                        new SimpleTreeNode<>("clyoudu")
                                .addChild(
                                        new SimpleTreeNode<>("list")
                                                .addChild(new SimpleTreeNode<>("ArrayList.java"))
                                                .addChild(new SimpleTreeNode<>("CircularLinkedList.java"))
                                                .addChild(new SimpleTreeNode<>("Collection.java"))
                                                .addChild(new SimpleTreeNode<>("DoublyLinkedList.java"))
                                                .addChild(new SimpleTreeNode<>("List.java"))
                                                .addChild(new SimpleTreeNode<>("SingleLinkedList.java"))
                                )
                                .addChild(new SimpleTreeNode<>("matrix"))
                );

        SimpleTree<String> simpleTree = new SimpleTree<>(github);

        simpleTree.addChild(Collections.singletonList(new SimpleTreeNode<>("stack")));

        List<TreeNode<String>> list = simpleTree.preorderTraversal();
        System.out.println("========================preorderTraversal===================");
        list.forEach(node -> System.out.println(genTab(node.getLevel()) + node.getElement()));

        System.out.println("========================contails===================");
        System.out.println(simpleTree.contains("a"));
        System.out.println(simpleTree.contains("DoublyLinkedList.java").getElement());


        System.out.println("========================path===================");
        List<TreeNode<String>> path = simpleTree.path("clyoudu","List.java");
        path.forEach(stringTreeNode -> System.out.println(stringTreeNode.getElement()));

        System.out.println("========================depth===================");
        System.out.println(simpleTree.depth("List.java"));

        System.out.println("========================length===================");
        System.out.println(simpleTree.length("github","List.java"));

        simpleTree.remove("SingleLinkedList.java");
        list = simpleTree.preorderTraversal();
        System.out.println("========================remove & preorderTraversal===================");
        list.forEach(node -> System.out.println(genTab(node.getLevel()) + node.getElement()));

        System.out.println(new Date().getTime());
    }

    private static String genTab(int level){
        StringBuilder result = new StringBuilder();
        while(level -- > 0){
            result.append("  ");
        }

        return result.toString();
    }

}
