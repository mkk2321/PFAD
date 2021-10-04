package com.example.pfad1.interfaces;

public interface IResult<T extends Enum<?>> {
    T getResult();

    void setResult(T t);
}
