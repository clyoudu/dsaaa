package github.clyoudu.queue;

import github.clyoudu.quene.SimpleArrayQueue;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/12 17:19
 * @Description TestSimpleArrayQueue
 */
public class TestSimpleArrayQueue {

    public static void main(String[] args){
        SimpleArrayQueue<String> queue = new SimpleArrayQueue<>(5);
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
