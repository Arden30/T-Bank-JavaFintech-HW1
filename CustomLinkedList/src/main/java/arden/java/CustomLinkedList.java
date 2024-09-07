package arden.java;

import java.util.Collection;

public class CustomLinkedList<T> {
    Node<T> head;
    Node<T> tail;
    int size = 0;

    public CustomLinkedList() {
        head = null;
        tail = null;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }

        tail = newNode;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.data;
    }

    public boolean remove(T data) {
        if (head == null) {
            return false;
        }

        Node<T> node = head;
        while (node != null) {
            if (node.data.equals(data)) {
                if (node == head) {
                    head = node.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null;
                    }
                } else if (node == tail) {
                    tail = node.prev;
                    tail.next = null;
                } else {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }
                size--;

                return true;
            }

            node = node.next;
        }

        return false;
    }

    public boolean contains(T data) {
        if (head == null) {
            return false;
        }

        Node<T> node = head;
        while (node != null) {
            if (node.data.equals(data)) {
                return true;
            }

            node = node.next;
        }

        return false;
    }

    public void addAll(Collection<T> c) {
        for (T item : c) {
            add(item);
        }
    }

    public int size() {
        return size;
    }
}
