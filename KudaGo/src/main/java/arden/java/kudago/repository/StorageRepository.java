package arden.java.kudago.repository;

import java.util.List;

public interface StorageRepository<K, V> {
    V create(K key, V value);
    V read(K key);
    List<V> readAll();
    V update(K key, V value);
    boolean delete(K key);
}
