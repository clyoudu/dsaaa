package github.clyoudu.quene;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/12 16:29
 * @Description Queue
 */
public interface Queue<E> {

    boolean add(E element);

    E poll();

    void clear();

    boolean isEmpty();

    boolean isFull();

    E peek();

}
