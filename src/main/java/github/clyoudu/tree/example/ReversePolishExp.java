package github.clyoudu.tree.example;

import github.clyoudu.stack.ArrayStack;
import github.clyoudu.stack.Stack;
import github.clyoudu.stack.example.StackCalculator;
import github.clyoudu.stack.example.SymbolPairChecker;
import github.clyoudu.tree.BinaryTree;
import github.clyoudu.tree.node.BinaryTreeNode;

import java.util.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/24 15:03
 * @Description ReversePolishExp
 */
public class ReversePolishExp {

    public static BinaryTree<String> reversePolishTree(String exp){
        exp = exp.replaceAll(" ", "")//干掉空格
                .replaceAll("\\(-", "(0-");//转换负数(-2) -> (0-2)，其中负数必须用括号括起来
        SymbolPairChecker.check(exp);

        List<String> reversePolishExp = StackCalculator.reversePolishExp(exp);

        //11+22*33+(44*55+66)*77
        Stack<BinaryTreeNode<String>> stack = new ArrayStack<>();
        reversePolishExp.forEach(ele -> {
            if(ele.length() > 1 || StackCalculator.typeOfCharacter(ele.charAt(0)).equals("num")){
                stack.push(new BinaryTreeNode<>(ele));
            }else if(StackCalculator.typeOfCharacter(ele.charAt(0)).equals("op")){
                BinaryTreeNode<String> rightChild = stack.pop();
                BinaryTreeNode<String> leftChild = stack.pop();
                stack.push(new BinaryTreeNode<>(ele).setLeftChild(leftChild).setRightChild(rightChild));
            }
        });
        return new BinaryTree<>(stack.pop());
    }

    public static void main(String[] args){
        StackCalculator.reversePolishExp("11+22*33+(44*55+66)*77").forEach(System.out::print);
        System.out.println();
        reversePolishTree("11+22*33+(44*55+66)*77").postorderTraversal().forEach(node -> System.out.print(node.getElement()));
    }

}
