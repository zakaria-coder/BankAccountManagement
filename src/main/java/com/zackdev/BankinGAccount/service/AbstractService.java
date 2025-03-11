package com.zackdev.BankinGAccount.service;

import java.util.List;


public interface AbstractService<T> {
    List<T> findAll();
    T findById(Integer id);
    Integer save(T dto);
    void delete(Integer t);
}
