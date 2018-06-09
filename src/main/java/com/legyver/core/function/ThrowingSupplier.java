package com.legyver.core.function;

import com.legyver.core.exception.CoreException;

public interface ThrowingSupplier<T> {

	T get() throws CoreException;
}
