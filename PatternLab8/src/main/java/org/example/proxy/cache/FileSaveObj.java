package org.example.proxy.cache;

import java.io.Serializable;

public class FileSaveObj<T> implements Serializable {
    // поля идентифицирующие результат выполнения метода
    private Object[] identityBy;
    // результат выполнения метода
    private T result;

    public FileSaveObj(Object[] identityBy, T result) {
        this.identityBy = identityBy;
        this.result = result;
    }

    public Object[] getIdentityBy() {
        return identityBy;
    }

    public void setIdentityBy(Object[] identityBy) {
        this.identityBy = identityBy;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
