package github.clyoudu.quene;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/12 17:22
 * @Description ArrayQueue
 */
public class ArrayQueue<E> extends SimpleArrayQueue<E> implements Queue<E> {

    public ArrayQueue(int capacity){
        super(capacity);
    }

    @Override
    public boolean add(E element) {
        if(isFull()){
            throw new RuntimeException("Queue Full!");
        }
        elements[rear] = element;
        rear = (rear + 1) % 5;
        return true;
    }

    @Override
    public E poll() {
        E element = (E) elements[front];

        elements[front] = null;
        front = (front + 1) % 5;

        return element;
    }

    @Override
    public boolean isFull() {
        return super.isFull();
    }
}
