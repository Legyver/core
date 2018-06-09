package com.legyver.core.function;

import com.legyver.core.exception.CoreException;

@FunctionalInterface
public interface ThrowingFunction<T extends Object, R extends Object> {

	public R apply(T t) throws CoreException;
}
