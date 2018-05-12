package github.clyoudu.queue;

import github.clyoudu.quene.LinkedQueue;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/12 17:58
 * @Description TestLinkedQueue
 */
public class TestLinkedQueue {

    public static void main(String[] args){
        LinkedQueue<String> queue = new LinkedQueue<>(5);
        queue.add("1");
        System.out.println(queue.poll());
        queue.add("2");
        System.out.println(queue.poll());
        queue.add("3");
        System.out.println(queue.poll());
        queue.add("4");
        System.out.println(queue.poll());
        queue.add("5");
        System.out.println(queue.poll());
        queue.add("6");
        System.out.println(queue.poll());
        queue.add("7");
        queue.add("8");
        queue.add("9");
        queue.add("10");
        queue.add("11");
        queue.add("12");
        queue.add("13");
    }

}
