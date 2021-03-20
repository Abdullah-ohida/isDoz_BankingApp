package com.database;

import com.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CentralDBImp<T extends Storable> implements CentralDB<T> {
   List<T> dataStore = new ArrayList<>();

    @Override
    public void save(T t) {
        dataStore.add(t);
    }

    @Override
    public boolean contains(T t) {
        return dataStore.contains(t);
    }

    @Override
    public void delete(Optional<T> t) {
        dataStore.remove(t);
    }

    @Override
    public List<T> findAll() {
        return dataStore;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Optional<T> findById(String storableId) {
        for(T item : dataStore){
            if (item.getId().equals(storableId))
                return Optional.of(item);
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> findByName(String StorableId) {
        return Optional.empty();
    }

    @Override
    public Optional<T> findByAccountNumber(String storableAccountNumber) {
        for(T item : dataStore){
            if (item.getAccountNumber().equals(storableAccountNumber))
                return Optional.of(item);
        }
        return Optional.empty();
    }

}
