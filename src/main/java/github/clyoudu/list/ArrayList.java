package github.clyoudu.list;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2018/4/25
 * @time 23:00
 * @desc ArrayList
 */
public class ArrayList<E> implements List<E> {

    Object[] elements;
    int size = 0;
    private static final Object[] EMPTY_ELEMENTS = {};
    private static final Integer DEFAULT_CAPACITY = 10;

    public ArrayList() {
        elements = EMPTY_ELEMENTS;
    }

    public ArrayList(E... elements) {
        for (E element : elements) {
            add(element);
        }
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < elements.length; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public E get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(index + " >= " + size);
        }
        return (E) elements[index];
    }

    @Override
    public boolean add(E element) {
        if (elements == EMPTY_ELEMENTS) {
            elements = new Object[DEFAULT_CAPACITY];
            elements[0] = element;
            size++;
            return true;
        } else {
            ensureCapacity(size() + 1);
            elements[size] = element;
            size++;
            return true;
        }
    }

    private void ensureCapacity(int toSize) {
        if (toSize > elements.length) {
            Object[] newElements = new Object[elements.length + elements.length / 2];
            for (int i = 0; i < elements.length; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }

        if (toSize < elements.length && toSize > DEFAULT_CAPACITY && toSize < elements.length / 2) {
            Object[] newElements = new Object[toSize];
            for (int i = 0; i < toSize; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    @Override
    public E remove(E element) {
        int index = indexOf(element);
        if (index >= 0) {
            for (int i = index; i < size() - 1; i++) {
                elements[i] = elements[i + 1];
            }
            size--;
            ensureCapacity(size());
            return element;
        } else {
            return null;
        }
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
        return indexOf(element) > 0;
    }

    @Override
    public boolean insert(int index, E element) {
        if(index >= size){
            throw new IndexOutOfBoundsException(index + " >= " + size);
        }

        ensureCapacity(size() + 1);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
        size++;

        return true;
    }

    @Override
    public E replace(int index, E newElement) {
        if(index >= size){
            throw new IndexOutOfBoundsException(index + " >= " + size);
        }

        E oldElement = (E) elements[index];
        elements[index] = newElement;

        return oldElement;
    }

    @Override
    public E remove(int index) {
        if(index >= size){
            throw new IndexOutOfBoundsException(index + " >= " + size);
        }

        E element = (E) elements[index];

        for (int i = index; i < size() - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        ensureCapacity(size());
        return element;
    }

    @Override
    public void clear() {
        size = 0;
        elements = EMPTY_ELEMENTS;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator(0);
    }

    private class ArrayListIterator implements Iterator<E>{

        int index;
        int preIndex = 0;

        ArrayListIterator(int index){
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            preIndex = index;
            return (E) elements[index++];
        }

        @Override
        public void remove() {
            ArrayList.this.remove((E) elements[preIndex]);
            if (preIndex < index) {
                index--;
            }
        }
    }
}
