package github.clyoudu.list;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2018/4/25
 * @time 22:56
 * @desc List
 */
public interface List<E> extends Collection<E> {

    int indexOf(E element);
    E get(int index);
    boolean insert(int index,E element);
    E replace(int index,E newElement);
    E remove(int index);

}
