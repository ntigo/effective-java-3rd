package com.ntigo.study.effectivejava3rd.item03.assist;

public interface IGenericSingleton<T> {
    boolean send( T message );
}
