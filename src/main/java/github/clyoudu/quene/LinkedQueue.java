package github.clyoudu.quene;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/12 17:46
 * @Description LinkedQueue
 */
public class LinkedQueue<E> implements Queue<E> {

    Node<E> front;

    Node<E> rear;

    int capacity;

    int currentCapacity;

    public LinkedQueue(int capacity){
        this.capacity = capacity;
    }

    @Override
    public boolean add(E element) {
        if(isFull()){
            throw new RuntimeException("Queue Full!");
        }

        Node<E> node = new Node<>();
        node.element = element;
        if(isEmpty()){

            front = node;
            rear = node;
        }else{
           rear.next = node;
           rear = node;
        }

        currentCapacity ++;

        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        E element = front.element;
        currentCapacity --;
        front = front.next;

        return element;
    }

    @Override
    public void clear() {
        currentCapacity = 0;
        front = rear = null;
    }

    @Override
    public boolean isEmpty() {
        return currentCapacity == 0 || front == null;
    }

    @Override
    public boolean isFull() {
        return currentCapacity == capacity;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        return front.element;
    }

    class Node<EE> {
        EE element;
        Node<EE> next;
    }
}
