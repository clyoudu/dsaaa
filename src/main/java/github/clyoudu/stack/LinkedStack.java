package github.clyoudu.stack;

import github.clyoudu.list.SingleLinkedList;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/10 14:09
 * @Description LinkedStack
 */
public class LinkedStack<E> extends AbstractStack<E> implements Stack<E> {

    public LinkedStack() {
        super(new SingleLinkedList<>());
    }
}
