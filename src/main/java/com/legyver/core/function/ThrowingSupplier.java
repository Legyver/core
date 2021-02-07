package com.legyver.core.function;

import com.legyver.core.exception.CoreException;

/**
 *
 * Version of {@link java.util.function.Supplier} that throws a CoreException
 * @param <T> the return type
 */
@FunctionalInterface
public interface ThrowingSupplier<T> {

	/**
	 * Version of {@link java.util.function.Supplier} that throws a CoreException
	 * @return the result
	 * @throws CoreException if the supplier throws an exception
	 */
	T get() throws CoreException;
}
