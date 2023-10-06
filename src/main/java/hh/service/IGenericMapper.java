package hh.service;

import hh.exception.CustomsException;

public interface IGenericMapper <T,K,V>{
    T toEntity(K k) throws CustomsException;

    V toResponse(T t);
}
