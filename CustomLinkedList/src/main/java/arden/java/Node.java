package arden.java;

public class Node<T> {
    Node<T> prev;
    Node<T> next;
    T data;

    public Node(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
