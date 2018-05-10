package github.clyoudu.stack;

import github.clyoudu.list.ArrayList;
import github.clyoudu.list.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/10 11:43
 * @Description ArrayStack
 */
public class ArrayStack<E> extends AbstractStack<E> implements Stack<E> {

    public ArrayStack() {
        super(new ArrayList<>());
    }
}
