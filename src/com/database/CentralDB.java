package com.database;

import com.Account;

import java.util.List;
import java.util.Optional;

public interface CentralDB<T>{
    void save(T t);

    boolean contains(T t);

    void delete(Optional<T> t);

    List<T> findAll();

    int size();

    Optional<T> findById(String StorableId);

    Optional<T> findByName(String StorableId);

    Optional<T> findByAccountNumber(String storableAccountNumber);
}
