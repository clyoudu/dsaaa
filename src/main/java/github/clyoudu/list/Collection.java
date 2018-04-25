package github.clyoudu.list;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2018/4/25
 * @time 22:51
 * @desc Collection
 */
public interface Collection<E> extends Iterable<E> {

    boolean add(E element);
    E remove(E element);
    int size();
    boolean isEmpty();
    boolean contains(E element);
    void clear();

}
