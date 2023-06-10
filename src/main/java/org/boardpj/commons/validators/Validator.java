package org.boardpj.commons.validators;

public interface Validator<T> {
    void check(T t);
}
