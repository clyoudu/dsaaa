package github.clyoudu.stack;


/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/10 11:43
 * @Description github.clyoudu.stack.TestStack
 */
public class TestStack {

    public static void main(String[] args){
        ArrayStack<String> arrayStack = new ArrayStack<>();
        arrayStack.push("first");
        arrayStack.push("second");
        arrayStack.push("third");

        System.out.println(arrayStack.isEmpty());
        System.out.println(arrayStack.peek());

        while(!arrayStack.isEmpty()){
            System.out.println(arrayStack.pop());
        }

        arrayStack.push("1");
        arrayStack.push("2");
        arrayStack.push("3");

        arrayStack.empty();
        System.out.println(arrayStack.isEmpty());

    }

}
