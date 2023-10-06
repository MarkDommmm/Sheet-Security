package hh.service;

import hh.exception.CustomsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IGenericService<T,K, E> {
    Page<T> getAll(Pageable pageable, String search);
    List<T> getAll();

    void save(K k) throws CustomsException;

    T update(K k, E e) throws CustomsException;

    T findById(E e) throws CustomsException;

    T remove(E e) throws CustomsException;


}
