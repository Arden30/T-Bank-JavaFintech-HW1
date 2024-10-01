package arden.java.kudago.repository.impl;

import arden.java.kudago.repository.StorageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StorageRepositoryImpl<K, V> implements StorageRepository<K, V> {
    private final Map<K, V> storage = new ConcurrentHashMap<>();

    @Override
    public V create(K key, V value) {
        storage.put(key, value);

        return value;
    }

    @Override
    public V read(K key) {
        return storage.get(key);
    }

    @Override
    public List<V> readAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public V update(K key, V value) {
        storage.put(key, value);

        return value;
    }

    @Override
    public boolean delete(K key) {
        return storage.remove(key) != null;
    }
}
