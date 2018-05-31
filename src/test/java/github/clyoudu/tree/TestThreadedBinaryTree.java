package github.clyoudu.tree;

import github.clyoudu.tree.node.TreeNode;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/30 15:30
 * @Description TestThreadedBinaryTree
 */
public class TestThreadedBinaryTree {

    public static void main(String[] args){
        BinaryTree<String> binaryTree = new BinaryTree<>(new String[]{
                "A","B","C","D",null,"E","F",null,"G",null,null,"H",null,null,null
        });
        PreOrderThreadedBinaryTree<String> preOrderThreadedBinaryTree = new PreOrderThreadedBinaryTree<>(new String[]{
                "A","B","C","D",null,"E","F",null,"G",null,null,"H",null,null,null
        });
        InOrderThreadedBinaryTree<String> inOrderThreadedBinaryTree = new InOrderThreadedBinaryTree<>(new String[]{
                "A","B","C","D",null,"E","F",null,"G",null,null,"H",null,null,null
        });
        PostOrderThreadedBinaryTree<String> postOrderThreadedBinaryTree = new PostOrderThreadedBinaryTree<>(new String[]{
                "A","B","C","D",null,"E","F",null,"G",null,null,"H",null,null,null
        });

        System.out.println("========================binary tree preorderTraversal===================");
        binaryTree.preorderTraversal().forEach(stringTreeNode -> System.out.println(stringTreeNode.getElement()));

        System.out.println("========================threaded binary tree preorderTraversal===================");
        preOrderThreadedBinaryTree.preorderTraversal().forEach(stringTreeNode -> System.out.println(stringTreeNode.getElement()));

        System.out.println("========================threaded binary tree threadPreOrderTraversal===================");
        preOrderThreadedBinaryTree.threadPreOrderTraversal().forEach(stringTreeNode -> System.out.println(stringTreeNode.getElement()));

        System.out.println("========================binary tree inorderTraversal===================");
        binaryTree.inorderTraversal().forEach(stringTreeNode -> System.out.println(stringTreeNode.getElement()));

        System.out.println("========================threaded binary tree inorderTraversal===================");
        preOrderThreadedBinaryTree.inorderTraversal().forEach(stringTreeNode -> System.out.println(stringTreeNode.getElement()));

        System.out.println("========================threaded binary tree threadInOrderTraversal===================");
        inOrderThreadedBinaryTree.threadInOrderTraversal().forEach(stringTreeNode -> System.out.println(stringTreeNode.getElement()));

        System.out.println("========================binary tree postorderTraversal===================");
        binaryTree.postorderTraversal().forEach(stringTreeNode -> System.out.println(stringTreeNode.getElement()));

        System.out.println("========================threaded binary tree postorderTraversal===================");
        preOrderThreadedBinaryTree.postorderTraversal().forEach(stringTreeNode -> System.out.println(stringTreeNode.getElement()));

        System.out.println("========================threaded binary tree threadInOrderTraversal===================");
        postOrderThreadedBinaryTree.threadPostOrderTraversal().forEach(stringTreeNode -> System.out.println(stringTreeNode.getElement()));

        System.out.println("========================pre order threaded binary tree preNode===================");
        Stream.of("A","B","C","D","E","F","G","H").forEach(s -> {
            TreeNode<String> node = preOrderThreadedBinaryTree.preNode(s);
            if(node == null){
                System.out.println(s + " <- null");
            }else{
                System.out.println(s + " <- " + node.getElement());
            }
        });

        System.out.println("========================pre order threaded binary tree nextNode===================");
        Stream.of("A","B","C","D","E","F","G","H").forEach(s -> {
            TreeNode<String> node = preOrderThreadedBinaryTree.nextNode(s);
            if(node == null){
                System.out.println(s + " -> null");
            }else{
                System.out.println(s + " -> " + node.getElement());
            }
        });

    }

}
