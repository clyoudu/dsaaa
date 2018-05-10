package github.clyoudu.stack;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/10 14:09
 * @Description Stack
 */
public interface Stack<E> {

    E push(E element);

    E pop();

    E peek();

    void empty();

    boolean isEmpty();

}
