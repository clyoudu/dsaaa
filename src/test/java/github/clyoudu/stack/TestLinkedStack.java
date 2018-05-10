package github.clyoudu.stack;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/10 14:23
 * @Description TestLinkedStack
 */
public class TestLinkedStack {

    public static void main(String[] args){
        LinkedStack<String> linkedStack = new LinkedStack<>();
        linkedStack.push("first");
        linkedStack.push("second");
        linkedStack.push("third");

        System.out.println(linkedStack.isEmpty());
        System.out.println(linkedStack.peek());

        while(!linkedStack.isEmpty()){
            System.out.println(linkedStack.pop());
        }

        linkedStack.push("1");
        linkedStack.push("2");
        linkedStack.push("3");

        linkedStack.empty();
        System.out.println(linkedStack.isEmpty());

    }

}
