
package com.xuanhieu.di.util;

//biến data type thành tham số, truyền tham số theo kiểu lấy data type
//mà truyền, không phải truyền 1 value cụ thể nào đó
//kĩ thuật này gọi là GENERIC

@FunctionalInterface
public interface Filter<T> {
    public boolean check(T t);
}
