package github.clyoudu.stack;

import github.clyoudu.list.List;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/10 14:17
 * @Description AbstractStack
 */
public abstract class AbstractStack<E> implements Stack<E> {

    protected List<E> list;

    protected AbstractStack(List<E> list){
        this.list = list;
    }

    @Override
    public E push(E element){
        list.add(element);
        return element;
    }

    @Override
    public E pop(){
        if (list.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Empty Stack!");
        }
        return list.remove(list.size() - 1);
    }

    @Override
    public E peek(){
        if (list.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Empty Stack!");
        }
        return list.get(list.size() - 1);
    }

    @Override
    public void empty(){
        list.clear();
    }

    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

}
