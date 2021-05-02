package com.legyver.core.function;

import com.legyver.core.exception.CoreException;

/**
 * Version of {@link java.util.function.BiConsumer} that throws a CoreException
 * @param <T> the type of the first argument
 * @param <U> the type of the second argument
 */
@FunctionalInterface
public interface ThrowingBiConsumer<T, U> {
	/**
	 * Version of {@link java.util.function.BiConsumer} that throws a CoreException
	 * @param t the first parameter to accept
	 * @param u the second parameter to accept
	 * @throws CoreException if the consumer throws an exception
	 */
	void accept(T t, U u) throws CoreException;
}
