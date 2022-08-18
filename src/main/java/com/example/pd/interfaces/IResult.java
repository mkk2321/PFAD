package com.example.pd.interfaces;

public interface IResult<T extends Enum<?>> {
    T getResult();

    void setResult(T t);
}
