package github.clyoudu.tree;

import github.clyoudu.tree.node.BinaryTreeNode;
import github.clyoudu.tree.node.TreeNode;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/23 18:01
 * @Description TestBinaryTree
 */
public class TestBinaryTree {

    public static void main(String[] args) {
        BinaryTreeNode<String> github = new BinaryTreeNode<>("github")
                .addChild(
                        new BinaryTreeNode<>("clyoudu")
                                .addChild(
                                        new BinaryTreeNode<>("list")
                                                .addChild(new BinaryTreeNode<>("ArrayList.java"))
                                                .addChild(new BinaryTreeNode<>("CircularLinkedList.java"))
                                )
                                .addChild(new BinaryTreeNode<>("matrix"))
                );

        BinaryTree<String> binaryTree = new BinaryTree<>(github);

        binaryTree.addChild(Collections.singletonList(new BinaryTreeNode<>("stack")));

        List<TreeNode<String>> list = binaryTree.preorderTraversal();
        System.out.println("========================preorderTraversal===================");
        list.forEach(node -> System.out.println(genTab(node.getLevel()) + node.getElement()));

        System.out.println("========================contails===================");
        System.out.println(binaryTree.contains("a"));
        System.out.println(binaryTree.contains("CircularLinkedList.java").getElement());


        System.out.println("========================path===================");
        List<TreeNode<String>> path = binaryTree.path("clyoudu", "ArrayList.java");
        path.forEach(stringTreeNode -> System.out.println(stringTreeNode.getElement()));

        System.out.println("========================depth===================");
        System.out.println(binaryTree.depth("ArrayList.java"));

        System.out.println("========================length===================");
        System.out.println(binaryTree.length("github", "ArrayList.java"));

        System.out.println("========================height===================");
        System.out.println(binaryTree.height());

        list = binaryTree.inorderTraversal();
        System.out.println("========================inorderTraversal===================");
        list.forEach(node -> System.out.println(genTab(node.getLevel()) + node.getElement()));

        binaryTree.remove("ArrayList.java");
        list = binaryTree.preorderTraversal();
        System.out.println("========================remove & preorderTraversal===================");
        list.forEach(node -> System.out.println(genTab(node.getLevel()) + node.getElement()));

        System.out.println("========================array tree===================");
        String[] arrayTree = binaryTree.toArray(new String[0]);
        for (String s : arrayTree) {
            System.out.println(s);
        }

        System.out.println("========================full binary tree from array & inorderTraversal===================");
        binaryTree = new BinaryTree<>(new String[]{"A", "B", "C", "D", "E", "F", "G"});
        list = binaryTree.inorderTraversal();
        list.forEach(node -> System.out.println(genTab(node.getLevel()) + node.getElement()));

        System.out.println("========================complete binary tree from array & inorderTraversal===================");
        binaryTree = new BinaryTree<>(new String[]{"A", "B", "C", null, "D", null, "E", null, null, "F", null, null, null, null, "G"});
        list = binaryTree.inorderTraversal();
        list.forEach(node -> System.out.println(genTab(node.getLevel()) + node.getElement()));
    }

    private static String genTab(int level) {
        StringBuilder result = new StringBuilder();
        while (level-- > 0) {
            result.append("  ");
        }

        return result.toString();
    }

}
