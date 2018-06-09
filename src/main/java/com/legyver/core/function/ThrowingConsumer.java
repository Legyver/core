package com.legyver.core.function;

import com.legyver.core.exception.CoreException;

public interface ThrowingConsumer<T extends Object> {

	void accept(T t) throws CoreException;
}
