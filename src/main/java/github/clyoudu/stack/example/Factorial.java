package github.clyoudu.stack.example;

import github.clyoudu.stack.ArrayStack;
import github.clyoudu.stack.Stack;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/12 16:15
 * @Description Factorial
 */
public class Factorial {

    public static void main(String[] args){
        System.out.println(factorial(6));
        System.out.println(factorialStack(6));
    }

    public static Long factorial(int n){
        if(n == 1){
            return 1L;
        }else{
            return n * factorial(n-1);
        }
    }

    public static Long factorialStack(int n){
        Stack<Integer> stack = new ArrayStack<>();
        while(n > 0){
            stack.push(n--);
        }

        Long result = 1L;
        while(!stack.isEmpty()){
            result *= stack.pop();
        }

        return  result;
    }

}
