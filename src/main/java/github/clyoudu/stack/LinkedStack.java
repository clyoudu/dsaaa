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

    @Override
    public E push(E element) {
        list.insert(0,element);
        return element;
    }

    @Override
    public E pop() {
        if (list.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Empty Stack!");
        }

        return list.remove(0);
    }

    @Override
    public E peek() {
        if (list.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Empty Stack!");
        }

        return list.get(0);
    }
}
