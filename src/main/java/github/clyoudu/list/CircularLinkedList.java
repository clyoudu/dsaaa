package github.clyoudu.list;

import java.util.Iterator;

/**
 * Create by IntelliJ IDEA
 *
 * @Author chenlei
 * @DateTime 2018/5/3 15:37
 * @Description CircularLinkedList
 */
public class CircularLinkedList<E> extends SingleLinkedList<E> {

    public CircularLinkedList(){
        head = null;
        tail = null;
    }

    public CircularLinkedList(E...elements){
        SingleLinkedList.Node<E> prevNode = head;
        for (E element : elements) {
            if (size() == 0) {
                head = new SingleLinkedList.Node<>(element, null);
                prevNode = head;
            } else {
                SingleLinkedList.Node<E> currentNode = new SingleLinkedList.Node<>(element, null);
                prevNode.next = currentNode;
                prevNode = currentNode;
            }
            size++;
        }
        tail = prevNode;
        tail.next = head;
    }

    @Override
    public int indexOf(E element) {
        if (head == null || size() == 0) {
            return -1;
        }

        int index = 0;
        if (element == null) {
            for (SingleLinkedList.Node<E> node = head; node != null; node = node.next) {
                if (node.element == null) {
                    return index;
                }
                index++;
                if(index >= size()){
                    break;
                }
            }
        } else {
            for (SingleLinkedList.Node<E> node = head; node != null; node = node.next) {
                if (element.equals(node.element)) {
                    return index;
                }
                index++;
                if(index >= size()){
                    break;
                }
            }
        }

        return -1;
    }

    @Override
    public boolean insert(int index, E element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(index + " >= " + size);
        }

        if (index == 0) {
            Node<E> oldHead = head;
            head = new Node<>(element, oldHead);
            tail.next = head;
            size++;
        } else {
            Node<E> oldPreNode = node(index - 1);
            Node<E> node = oldPreNode.next;
            oldPreNode.next = new Node<>(element, node);
            size++;
        }

        return true;
    }

    @Override
    public E remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(index + " >= " + size);
        }

        if (index == 0) {
            Node<E> oldHead = head;
            head = oldHead.next;
            if(head == null){
                tail = null;
            } else {
                tail.next = head;
            }
            size--;
            return head == null ? null : head.element;
        } else {
            Node<E> toDelPrevNode = node(index - 1);
            Node<E> toDelNode = toDelPrevNode.next;
            toDelPrevNode.next = toDelPrevNode.next.next;
            if(size - 1 == index){
                tail = toDelPrevNode;
            }
            tail.next = head;
            size--;
            return toDelNode.element;
        }
    }

    @Override
    public boolean add(E element) {
        Node<E> node = new Node<>(element, null);
        if(isEmpty()){
            head = node;
            tail = head;
        }else{
            tail.next = node;
            tail = node;
        }
        tail.next = head;
        size++;
        return true;
    }
}
