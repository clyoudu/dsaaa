package github.clyoudu.list;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2018/4/26
 * @time 21:46
 * @desc SingleLinkedList
 */
public class SingleLinkedList<E> implements List<E> {

    Node<E> head;

    Node<E> tail;

    int size;

    public SingleLinkedList() {
        head = null;
        tail = null;
    }

    public SingleLinkedList(E... elements) {
        Node<E> prevNode = head;
        for (E element : elements) {
            if (size() == 0) {
                head = new Node<>(element, null);
                prevNode = head;
            } else {
                Node<E> currentNode = new Node<>(element, null);
                prevNode.next = currentNode;
                prevNode = currentNode;
            }
            size++;
        }
        tail = prevNode;
    }

    @Override
    public int indexOf(E element) {
        if (head == null || size() == 0) {
            return -1;
        }

        int index = 0;
        if (element == null) {
            for (Node<E> node = head; node != null; node = node.next) {
                if (node.element == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node<E> node = head; node != null; node = node.next) {
                if (element.equals(node.element)) {
                    return index;
                }
                index++;
            }
        }

        return -1;
    }

    @Override
    public E get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(index + " >= " + size);
        }

        return node(index).element;
    }

    @Override
    public boolean insert(int index, E element) {
        if (index != 0 && index >= size) {
            throw new IndexOutOfBoundsException(index + " >= " + size);
        }

        if (index == 0) {
            Node<E> oldHead = head;
            head = new Node<>(element, oldHead);
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
    public E replace(int index, E newElement) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(index + " >= " + size);
        }

        Node<E> node = node(index);
        E element = node.element;
        node.element = newElement;

        return element;
    }

    @Override
    public E remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(index + " >= " + size);
        }

        if (index == 0) {
            Node<E> oldHead = head;
            head = oldHead.next;
            size--;
            return oldHead.element;
        } else {
            Node<E> toDelPrevNode = node(index - 1);
            Node<E> toDelNode = toDelPrevNode.next;
            toDelPrevNode.next = toDelPrevNode.next.next;
            if(size - 1 == index){
                tail = toDelPrevNode;
            }
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
        size++;
        return true;
    }

    @Override
    public E remove(E element) {
        int index = indexOf(element);
        if (index >= 0) {
            return remove(index);
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;

    }

    @Override
    public Iterator<E> iterator() {
        return new SingleLinkedListIterator();
    }

    private class SingleLinkedListIterator implements Iterator<E>{

        Node<E> prevNode = null;
        Node<E> node = head;

        @Override
        public boolean hasNext() {
            return node != null && size != 0;
        }

        @Override
        public E next() {
            E element = node.element;
            prevNode = node;
            node = node.next;
            return element;
        }

        @Override
        public void remove() {
            SingleLinkedList.this.remove(prevNode);
        }
    }

    Node<E> node(int index) {
        int cursor = 0;
        Node<E> node = head;
        for (; node != null; node = node.next) {
            if (cursor == index) {
                return node;
            }
            cursor++;
        }

        return node;
    }

    final static class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[ ");
        int count = 0;
        for(Node<E> node = head;node != null && count < size;node = node.next,count++){
            stringBuilder.append(String.valueOf(node.element)).append(",");
        }

        String toReturn = stringBuilder.toString();
        if(toReturn.endsWith(",")){
            toReturn = toReturn.substring(0,toReturn.lastIndexOf(","));
        }

        return toReturn + " ]";
    }

    Node<E> remove(Node<E> node){
        if(head == null || size == 0){
            return null;
        }else{
            for (Node<E> n = head;n != null;n = n.next){
                if(n.equals(node)){
                    if(size == 1){
                        head = null;
                        tail = null;
                    }else{
                        if(n == head){
                            head = head.next;
                            tail.next = head;
                        }else if(n == tail){
                            Node<E> tailPrev = node(size - 2);
                            tailPrev.next = head;
                        }else{
                            Node<E> nodePrev = node(nodeIndex(node) - 1);
                            nodePrev.next = node.next;
                        }
                    }
                    size--;
                    return n;
                }
            }
        }
        return null;
    }

    private int nodeIndex(Node<E> node) {
        int index = 0;
        for (Node<E> n = head;n != null;n = n.next,index ++){
            if(n.equals(node)){
                return index;
            }
        }
        return -1;
    }
}
