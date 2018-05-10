package github.clyoudu.stack;

import github.clyoudu.list.ArrayList;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/10 11:43
 * @Description ArrayStack
 */
public class ArrayStack<E> {

    ArrayList<E> arrayList;

    public ArrayStack(){
        arrayList = new ArrayList<>();
    }

    public E push(E element){
        arrayList.add(element);
        return element;
    }

    public E pop(){
        return arrayList.remove(arrayList.size() - 1);
    }

    public E peek(){
        return arrayList.get(arrayList.size() - 1);
    }

    public void empty(){
        arrayList.clear();
    }

    public boolean isEmpty(){
        return arrayList.isEmpty();
    }

}
