package github.clyoudu.quene;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/12 17:12
 * @Description SimpleArrayQueue
 */
public class SimpleArrayQueue<E> implements Queue<E> {

    protected Object[] elements;

    protected int front;

    protected int rear;

    protected int capacity;

    public SimpleArrayQueue(int capacity){
        this.elements = new Object[capacity];
        this.capacity = capacity;
    }

    @Override
    public boolean add(E element) {
        if(isFull()){
            throw new RuntimeException("Queue Full!");
        }
        elements[rear++] = element;
        return true;
    }

    @Override
    public E poll() {
        E element = (E) elements[front];

        elements[front++] = null;

        return element;
    }

    @Override
    public void clear() {
        elements = new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public boolean isFull() {
        return rear - front == capacity;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        return (E) elements[front];
    }
}
